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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.PWDGenter;
import com.gxkj.common.util.SystemGlobals;
import com.gxkj.taobaoservice.daos.CaoZuoMaLogDao;
import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.daos.YanZhengMaLogDao;
import com.gxkj.taobaoservice.dto.SmsResponse;
import com.gxkj.taobaoservice.entitys.CaoZuoMaLog;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.entitys.YanzhengmaLog;
import com.gxkj.taobaoservice.enums.YanZhengMaLogTranTypes;
import com.gxkj.taobaoservice.enums.YanZhengMaTypes;
import com.gxkj.taobaoservice.jmxs.JMXSEntity;
import com.gxkj.taobaoservice.services.CaoZuoMaService;
import com.gxkj.taobaoservice.sms.SmsService;
import com.gxkj.taobaoservice.util.RegexUtils;
@Service
public class CaoZuoMaServiceImpl implements CaoZuoMaService {

	private static final Log log =  LogFactory.getLog(CaoZuoMaServiceImpl.class);
	
	@Autowired
	CaoZuoMaLogDao caoZuoMaLogDao ;
	
	@Autowired
	private JavaMailSenderImpl javaMailSender;
	
	@Autowired
	@Qualifier("caozuomaTemplateMailMessage")
	SimpleMailMessage caozuomaTemplateMailMessage;
	
	@Autowired
	private UserBaseDao userBaseDao;
	
	@Autowired
	@Qualifier("F1SmsServiceImpl")
	private SmsService smsServiceImpl;
	
	@Autowired
	private YanZhengMaLogDao yanzhengmaLogDao;
	
	public boolean doSendMail(UserBase base) throws SQLException,
			BusinessException, BindException, MessagingException {
		 
		String email = base.getBindEmail();
		if(StringUtils.isBlank(email)) {
			throw new BusinessException(BusinessExceptionInfos.NO_EMAIL_IS_BIND,"email");
		}
		
		caoZuoMaLogDao.setEnableCodeToMail(base.getId(),YanZhengMaTypes.email);
		
		String code = RegexUtils.getRandomNum(6)+"";
		Date now = new Date();
		
		CaoZuoMaLog caoZuoMaLog = new CaoZuoMaLog();
		caoZuoMaLog.setUserd(base.getId());
		caoZuoMaLog.setCode(code);
		caoZuoMaLog.setCreateDime(now);
		caoZuoMaLog.setType(YanZhengMaTypes.email);
		caoZuoMaLog.setValue(email);
		caoZuoMaLog.setEnabled(true);
		int validTimeLeng = SystemGlobals.getIntPreference("reg.code.valid.time", 5);
		Date expTime = DateUtils.addMilliseconds(now, validTimeLeng);
		caoZuoMaLog.setExpTime(expTime);
		/**
		 * 保存日志
		 */
		caoZuoMaLogDao.insert(caoZuoMaLog);
		
		
		/**
		 * 发送邮件
		 */
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
		helper.setFrom(caozuomaTemplateMailMessage.getFrom());
		helper.setTo(email);
		helper.setSubject(caozuomaTemplateMailMessage.getSubject());
		helper.setText(String.format( caozuomaTemplateMailMessage.getText(), code), true);
		javaMailSender.send(msg);
		return false;
	}

	 
	public String doMailSubmitCaoZuoMa(UserBase base, String caozuoma,
			String recaozuoma, String code) throws BusinessException, SQLException {
		
		 String bindEmail = base.getBindEmail();
		 if(StringUtils.isBlank(bindEmail)) {
			 throw new BusinessException(BusinessExceptionInfos.NO_EMAIL_IS_BIND,"email");
		 }
		 if(StringUtils.isBlank(caozuoma)) {
			 throw new BusinessException(BusinessExceptionInfos.CAO_ZUO_MA_IS_BLANK,"caozuoma");
		 }
		 if(StringUtils.isBlank(recaozuoma)) {
			 throw new BusinessException(BusinessExceptionInfos.RECAO_ZUO_MA_IS_BLANK,"recaozuoma");
		 }
		 if(!recaozuoma.equals(caozuoma)) {
			 throw new BusinessException(BusinessExceptionInfos.RECAO_ZUO_MA_NOT_EQUAL,"recaozuoma");
		 }
		 /**
		  * 验证码为空
		  */
		 if(StringUtils.isBlank(code)) {
			 throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_IS_BLANK,"yanzhengma");
		 }
		 
		 CaoZuoMaLog caoZuoMaLog = caoZuoMaLogDao.getCodeByUserIdAndType(base.getId(),base.getBindEmail(),YanZhengMaTypes.email);
		 String dbCode = caoZuoMaLog==null?null:caoZuoMaLog.getCode();
		 if(dbCode == null || !dbCode.equals(code)) {
				 throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_ERROR,"yanzhengma");
		 }
		 /**
		  * 设置操作码激活时间并设置为无效
		  */
		 Date now = new Date();
		 caoZuoMaLog.setActiveTime(now);
		 caoZuoMaLog.setEnabled(false);
		 caoZuoMaLogDao.update(caoZuoMaLog);
	
		 /**
		  * 重置操作码
		  */
		 String md5CaoZuoMa = PWDGenter.generateKen(caozuoma);
		 userBaseDao.updateUserCaoZuoMa(base.getId(),md5CaoZuoMa);
		 return md5CaoZuoMa;
	}


	/**
	 * 使用旧的操作码来重置操作码 
	 */
	public String doCaoZuoMaSubmitCaoZuoMa(UserBase userBase, String caozuoma,
			String recaozuoma, String oldcode) throws BusinessException,
			SQLException {
		if(StringUtils.isBlank(oldcode)) {
			 throw new BusinessException(BusinessExceptionInfos.OLD_CAO_ZUO_MA_IS_BLANK,"oldcode");
		 }
		if(StringUtils.isBlank(caozuoma)) {
			 throw new BusinessException(BusinessExceptionInfos.CAO_ZUO_MA_IS_BLANK,"caozuoma");
		 }
		if(StringUtils.isBlank(caozuoma)) {
			 throw new BusinessException(BusinessExceptionInfos.RECAO_ZUO_MA_IS_BLANK,"recaozuoma");
		 }
		 if(!recaozuoma.equals(caozuoma)) {
			 throw new BusinessException(BusinessExceptionInfos.RECAO_ZUO_MA_NOT_EQUAL,"recaozuoma");
		 }
		 String md5OldCaoZuoMa = PWDGenter.generateKen(oldcode);
		 String dbOldCaoZuoMa = userBase.getCaoZuoMa();
		 if(!md5OldCaoZuoMa.equals(dbOldCaoZuoMa)) {
			 throw new BusinessException(BusinessExceptionInfos.OLD_CAO_ZUO_MA_IS_ERROR,"oldcode");
		 }
		 /**
		  * 重置操作码
		  */
		 String newMd5CaoZuoMa = PWDGenter.generateKen(caozuoma);
		 userBaseDao.updateUserCaoZuoMa(userBase.getId(),newMd5CaoZuoMa);
		 return newMd5CaoZuoMa;
	}


 
	public void doSendPhone(UserBase base) throws BusinessException,
			SQLException {
		if(StringUtils.isBlank(base.getBindTelphone() )) {
			throw new BusinessException(BusinessExceptionInfos.TEL_NO_IS_BLANK,"phone");
		}
//		UserBase userBase = userBaseDao.getUsersByBindPhone(base.getBindTelphone());
//		if(userBase != null){
//			throw new BusinessException(BusinessExceptionInfos.TEL_IS_REGED,"phone");
//		}
		
		
		String code = RegexUtils.getRandomNum(6)+"";
		Date now = new Date();
		YanzhengmaLog regLog = new YanzhengmaLog();
		regLog.setCode(code);
		regLog.setCreateDime(now);
		regLog.setType(YanZhengMaTypes.phone);
		regLog.setValue(base.getBindTelphone());
		regLog.setEnabled(true);
		regLog.setTranType(YanZhengMaLogTranTypes.Update_CaoZuoMa);
		int validTimeLeng = SystemGlobals.getIntPreference("reg.code.valid.time", 5);
		Date expTime = DateUtils.addMilliseconds(now, validTimeLeng);
		regLog.setExpTime(expTime);
		/**
		 * 保存注册日志
		 */
		yanzhengmaLogDao.insert(regLog);
		
		 
		/**
		 * 手机发送验证码
		 */
		SmsResponse repose = smsServiceImpl.sendSms(String.format(JMXSEntity.getSmsCodeTempletate(), code), base.getBindTelphone(), now);
		if(!repose.isOk()){
			log.error("手机发送验证码失败，错误原因是："+smsServiceImpl.getErrorMsg(repose.getCode()));
			throw new BusinessException(BusinessExceptionInfos.TEL_Code_Send_error,"telNo");
		}
		
	}


 
	public String doPhoneSubmitCaoZuoMa(UserBase base, String caozuoma,
			String recaozuoma, String code) throws BusinessException,
			SQLException {
		String bindPhone = base.getBindTelphone();
		 if(StringUtils.isBlank(bindPhone)) {
			 throw new BusinessException(BusinessExceptionInfos.TEL_NO_IS_BLANK,"phone");
		 }
		 if(StringUtils.isBlank(caozuoma)) {
			 throw new BusinessException(BusinessExceptionInfos.CAO_ZUO_MA_IS_BLANK,"caozuoma");
		 }
		 if(StringUtils.isBlank(recaozuoma)) {
			 throw new BusinessException(BusinessExceptionInfos.RECAO_ZUO_MA_IS_BLANK,"recaozuoma");
		 }
		 if(!recaozuoma.equals(caozuoma)) {
			 throw new BusinessException(BusinessExceptionInfos.RECAO_ZUO_MA_NOT_EQUAL,"recaozuoma");
		 }
		 /**
		  * 验证码为空
		  */
		 if(StringUtils.isBlank(code)) {
			 throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_IS_BLANK,"yanzhengma");
		 }
		 
		 YanzhengmaLog	regLog = yanzhengmaLogDao.getRegLogByTypeAndValue(YanZhengMaTypes.phone,YanZhengMaLogTranTypes.Update_CaoZuoMa,base.getBindTelphone());
			
		 String dbCode = regLog==null?null:regLog.getCode();
		 if(dbCode == null || !dbCode.equals(code)) {
				 throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_ERROR,"yanzhengma");
		 }
		 /**
		  * 设置操作码激活时间并设置为无效
		  */
		 	Date now = new Date();
			regLog.setActiveTime(now);
			regLog.setEnabled(false);
			yanzhengmaLogDao.update(regLog);
	
		 /**
		  * 重置操作码
		  */
		 String md5CaoZuoMa = PWDGenter.generateKen(caozuoma);
		 userBaseDao.updateUserCaoZuoMa(base.getId(),md5CaoZuoMa);
		 return md5CaoZuoMa;
	}

}
