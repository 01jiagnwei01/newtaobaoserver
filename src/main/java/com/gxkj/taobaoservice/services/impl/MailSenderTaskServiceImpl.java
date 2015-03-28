package com.gxkj.taobaoservice.services.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.Destination;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.MailContentDao;
import com.gxkj.taobaoservice.daos.MailSenderRefAddressListDao;
import com.gxkj.taobaoservice.daos.MailSenderTaskDao;
import com.gxkj.taobaoservice.daos.MailTempleteDao;
import com.gxkj.taobaoservice.dto.MailSenderTaskDTO;
import com.gxkj.taobaoservice.dto.ShouJianRen;
import com.gxkj.taobaoservice.dto.ToolMailDTO;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.MailContent;
import com.gxkj.taobaoservice.entitys.MailSenderRefAddressList;
import com.gxkj.taobaoservice.entitys.MailSenderTask;
import com.gxkj.taobaoservice.entitys.MailTemplete;
import com.gxkj.taobaoservice.enums.MailSenderRefAddressListStatus;
import com.gxkj.taobaoservice.enums.MailSenderStatus;
import com.gxkj.taobaoservice.services.MailSenderTaskService;
@Service
public class MailSenderTaskServiceImpl implements MailSenderTaskService {
	
	@Autowired
	private MailSenderTaskDao mailSenderTaskDao;
	
	@Autowired
	private MailContentDao mailContentDao;
	
	@Autowired
	private MailTempleteDao mailTempleteDao;
	
	@Autowired
	private MailSenderRefAddressListDao mailSenderRefAddressListDao;
	
	
//	@Autowired
//	@Qualifier("jmsTemplate")
//	private JmsTemplate jmsTemplate; 
//	
//	@Autowired  
//	@Qualifier("toolMailQueue")  
//	private Destination toolMailQueueDestination;
	

	public ListPager doPage(int pageno, int pagesize, String title,
			MailSenderStatus status, Integer createUser) throws SQLException {
		 
		return mailSenderTaskDao.doPage( pageno,  pagesize,  title,
				 status,  createUser);
	}

	/**
	 * 增加邮件发送任务 
	 * @throws BusinessException 
	 */
	public MailSenderTask doAddMailSendTask(int contentId, AdminUser adminUser,ShouJianRen[] shouJianRens)
			throws SQLException, BusinessException {
		if(shouJianRens == null || shouJianRens.length == 0){
			throw new BusinessException(BusinessExceptionInfos.MAIL_RECEIVER_CANNOT_BE_NULL);
		}
		MailContent mailContent = (MailContent) mailContentDao.selectById(contentId, MailContent.class);
		if(mailContent == null){
			throw new BusinessException(BusinessExceptionInfos.MAIL_CONTENT_CANNOT_FOUND);
		}
		MailSenderTask task = new MailSenderTask();
		task.setContent(mailContent.getContent());
		task.setCreateTime(new Date());
		task.setCreateUser(adminUser.getId());
		//未发送
		task.setStatus(MailSenderStatus.NOSEND);
		task.setTitle(mailContent.getTitle());
		task.setCreateUserName(adminUser.getName());
		task.setContentId(contentId);
		mailSenderTaskDao.insert(task);
		
		//保存收件人
		for(ShouJianRen item :shouJianRens){
			MailSenderRefAddressList ref = new MailSenderRefAddressList();
			ref.setEmail(item.getEmail());
			ref.setEmailUserName(item.getName());
			ref.setGender(item.getGender());
			ref.setMailAddressListId(item.getId());
			ref.setMailSenderTaskId(task.getId());
			//未发送
			ref.setStatus(MailSenderRefAddressListStatus.NOSEND);
			mailSenderRefAddressListDao.insert(ref);
		}
		return task;
	}

	public MailSenderTask doSendMailTask(int taskId, AdminUser adminUser)
			throws SQLException, BusinessException {
		MailSenderTask entity = (MailSenderTask) mailSenderTaskDao.selectById(taskId, MailSenderTask.class);
		if(entity.getStatus() == MailSenderStatus.ISEXECUTED){
			throw new BusinessException(BusinessExceptionInfos.ONLY_NOSEND_MAIL_CAN_EXECUTE);
		}
		List<MailSenderRefAddressList> receivers = mailSenderRefAddressListDao.getReceiversByTaskId(taskId);
	 
		for(int i=0,l=receivers.size();i<l;i++){
			MailSenderRefAddressList item = receivers.get(i);
			ToolMailDTO mailDto = new ToolMailDTO();
			mailDto.setContent(entity.getContent());
			mailDto.setDbTableId(item.getId());
			mailDto.setEmail(item.getEmail());
			mailDto.setSubject(entity.getTitle());
			
			jmsTemplate.convertAndSend(toolMailQueueDestination, mailDto);
		}
		entity.setStatus(MailSenderStatus.ISEXECUTED);
		mailSenderTaskDao.update(entity);
		return entity;
	}

	 
	public void doDelTask(Integer taskId) throws SQLException,
			BusinessException {
		MailSenderTask task = (MailSenderTask) mailSenderTaskDao.selectById(taskId, MailSenderTask.class);
		if(task.getStatus() != MailSenderStatus.NOSEND){
			throw new BusinessException(BusinessExceptionInfos.ONLY_NOSEND_MAIL_CAN_EXECUTE);
		}
		mailSenderTaskDao.delete(task);
		mailSenderRefAddressListDao.deleReceiverByTaskId( taskId);
		
	}

  
	public MailSenderTaskDTO getMailSenderTaskById(Integer taskId)
			throws SQLException {
		MailSenderTaskDTO dto = new MailSenderTaskDTO();
		MailSenderTask task = (MailSenderTask) mailSenderTaskDao.selectById(taskId, MailSenderTask.class);
		
		if(task != null){
			MailContent mailContent = (MailContent) mailContentDao.selectById(task.getContentId(), MailContent.class);
			dto.setMailContent(mailContent);
			if(mailContent !=null){
				MailTemplete mailTemplete = (MailTemplete) mailTempleteDao.selectById(mailContent.getTempleteId(), MailTemplete.class);
				dto.setMailTemplete(mailTemplete);
			}
		}
		
		
		try {
			BeanUtils.copyProperties(dto, task);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		List<ShouJianRen> shouJianRens = new ArrayList<ShouJianRen>();
		
		List<MailSenderRefAddressList> receivers = mailSenderRefAddressListDao.getReceiversByTaskId(taskId);
		 
		for(int i=0,l=receivers.size();i<l;i++){
			MailSenderRefAddressList item = receivers.get(i);
			ShouJianRen receiver = new ShouJianRen();
			receiver.setEmail(item.getEmail());
			receiver.setGender(item.getGender());
			receiver.setId(item.getMailAddressListId());
			receiver.setName(item.getEmailUserName());
			
			shouJianRens.add(receiver);
		}
		dto.setShouJianRen(shouJianRens);
		return dto;
	}

	
	public MailSenderTask doUpdateMailSendTask(int taskId, int contentId,
			AdminUser adminUser, ShouJianRen[] shoujianrens)
			throws SQLException, BusinessException {
		
		if(shoujianrens == null || shoujianrens.length == 0){
			throw new BusinessException(BusinessExceptionInfos.MAIL_RECEIVER_CANNOT_BE_NULL);
		}
		MailSenderTask task = (MailSenderTask) mailSenderTaskDao.selectById(taskId, MailSenderTask.class);
		if(task.getStatus() != MailSenderStatus.NOSEND){
			throw new BusinessException(BusinessExceptionInfos.ONLY_NOSEND_MAIL_CAN_EXECUTE);
		}
		MailContent mailContent = (MailContent) mailContentDao.selectById(contentId, MailContent.class);
		if(mailContent == null){
			throw new BusinessException(BusinessExceptionInfos.MAIL_CONTENT_CANNOT_FOUND);
		}
		
		task.setContent(mailContent.getContent());
		task.setCreateTime(new Date());
		task.setCreateUser(adminUser.getId());
		//未发送
		task.setStatus(MailSenderStatus.NOSEND);
		task.setTitle(mailContent.getTitle());
		task.setCreateUserName(adminUser.getName());
		mailSenderTaskDao.update(task);
		/**
		 * 删除原有的联系人
		 */
		mailSenderRefAddressListDao.deleReceiverByTaskId( taskId);
		//保存收件人
		for(ShouJianRen item :shoujianrens){
			MailSenderRefAddressList ref = new MailSenderRefAddressList();
			ref.setEmail(item.getEmail());
			ref.setEmailUserName(item.getName());
			ref.setGender(item.getGender());
			ref.setMailAddressListId(item.getId());
			ref.setMailSenderTaskId(task.getId());
			//未发送
			ref.setStatus(MailSenderRefAddressListStatus.NOSEND);
			mailSenderRefAddressListDao.insert(ref);
		}
		return task;
	}

}
