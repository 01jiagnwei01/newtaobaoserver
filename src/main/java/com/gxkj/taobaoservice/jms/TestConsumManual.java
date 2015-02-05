package com.gxkj.taobaoservice.jms;

import java.util.Enumeration;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import com.gxkj.taobaoservice.jms.dto.SerializableEntity;
/**
 * 
 * 重发死亡队列里的消息
 *
 */
@Component
@ManagedResource (objectName = "bean:name=ManualJMS", description = "My Manual JMS", log = true, logFile = "jms.log", currencyTimeLimit = 15, persistPolicy = "OnUpdate", persistPeriod = 200, persistLocation = "jms", persistName = "jms")
public class TestConsumManual {

	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate; 
	
	@Autowired  
	@Qualifier("myqueue")  
	private Destination destination;
	
	@ManagedOperation(description = "brower In DLQ" )
	public 	  Integer browerInfoDLQ(String queueName){
		 if(StringUtils.isEmpty(queueName)){
			 queueName = "ActiveMQ.DLQ";
		 }
		Integer result = jmsTemplate.browse(queueName , new BrowserCallback<Integer>() {
			public Integer doInJms(Session session, QueueBrowser browser) throws JMSException {
				Enumeration<?> messages = browser.getEnumeration();
				int total = 0;
				while (messages.hasMoreElements()) {
					Message message = (Message) messages.nextElement();
					if (message instanceof ObjectMessage) { 
						ObjectMessage objMessage = (ObjectMessage) message;
						Object obj = objMessage.getObject();
						 SerializableEntity entity = (SerializableEntity) obj;  
						 System.out.println("接收到一个ObjectMessage，包含对象为："+entity+",对象内容是："+entity.getMsg());
					
					}
					total++;
				}
				return total;
			}
		});
		return result;
	}
	@ManagedOperation(description = "receive msg In DLQ" )
	public String receiveMsgInDLQ(String queueName) throws JMSException{
		 if(StringUtils.isEmpty(queueName)){
			 queueName = "ActiveMQ.DLQ";
		 }
		 Message message = jmsTemplate.receive(queueName);
		 String result = "";
		 if (message instanceof ObjectMessage) { 
				ObjectMessage objMessage = (ObjectMessage) message;
				 Object obj = objMessage.getObject();
				 SerializableEntity entity = (SerializableEntity) obj;  
				 System.out.println("接收到一个ObjectMessage，包含对象为："+entity+",对象内容是："+entity.getMsg());
				 result = entity.getMsg();
			
			}
		 return result;
	}
}
