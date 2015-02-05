package com.gxkj.taobaoservice.util.maildemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class MAIN implements Observer {

	 
	public void update(Observable o, Object arg) {
		// 发送邮件
        SimpleMailSender sms = MailSenderFactory
            .getSender(MailSenderType.SERVICE);
        List<String> recipients = new ArrayList<String>();
        recipients.add("346745719@qq.com");
        //recipients.add("invisible@gmail.com");
        try {
            for (String recipient : recipients) {
            sms.send(recipient, "价格变动", "您关注的物品 降价了， 降幅达"
                + 15 + "元人民币。赶快购物吧。");
            }
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } 

	}
	public static void main(String[] args) {
		new MAIN().update(null,null);
	}

}
