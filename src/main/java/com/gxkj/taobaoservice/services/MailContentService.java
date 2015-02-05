package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import org.springframework.validation.BindException;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.MailContent;

public interface MailContentService {

	/**
	 * 增加邮件内容
	 * @param entity
	 * @param adminUser
	 * @return
	 * @throws SQLException
	 * @throws BindException 
	 */
	public MailContent doAddMailContent(MailContent entity,AdminUser adminUser) throws SQLException, BindException;
	
	/**
	 * 修改邮件内容
	 * @param entity
	 * @param adminUser
	 * @return
	 * @throws SQLException
	 * @throws BindException 
	 */
	public MailContent doUpdateMailContent(MailContent entity,AdminUser adminUser) throws SQLException, BindException;
	
	/**
	 * 删除邮件内容
	 * @param id
	 * @param adminUser
	 * @return
	 * @throws SQLException
	 */
	public boolean doDelMailContent(int id,AdminUser adminUser) throws SQLException;
	
	/***
	 * 根据邮件ID查询邮件内容
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public MailContent getMailContentById (int id)throws SQLException;
	/**
	 * 
	 * @param pageno
	 * @param pagesize
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	
	public ListPager doPage(int pageno, int pagesize, String title ) throws SQLException;
}
