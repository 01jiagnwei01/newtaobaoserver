package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
import com.gxkj.common.util.SystemGlobals;
import com.gxkj.taobaoservice.daos.RegLogDao;
import com.gxkj.taobaoservice.entitys.RegLog;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.RegLogTranType;
import com.gxkj.taobaoservice.enums.RegLogTypes;
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
	private RegLogDao regLogDao;
	
	@Autowired
	private JavaMailSenderImpl javaMailSender;
	
	public void doSendMail(UserBase userBase,String tomail) throws BusinessException, SQLException, BindException, MessagingException {
		
		boolean isReged = emailService.emailIsRegdByOtherPeople(userBase,tomail);
		if(isReged){
			throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_REGED,"email");
		}
		regLogDao.updateEmaiToNoEnable(tomail);
		
		/**
		 * 保存修改的注册码
		 */
		String code = RegexUtils.getRandomNum(6)+"";
		Date now = new Date();
		RegLog regLog = new RegLog();
		regLog.setCode(code);
		regLog.setCreateDime(now);
		regLog.setType(RegLogTypes.email);
		regLog.setValue(tomail);
		regLog.setEnabled(true);
		regLog.setTranType(RegLogTranType.UPDATE_BIND_EMAIL);
		int validTimeLeng = SystemGlobals.getIntPreference("reg.code.valid.time", 5);
		Date expTime = DateUtils.addMilliseconds(now, validTimeLeng);
		regLog.setExpTime(expTime);
		regLogDao.insert(regLog);
		
		
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

}
