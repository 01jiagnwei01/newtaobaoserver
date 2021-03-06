package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.gxkj.taobaoservice.daos.YanZhengMaLogDao;
import com.gxkj.taobaoservice.daos.UserAccountDao;
import com.gxkj.taobaoservice.daos.UserAccountLogDao;
import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.daos.UserLinkDao;
import com.gxkj.taobaoservice.dto.RegObjDTO;
import com.gxkj.taobaoservice.dto.SmsResponse;
import com.gxkj.taobaoservice.entitys.OperateLog;
import com.gxkj.taobaoservice.entitys.YanzhengmaLog;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.OperateTypes;
import com.gxkj.taobaoservice.enums.YanZhengMaLogTranTypes;
import com.gxkj.taobaoservice.enums.YanZhengMaTypes;
import com.gxkj.taobaoservice.enums.UserBaseStatus;
import com.gxkj.taobaoservice.jmxs.JMXSEntity;
import com.gxkj.taobaoservice.services.EmailService;
import com.gxkj.taobaoservice.services.RegService;
import com.gxkj.taobaoservice.sms.SmsService;
import com.gxkj.taobaoservice.util.RegexUtils;
@Service
public class RegServiceImpl implements RegService {

	private static final Log log =  LogFactory.getLog(RegServiceImpl.class);
	
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
	private YanZhengMaLogDao yanzhengmaLogDao;
	
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
	
	@Autowired
	@Qualifier("F1SmsServiceImpl")
	private SmsService smsServiceImpl;
	
	public boolean doSendMail(String mail) throws SQLException,
		BusinessException, BindException, MessagingException {
			if(StringUtils.isBlank(mail )) {
				throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_BLANK,"email");
			}
			boolean isReged = emailService.emailIsRegd(mail);
			if(isReged){
				throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_REGED,"email");
			}
			yanzhengmaLogDao.updateToNoEnable(mail,YanZhengMaTypes.email);
			
			String code = RegexUtils.getRandomNum(6)+"";
			Date now = new Date();
			YanzhengmaLog regLog = new YanzhengmaLog();
			regLog.setCode(code);
			regLog.setCreateDime(now);
			regLog.setType(YanZhengMaTypes.email);
			regLog.setValue(mail);
			regLog.setEnabled(true);
			regLog.setTranType(YanZhengMaLogTranTypes.Reg);
			int validTimeLeng = SystemGlobals.getIntPreference("reg.code.valid.time", 5);
			Date expTime = DateUtils.addMilliseconds(now, validTimeLeng);
			regLog.setExpTime(expTime);
			/**
			 * 保存注册日志
			 */
			yanzhengmaLogDao.insert(regLog);
			
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
	 * @throws BindException 
	 */
	public void doRegFn(RegObjDTO regObjDTO) throws SQLException, BusinessException {
		String password = regObjDTO.getPassword();
		String rePassword = regObjDTO.getRePassword();
		String userName = regObjDTO.getUserName();
		String  email  = regObjDTO.getEmail();
		if(StringUtils.isBlank(password )) {
			throw new BusinessException(BusinessExceptionInfos.PASSWORD_IS_BLANK,"password");
		}
		if(StringUtils.isBlank(rePassword )) {
			throw new BusinessException(BusinessExceptionInfos.REPASSWORD_IS_BLANK,"rePassword");
		}
		/**
		 * 用户名长度不能小于5
		 */
		if(RegexUtils.stringLengLessThan(5, userName)) {
			throw new BusinessException(BusinessExceptionInfos.USER_NAME_IS_less_than,"userName");
		}
		/**
		 * 用户名长度不能大于50
		 */
		if(RegexUtils.stringLengMoreThan(50, userName)) {
			throw new BusinessException(BusinessExceptionInfos.USER_NAME_IS_OUT_MAX,"userName");
			
		}
		/**
		 * 密码长度不能大于20
		 */
		if(RegexUtils.stringLengMoreThan(20, password)) {
			throw new BusinessException(BusinessExceptionInfos.PASSWORD_IS_MORE_than_20,"password");
		}
		if(StringUtils.isBlank(userName )) {
			throw new BusinessException(BusinessExceptionInfos.USER_NAME_IS_BLANK,"userName");
		}
		
		YanzhengmaLog regLog = null;
		OperateTypes operateTypes = null;
		String afterValue = null;
		if(regObjDTO.getType().equals("phone")){
			if(!RegexUtils.isMobileNO(regObjDTO.getPhone())){
				throw new BusinessException(BusinessExceptionInfos.TEL_NO_ERROR,"phone");
			}
			/**
			 * 判断手机号码是否被注册过
			 */
			UserBase userBase = userBaseDao.getUsersByBindPhone(regObjDTO.getPhone());
			if(userBase != null){
				throw new BusinessException(BusinessExceptionInfos.TEL_IS_REGED,"phone");
			}
			
			regLog = yanzhengmaLogDao.getRegLogByTypeAndValue(YanZhengMaTypes.phone,YanZhengMaLogTranTypes.Reg,regObjDTO.getPhone()); 
			if(regLog == null) {
				throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_ERROR,"code");
			}
			
			operateTypes = OperateTypes.ACTIVE_PHONE;
			afterValue = regObjDTO.getPhone();
			
		}else if("email".equals(regObjDTO.getType())){
			if(!RegexUtils.isEmail(email)){
				throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_INVALID,"email");
			}
			/**
			 * 邮箱长度不能超过50
			 */
			if(RegexUtils.stringLengMoreThan(50, email)) {
				throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_MORE_than_50,"email");
			}
			
			/**
			 * 判断邮箱是否被注册过
			 */
			boolean isReged = emailService.emailIsRegd(regObjDTO.getEmail());
			if(isReged){
				throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_REGED,"email");
			}
			
			regLog = yanzhengmaLogDao.getRegLogByTypeAndValue(YanZhengMaTypes.email,YanZhengMaLogTranTypes.Reg,regObjDTO.getEmail()); 
			if(regLog == null) {
				throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_ERROR,"code");
			}
			operateTypes = OperateTypes.ACTIVE_EMAIL;
			afterValue = regObjDTO.getEmail();
		}
		
		
		/**
		 * 判断用户名是否被使用过 
		 */
		boolean userNameIsReged = userBaseDao.userNameIsReged(userName);
		if(userNameIsReged) {
			throw new BusinessException(BusinessExceptionInfos.USER_NAME_IS_REGED,"userName");
		}
		
		
		if (!regLog.getCode().equals(regObjDTO.getYanzhengma())) {
			throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_ERROR,"code");
		}
		
		/**
		 * 进行注册流程
		 */
		Date now = new Date();
		regLog.setActiveTime(now);
		regLog.setEnabled(false);
		yanzhengmaLogDao.update(regLog);
		
		UserBase userBase = new UserBase();
		userBase.setPassword(PWDGenter.generateKen(password) );
		userBase.setUserName(userName);
		userBase.setRegTime(now);
		userBase.setStatus(UserBaseStatus.NORMAL);
		userBase.setBindEmail(regObjDTO.getEmail());
		userBase.setBindTelphone(regObjDTO.getPhone());
		userBaseDao.insert(userBase);
		
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
		operateLog.setOperateType(operateTypes);
		operateLog.setAfterValue(afterValue);
		operateLog.setUser_id(userBase.getId());
		operateLogDao.insert(operateLog);
		
	}

 
	public void doSendPhone(String phone) throws SQLException,
			BusinessException {
		 
		if(StringUtils.isBlank(phone )) {
			throw new BusinessException(BusinessExceptionInfos.TEL_NO_IS_BLANK,"phone");
		}
		UserBase userBase = userBaseDao.getUsersByBindPhone(phone);
		if(userBase != null){
			throw new BusinessException(BusinessExceptionInfos.TEL_IS_REGED,"phone");
		}
		
		yanzhengmaLogDao.updateToNoEnable(phone,YanZhengMaTypes.phone);
		
		String code = RegexUtils.getRandomNum(6)+"";
		Date now = new Date();
		YanzhengmaLog regLog = new YanzhengmaLog();
		regLog.setCode(code);
		regLog.setCreateDime(now);
		regLog.setType(YanZhengMaTypes.phone);
		regLog.setValue(phone);
		regLog.setEnabled(true);
		regLog.setTranType(YanZhengMaLogTranTypes.Reg);
		int validTimeLeng = SystemGlobals.getIntPreference("reg.code.valid.time", 5);
		Date expTime = DateUtils.addMilliseconds(now, validTimeLeng);
		regLog.setExpTime(expTime);
		/**
		 * 保存注册日志
		 */
		yanzhengmaLogDao.insert(regLog);
		
		/**
		 * 发送手机短信
		 */
		/**
		 * 手机发送验证码
		 */
		SmsResponse repose = smsServiceImpl.sendSms(String.format(JMXSEntity.getSmsCodeTempletate(), code), phone, now);
		if(!repose.isOk()){
			log.error("手机发送验证码失败，错误原因是："+smsServiceImpl.getErrorMsg(repose.getCode()));
			throw new BusinessException(BusinessExceptionInfos.TEL_Code_Send_error,"telNo");
		}
	}

}
