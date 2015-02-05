package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.MailSenderTaskDao;
import com.gxkj.taobaoservice.dto.MailSenderTaskDTO;
import com.gxkj.taobaoservice.dto.ShouJianRen;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.MailSenderTask;
import com.gxkj.taobaoservice.enums.MailSenderStatus;

public interface MailSenderTaskService {

	ListPager doPage(int pageno, int pagesize, String title,
			MailSenderStatus status, Integer createUser) throws SQLException;

	/**
	 * 新增邮件发送任务
	 * @param contentId
	 * @param adminUser
	 * @param shouJianRens
	 * @return
	 * @throws BusinessException 
	 */
	MailSenderTask doAddMailSendTask(int contentId, AdminUser adminUser,ShouJianRen[] shouJianRens)throws SQLException, BusinessException;

	/**
	 * 发送邮件
	 * @param taskId
	 * @param adminUser
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	MailSenderTask doSendMailTask(int taskId, AdminUser adminUser)throws SQLException, BusinessException;

	/**
	 * 删除某个任务
	 * @param taskId
	 * @throws SQLException
	 * @throws BusinessException
	 */
	void doDelTask(Integer taskId)throws SQLException, BusinessException;

	/**
	 * 根据ID查询任务信息
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	MailSenderTaskDTO getMailSenderTaskById(Integer taskId)throws SQLException;

	/**
	 * 修改某个任务
	 * @param taskId
	 * @param contentId
	 * @param adminUser
	 * @param shoujianrens
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	MailSenderTask doUpdateMailSendTask(int taskId, int contentId,
			AdminUser adminUser, ShouJianRen[] shoujianrens)throws SQLException, BusinessException;

	 

}
