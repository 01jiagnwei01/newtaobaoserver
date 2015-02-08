package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.PWDGenter;
import com.gxkj.common.util.SystemGlobals;
import com.gxkj.taobaoservice.daos.OperateLogDao;
import com.gxkj.taobaoservice.daos.UserAccountDao;
import com.gxkj.taobaoservice.daos.UserAccountLogDao;
import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.daos.UserLinkDao;
import com.gxkj.taobaoservice.dto.RegObjDTO;
import com.gxkj.taobaoservice.entitys.OperateLog;
import com.gxkj.taobaoservice.entitys.RegLog;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.entitys.UserLink;
import com.gxkj.taobaoservice.enums.OperateTypes;
import com.gxkj.taobaoservice.enums.RegLogTypes;
import com.gxkj.taobaoservice.enums.UserBaseStatus;
import com.gxkj.taobaoservice.enums.UserLinkStatus;
import com.gxkj.taobaoservice.enums.UserLinkTypes;
import com.gxkj.taobaoservice.services.EmailService;
import com.gxkj.taobaoservice.services.RegLogService;
import com.gxkj.taobaoservice.services.RegService;
import com.gxkj.taobaoservice.util.RegexUtils;
@Service
public class RegServiceImpl implements RegService {

	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSenderImpl javaMailSender;
	
	@Autowired
	@Qualifier("regTemplateMailMessage")
	SimpleMailMessage regTemplateMailMessage;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	RegLogService  regLogService ;
	
	@Autowired
	UserBaseDao userBaseDao;
	
	@Autowired
	private UserLinkDao userLinkDao;
	

	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private UserAccountLogDao userAccountLogDao;
	
	@Autowired
	private OperateLogDao operateLogDao;
	
	public boolean doSendMail(String mail) throws SQLException,
		BusinessException, BindException, MessagingException {
			boolean isReged = emailService.emailIsRegd(mail);
			if(isReged){
				throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_REGED);
			}
			 regLogService.updateEmaiToNoEnable(mail);
			
			String code = RegexUtils.getRandomNum(6)+"";
			Date now = new Date();
			RegLog regLog = new RegLog();
			regLog.setCode(code);
			regLog.setCreateDime(now);
			regLog.setType(RegLogTypes.email);
			regLog.setValue(mail);
			regLog.setEnabled(true);
			int validTimeLeng = SystemGlobals.getIntPreference("reg.code.valid.time", 5);
			Date expTime = DateUtils.addMilliseconds(now, validTimeLeng);
			regLog.setExpTime(expTime);
			/**
			 * 保存注册信息
			 */
			regLogService.addRegLog(regLog);
			
			/**
			 * 发送邮件
			 */
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
			helper.setFrom(regTemplateMailMessage.getFrom());
			helper.setTo(mail);
			helper.setSubject(regTemplateMailMessage.getSubject());
			helper.setText(String.format( regTemplateMailMessage.getText(), code), true);
			javaMailSender.send(msg);
			return true;
	}

	/**
	 * 进行注册操作
	 * @throws SQLException 
	 * @throws BusinessException 
	 */
	public void doRegFn(RegObjDTO regObjDTO) throws SQLException, BusinessException {
		String password = regObjDTO.getPassword();
		String rePassword = regObjDTO.getRePassword();
		String userName = regObjDTO.getUserName();
		if(StringUtils.isBlank(password )) {
			throw new BusinessException(BusinessExceptionInfos.PASSWORD_IS_BLANK,"password");
		}
		if(StringUtils.isBlank(rePassword )) {
			throw new BusinessException(BusinessExceptionInfos.REPASSWORD_IS_BLANK,"rePassword");
		}
		/**
		 * 判断用户名是否被使用过 
		 */
		if(StringUtils.isBlank(userName )) {
			throw new BusinessException(BusinessExceptionInfos.USER_NAME_IS_BLANK,"userName");
		}
		boolean userNameIsReged = userBaseDao.userNameIsReged(userName);
		if(userNameIsReged) {
			throw new BusinessException(BusinessExceptionInfos.USER_NAME_IS_REGED,"userName");
		}
		
		RegLog regLog = regLogService.getRegLogByTypeAndValue(RegLogTypes.email,regObjDTO.getEmail());
		if(regLog == null) {
			throw new BusinessException(BusinessExceptionInfos.EMAIL_NOT_SEND_CODE,"email");
		}
		if (!regLog.getCode().equals(regObjDTO.getYanzhengma())) {
			throw new BusinessException(BusinessExceptionInfos.YAN_ZHENG_MA_ERROR,"code");
		}
		boolean isReged = emailService.emailIsRegd(regObjDTO.getEmail());
		if(isReged){
			throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_REGED,"email");
		}
		/**
		 * 进行注册流程
		 */
		Date now = new Date();
		regLog.setActiveTime(now);
		
		UserBase userBase = new UserBase();
		userBase.setPassword(PWDGenter.generateKen(password) );
		userBase.setUserName(userName);
		userBase.setRegTime(now);
		userBase.setStatus(UserBaseStatus.NORMAL);
		userBaseDao.insert(userBase);
		
		
		/**
		 * 联系方式
		 * 邮箱：默认
		 */
		UserLink emailLink = new UserLink();
		emailLink.setStatus(UserLinkStatus.NORMAL);
		emailLink.setLinkType(UserLinkTypes.EMAIL);
		emailLink.setLinkValue(regObjDTO.getEmail());
		emailLink.setUserId(userBase.getId());
		userLinkDao.insert(emailLink);
		
		/**
		 * 初始化账号信息
		 */
		UserAccount userAccount = new UserAccount();
		userAccount.setUserId(userBase.getId());
		userAccountDao.insert(userAccount);
		userBase.setUerAccount(userAccount);
		
		/**
		 * 进行操作日志
		 */
		OperateLog operateLog = new OperateLog();
		operateLog.setIp(regObjDTO.getIp());
		operateLog.setOperateTime(now);
		operateLog.setOperateType(OperateTypes.REG_EMAIL);
		operateLog.setAfterValue(regObjDTO.getEmail());
		operateLog.setUser_id(userBase.getId());
		operateLogDao.insert(operateLog);
		
	}

}
