package com.gxkj.taobaoservice.jms.listeners;

import java.sql.SQLException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gxkj.taobaoservice.daos.MailSenderRefAddressListDao;
import com.gxkj.taobaoservice.dto.ToolMailDTO;
import com.gxkj.taobaoservice.entitys.MailSenderRefAddressList;
import com.gxkj.taobaoservice.enums.MailSenderRefAddressListStatus;
import com.gxkj.taobaoservice.mail.MailSenderService;
@Component
public class ToolMailMessageListener  implements MessageListener {

	@Autowired
	private MailSenderRefAddressListDao mailSenderRefAddressListDao;
	
	@Autowired
	private MailSenderService mailSenderService;
	
	public void onMessage(Message message) {
		try {
			 Integer deliveryCount  = message.getIntProperty("JMSXDeliveryCount");
			 System.out.println("加载次数deliveryCount为"+deliveryCount);
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 ObjectMessage objMessage = (ObjectMessage) message;
		 Object obj;
		 ToolMailDTO toolMailDTO = null;
		try {
			obj = objMessage.getObject();
			toolMailDTO = (ToolMailDTO) obj; 
		} catch (JMSException e) {
			e.printStackTrace();
		}
		MailSenderRefAddressList dbEntity =  null;
		try {
			 dbEntity =  (MailSenderRefAddressList) mailSenderRefAddressListDao.selectById(toolMailDTO.getDbTableId(), MailSenderRefAddressList.class);
			 if(dbEntity.getStatus() == MailSenderRefAddressListStatus.SUCCESS ){
				 return;
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/***
		 * 
		 * 执行发送邮件代码
		 * 
		 */
		mailSenderService.sendMailWithToolMailDTO(toolMailDTO);
		
		dbEntity.setStatus(MailSenderRefAddressListStatus.SUCCESS);
		try {
			mailSenderRefAddressListDao.update(dbEntity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  
		
	}

}
