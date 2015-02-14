package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.PWDGenter;
import com.gxkj.common.util.SystemGlobals;
import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.daos.YanZhengMaBindCodeDao;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.entitys.YanZhengMaBindCode;
import com.gxkj.taobaoservice.enums.YanZhengMaTranType;
import com.gxkj.taobaoservice.enums.YanZhengMaTypes;
import com.gxkj.taobaoservice.services.EmailService;
import com.gxkj.taobaoservice.services.PasswordService;
import com.gxkj.taobaoservice.util.RegexUtils;
@Service
public class PasswordServiceImpl implements PasswordService {

	@Autowired
	@Qualifier("reSetBindEmailTemplateMailMessage")
	SimpleMailMessage reSetBindEmailTemplateMailMessage;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	private UserBaseDao userBaseDao;
	
	@Autowired
	private YanZhengMaBindCodeDao yanZhengMaBindCodeDao;
	
	@Autowired
	private JavaMailSenderImpl javaMailSender;
	
	public void doSendMail(UserBase userBase,String tomail) throws BusinessException, SQLException, BindException, MessagingException {
		
		if(StringUtils.isBlank(tomail)  ) {
			throw new BusinessException(BusinessExceptionInfos.NEW_EMAIL_IS_BLANK,"email");
		}
//		String bindEmail = userBase.getBindEmail();
//		if(StringUtils.isNotBlank(bindEmail) && tomail.equals(bindEmail )) {
//			throw new BusinessException(BusinessExceptionInfos.NEW_EMAIL_IS_EQUAL_USER_EMAIL,"email");
//		}
		boolean isReged = emailService.emailIsRegdByOtherPeople(userBase,tomail);
		if(isReged){
			throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_REGED,"email");
		}
		yanZhengMaBindCodeDao.updateEmaiToNoEnable(tomail);
		
		/**
		 * 保存修改的注册码
		 */
		String code = RegexUtils.getRandomNum(6)+"";
		Date now = new Date();
		YanZhengMaBindCode yanZhengMaBindCode = new YanZhengMaBindCode();
		yanZhengMaBindCode.setCode(code);
		yanZhengMaBindCode.setCreateDime(now);
		yanZhengMaBindCode.setType(YanZhengMaTypes.email);
		yanZhengMaBindCode.setValue(tomail);
		yanZhengMaBindCode.setEnabled(true);
		yanZhengMaBindCode.setTranType(YanZhengMaTranType.UPDATE_BIND_EMAIL);
		int validTimeLeng = SystemGlobals.getIntPreference("reg.code.valid.time", 5);
		Date expTime = DateUtils.addMilliseconds(now, validTimeLeng);
		yanZhengMaBindCode.setExpTime(expTime);
		yanZhengMaBindCode.setUserId(userBase.getId());
		yanZhengMaBindCodeDao.insert(yanZhengMaBindCode);
		
		
		/**
		 * 发送邮件
		 */
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
		helper.setFrom(reSetBindEmailTemplateMailMessage.getFrom());
		helper.setTo(tomail);
		helper.setSubject(reSetBindEmailTemplateMailMessage.getSubject());
		helper.setText(String.format( reSetBindEmailTemplateMailMessage.getText(), code), true);
		javaMailSender.send(msg);

	}

 
	public void doupdateByEmail(UserBase userBase, String email,
			String caozuoma, String yanzhengma) throws BusinessException, SQLException {
		if(StringUtils.isBlank(email)) {
			 throw new BusinessException(BusinessExceptionInfos.NEW_EMAIL_IS_BLANK,"email");
		 }
		 if(StringUtils.isBlank(yanzhengma)) {
			 throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_IS_BLANK,"yanzhengma");
		 }
		 if(StringUtils.isBlank(caozuoma)) {
			 throw new BusinessException(BusinessExceptionInfos.CAO_ZUO_MA_IS_BLANK,"caozuoma");
		 }
		 String sessionCaozuoma = userBase.getCaoZuoMa();
		 if(StringUtils.isBlank(sessionCaozuoma)) {
			 sessionCaozuoma = "";
		 }
		 String md5Caozuoma = PWDGenter.generateKen(caozuoma);
		 if(md5Caozuoma.equalsIgnoreCase(sessionCaozuoma)) {
			 throw new BusinessException(BusinessExceptionInfos.CAO_ZUO_MA_IS_ERROR,"caozuoma");
		 }
		 
		 YanZhengMaBindCode yanZhengMaBindCode = yanZhengMaBindCodeDao.getRegLogByUserIdAndTransAndTypeAndValue(userBase.getId(),YanZhengMaTranType.UPDATE_BIND_EMAIL,
				 YanZhengMaTypes.email,email);
		 if(yanZhengMaBindCode == null) {
			 throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_ERROR,"yanzhengma");
		 }
		 if(yanzhengma.equals(yanZhengMaBindCode.getCode())){
			 throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_ERROR,"yanzhengma");
		 }
		 
		 //重置绑定邮箱
		 userBase.setBindEmail(email);
		 userBaseDao.update(userBase);
	}

}
