package com.gxkj.taobaoservice.mail;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.SystemGlobals;
import com.gxkj.taobaoservice.dto.ToolMailDTO;
import com.gxkj.taobaoservice.entitys.RegLog;
import com.gxkj.taobaoservice.enums.RegLogTranType;
import com.gxkj.taobaoservice.enums.RegLogTypes;
import com.gxkj.taobaoservice.services.RegLogService;
import com.gxkj.taobaoservice.util.RegexUtils;

@Service("demoMailService")
public class MailSenderService {
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSenderImpl javaMailSender;
	
	@Autowired
	@Qualifier("templateMailMessage")
	private SimpleMailMessage templateMailMessage;
	
//	@Autowired
//	private EmailServiceImpl emailServiceImpl;
	
	@Autowired
	private RegLogService  regLogService ;
	
	
	private static final String ENCODING = "utf-8";
	
	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void setSimpleMailMessage(SimpleMailMessage templateMailMessage) {
		this.templateMailMessage = templateMailMessage;
	}
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
 
	public void sendMail(String from, String to, String subject, String msg) {
 
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
 
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(msg);
		mailSender.send(simpleMailMessage);	
	}
	public void sendMaiForReg(String email) throws SQLException, BusinessException, BindException {
		 
//		boolean isReged = false;//emailServiceImpl.emailIsRegd(email);
//		if(isReged){
//			throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_REGED);
//		}
//		String code = RegexUtils.getRandomNum(6)+"";
//		Date now = new Date();
//		RegLog regLog = new RegLog();
//		regLog.setCode(code);
//		regLog.setCreateDime(now);
//		regLog.setType(RegLogTypes.email);
//		regLog.setTranType(RegLogTranType.REG);
//		int validTimeLeng = SystemGlobals.getIntPreference("reg.code.valid.time", 5);
//		Date expTime = DateUtils.addMilliseconds(now, validTimeLeng);
//		regLog.setExpTime(expTime);
//		/**
//		 * 保存注册信息
//		 */
//		regLogService.addRegLog(regLog);
//		
//		/**
//		 * 发送邮件
//		 */
//		SimpleMailMessage simpleMailMessage = new SimpleMailMessage(templateMailMessage);
//		simpleMailMessage.setTo(email);
//		simpleMailMessage.setSubject("谷谷道场注册码");
//		simpleMailMessage.setText(String.format("谷谷道场注册码:[%s]", code));
//		mailSender.send(simpleMailMessage);	
	} 
	/***
	 * 邮件营销发送邮件
	 * @param toolMailDTO
	 */
	public void sendMailWithToolMailDTO(ToolMailDTO toolMailDTO){
		 MimeMessage msg = javaMailSender.createMimeMessage();
		 try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, ENCODING);
			helper.setFrom(templateMailMessage.getFrom());
			helper.setTo(toolMailDTO.getEmail());
			helper.setSubject(toolMailDTO.getSubject());
			String htmlTemplete = "<!DOCTYPE html><html lang=\"zh\"><head><meta http-equiv=\"X-UA-Compatible\" content=\"IE=EmulateIE8\" content=\"ie=edge\"/><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body >${content}</body></html>";
			String content = htmlTemplete.replace("${content}", toolMailDTO.getContent());
			helper.setText(content, true);
			javaMailSender.send(msg);
			
		} catch (MessagingException e) {
			 
			e.printStackTrace();
		}
	}
	
	public void sendMailWithTemplate(String dear, String content) {			 
		   SimpleMailMessage message = new SimpleMailMessage(templateMailMessage);
	 	   message.setText(String.format( templateMailMessage.getText(), dear, content));
	 	   mailSender.send(message);
	}
	
	public void sendMailWithAttachment(String dear, String content) {
		 
		   MimeMessage message = javaMailSender.createMimeMessage();
	 
		   try{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
	 
			helper.setFrom(templateMailMessage.getFrom());
			helper.setTo(templateMailMessage.getTo());
			helper.setSubject(templateMailMessage.getSubject());
			helper.setText(String.format(
					templateMailMessage.getText(), dear, content));
	 
			FileSystemResource file = new FileSystemResource("C:\\hello\\java.txt");
			helper.addAttachment(file.getFilename(), file);
	 
		     }catch (MessagingException e) {
			throw new MailParseException(e);
		     }
		   javaMailSender.send(message);
	         }
	
	/*
	 * Embedd inline message in mailbody
	 */
	public void sendMailWithInlineImage() throws MessagingException{
		//javaMailSender =  new JavaMailSenderImpl();
		 MimeMessage message = javaMailSender.createMimeMessage();
		// use the true flag to indicate you need a multipart message
		 MimeMessageHelper helper = new MimeMessageHelper(message, true);
		 helper.setTo("janakisrinivaschowdary@yahoo.co.in");
		 String cid = ContentIdGenerator.getContentId();
		// use the true flag to indicate the text included is HTML
		 helper.setText(
		   "<html><body><img src='cid:"+cid+"'></body></html>",true);
		// let's include the infamous windows Sample file (this time copied to c:/)
		 FileSystemResource res = new FileSystemResource(new File("d:/Penguins.jpg"));
		 helper.addInline(cid, res);
		 javaMailSender.send(message);
	}

}
