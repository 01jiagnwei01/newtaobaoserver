package com.gxkj.taobaoservice.util.mail;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.gxkj.common.util.Encrypt;
import com.gxkj.common.util.SystemGlobals;
import com.gxkj.taobaoservice.constants.EncryptKey;
import com.gxkj.taobaoservice.util.maildemo2.SendMixedMail;

public class MailSender {
	//以下属性根据自己情况设置.
    private static String protocol="smtp";
    private static String from="01jiangwei01@163.com";
    public static String body="<html><body><a href='{1}' target='_blank'>激活帐户</a><br/><br/><br/><br/><img src='cid:img1'/><img src='cid:img2'/></body></html>";
    private static String server="smtp.163.com";
    private static String username="01jiangwei01";//from mail name
    private static String password="xubaoyong200096";//from mail password
    
    private Session session;
    
     
    public static void main(String[] args) throws AddressException, MessagingException {
    	SendMixedMail sender = new SendMixedMail();
    	sender.sendEmail("346745719@qq.com", "第二次测试html", new Date(), SendMixedMail.body);
	}
    public void regSendEmail(String to,String subject,Date sentDate,String data) throws AddressException, MessagingException{
    	String system_index = SystemGlobals.getPreference("system.index.url");
    	
    	Encrypt encrypt = new Encrypt();
		encrypt.setKey(EncryptKey.encryptKeyString); 
		encrypt.setEncString(data);
		data=encrypt.getStrMi();
		
    	String content =  String.format("%s/email_active?data=%s", system_index,data);
    	content = this.body.replace("{1}",content);
    	this.sendEmail(to, subject, sentDate, content);
    }
    
    
    public Session getSession(){
    	if(session == null){
    		Properties prop=new Properties();
            prop.setProperty("mail.transport.protocol",protocol);
            prop.setProperty("mail.smtp.auth","true");
        	session=Session.getInstance(prop,new Authenticator(){//用户连接认证
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username,password);
                }
            });
    	}
    	return session;
    }
    
    /**
     * 创建图片
     * @param file
     * @param name
     * @return
     * @throws MessagingException
     */
    private static BodyPart createImagePart(File file,String name) throws MessagingException {
        MimeBodyPart image=new MimeBodyPart();
        
        DataSource ds=new FileDataSource(file);
        image.setDataHandler(new DataHandler(ds));
        image.setFileName(name);
        image.setContentID(name);
        
        return image;
    }
  
    public  void sendEmail(String to,String subject,Date sentDate,String htmlContent) throws AddressException, MessagingException{
   	 session = this.getSession();
   	 MimeMessage message=new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
        message.setSubject(subject);
        message.setSentDate(sentDate);
        
        /**
         * 创建复杂格式
         */
        Multipart multi=new MimeMultipart("mixed");//混合MIME消息
        BodyPart content=new MimeBodyPart();
        Multipart relate=new MimeMultipart("related");//组合MIME消息
        
        BodyPart html=new MimeBodyPart();
        html.setContent(htmlContent, "text/html;charset=UTF-8");
        relate.addBodyPart(html);
         relate.addBodyPart(createImagePart(new File("C:/Users/Public/Pictures/Sample Pictures/a.jpg"), "img1"));//嵌入图片
//        relate.addBodyPart(createImagePart(new File("D:/image2.jpg"), "img2"));
        
        content.setContent(relate);
        multi.addBodyPart(content);
        message.setContent(multi);//发送复杂文本消息
        
        //message.saveChanges();//保存消息
        
        Transport trans=session.getTransport();
        trans.connect(server,username,password);
        trans.sendMessage(message,message.getRecipients(Message.RecipientType.TO));//发送
        trans.close();
        
   }
	public void findBackPasswordSendEmail(String to, String subject,
			Date sentDate, String data) throws AddressException, MessagingException {
			
		String system_index = SystemGlobals.getPreference("system.index.url");
    	
    	Encrypt encrypt = new Encrypt();
		encrypt.setKey(EncryptKey.encryptKeyString); 
		encrypt.setEncString(data);
		data=encrypt.getStrMi();
		
    	String content =  String.format("%s/findbackpassword?data=%s", system_index,data);
    	content = this.body.replace("{1}",content);
    	this.sendEmail(to, subject, sentDate, content);
		
	}
     
}
