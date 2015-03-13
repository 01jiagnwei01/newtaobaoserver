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
import com.gxkj.taobaoservice.daos.YanZhengMaLogDao;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.entitys.YanzhengmaLog;
import com.gxkj.taobaoservice.enums.YanZhengMaLogTranTypes;
import com.gxkj.taobaoservice.enums.YanZhengMaTypes;
import com.gxkj.taobaoservice.services.EmailService;
import com.gxkj.taobaoservice.util.RegexUtils;
@Service 
public class EmailServiceImpl  implements EmailService{

	@Autowired
	@Qualifier("reSetBindEmailTemplateMailMessage")
	SimpleMailMessage reSetBindEmailTemplateMailMessage;
	
	@Autowired
	private UserBaseDao userBaseDao;
	
	@Autowired
	private YanZhengMaLogDao yanZhengMaLogDao;
	
	@Autowired
	private JavaMailSenderImpl javaMailSender;
	
	public boolean emailIsRegd(String email ) throws SQLException {
	 
		return userBaseDao.emailIsReged(email);
	}

 
	public boolean emailIsRegdByOtherPeople(UserBase userBase, String tomail) throws SQLException {
		 
		return userBaseDao.emailIsRegdByOtherPeople(userBase.getId(),tomail);
	}


	
	
	public void doSendMailCode(UserBase userBase,String tomail) throws BusinessException, SQLException, BindException, MessagingException {
		
		if(StringUtils.isBlank(tomail)  ) {
			throw new BusinessException(BusinessExceptionInfos.NEW_EMAIL_IS_BLANK,"email");
		}
		String bindEmail = userBase.getBindEmail();
		if(StringUtils.isNotBlank(bindEmail) && tomail.equals(bindEmail )) {
			throw new BusinessException(BusinessExceptionInfos.NEW_EMAIL_IS_EQUAL_USER_EMAIL,"email");
		}
		boolean isReged = this.emailIsRegdByOtherPeople(userBase,tomail);
		if(isReged){
			throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_REGED,"email");
		}
		yanZhengMaLogDao.updateEmaiToNoEnable(tomail,YanZhengMaTypes.email);
		
		/**
		 * 保存修改的注册码
		 */
		String code = RegexUtils.getRandomNum(6)+"";
		Date now = new Date();
		YanzhengmaLog yanZhengMaBindCode = new YanzhengmaLog();
		yanZhengMaBindCode.setCode(code);
		yanZhengMaBindCode.setCreateDime(now);
		yanZhengMaBindCode.setType(YanZhengMaTypes.email);
		yanZhengMaBindCode.setValue(tomail);
		yanZhengMaBindCode.setEnabled(true);
		yanZhengMaBindCode.setTranType(YanZhengMaLogTranTypes.Update_bind);
		int validTimeLeng = SystemGlobals.getIntPreference("reg.code.valid.time", 5);
		Date expTime = DateUtils.addMilliseconds(now, validTimeLeng);
		yanZhengMaBindCode.setExpTime(expTime);
		yanZhengMaBindCode.setUserId(userBase.getId());
		yanZhengMaLogDao.insert( yanZhengMaBindCode);
		
		
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

	
	public void doUpdateByEmail(UserBase userBase, String email,
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
		 if(!md5Caozuoma.equalsIgnoreCase(sessionCaozuoma)) {
			 throw new BusinessException(BusinessExceptionInfos.CAO_ZUO_MA_IS_ERROR,"caozuoma");
		 }
		 
		 YanzhengmaLog yanZhengMaBindCode = yanZhengMaLogDao.getRegLogByUserIdAndTransAndTypeAndValue(userBase.getId(),
				 YanZhengMaLogTranTypes.Update_bind,
				 YanZhengMaTypes.email,email);
		 
		 if(yanZhengMaBindCode == null) {
			 throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_ERROR,"yanzhengma");
		 }
		 if(!yanzhengma.equals(yanZhengMaBindCode.getCode())){
			 throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_ERROR,"yanzhengma");
		 }
		 Date now = new Date();
		 yanZhengMaBindCode.setEnabled(false);
		 yanZhengMaBindCode.setActiveTime(now);
		 yanZhengMaLogDao.update(yanZhengMaBindCode);
		 
		 //重置绑定邮箱
		 userBase.setBindEmail(email);
		 userBaseDao.update(userBase);
	}


	 
	public void doFindBackPasswordMailCode(String email)
			throws BusinessException, SQLException, MessagingException, BindException {
		if(StringUtils.isBlank(email)) {
			 throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_BLANK,"email");
		 }
		if(!RegexUtils.isEmail( email)){
			 throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_INVALID,"email");
		}
		UserBase userBase = userBaseDao.getUsersByBindEmail(email);
		if(userBase == null){
			 throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_NOT_REG,"email");
		}
		
		yanZhengMaLogDao.updateEmaiToNoEnable(email,YanZhengMaTypes.email);
		/**
		 * 保存修改的注册码
		 */
		String code = RegexUtils.getRandomNum(6)+"";
		Date now = new Date();
		YanzhengmaLog yanZhengMaBindCode = new YanzhengmaLog();
		yanZhengMaBindCode.setCode(code);
		yanZhengMaBindCode.setCreateDime(now);
		yanZhengMaBindCode.setType(YanZhengMaTypes.email);
		yanZhengMaBindCode.setValue(email);
		yanZhengMaBindCode.setEnabled(true);
		yanZhengMaBindCode.setTranType(YanZhengMaLogTranTypes.FIND_BACK_PASSWORD);
		int validTimeLeng = SystemGlobals.getIntPreference("reg.code.valid.time", 5);
		Date expTime = DateUtils.addMilliseconds(now, validTimeLeng);
		yanZhengMaBindCode.setExpTime(expTime);
		yanZhengMaBindCode.setUserId(userBase.getId());
		yanZhengMaLogDao.insert( yanZhengMaBindCode);
		
		
		/**
		 * 发送邮件
		 */
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
		helper.setFrom(reSetBindEmailTemplateMailMessage.getFrom());
		helper.setTo(email);
		helper.setSubject(reSetBindEmailTemplateMailMessage.getSubject());
		helper.setText(String.format( reSetBindEmailTemplateMailMessage.getText(), code), true);
		javaMailSender.send(msg);
	}

}
