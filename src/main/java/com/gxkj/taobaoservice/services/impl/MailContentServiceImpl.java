package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.gxkj.common.util.ListPager;
import com.gxkj.common.util.SpringValidatorHolder;
import com.gxkj.taobaoservice.daos.AdminUserDao;
import com.gxkj.taobaoservice.daos.MailContentDao;
import com.gxkj.taobaoservice.daos.MailTempleteDao;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.MailContent;
import com.gxkj.taobaoservice.entitys.MailTemplete;
import com.gxkj.taobaoservice.enums.MailContentStatus;
import com.gxkj.taobaoservice.services.MailContentService;
@Service
public class MailContentServiceImpl implements MailContentService {
	
	@Autowired
	private MailContentDao mailContentDao;
	
	@Autowired
	private AdminUserDao adminUserDao;
	
	@Autowired
	private MailTempleteDao mailTempleteDao;
	
	public MailContent doAddMailContent(MailContent entity, AdminUser adminUser)
			throws SQLException, BindException {
		 Date now = new Date();
		 entity.setUpdateTime(now);
		 entity.setUpdateUserId(adminUser.getId());
		 entity.setStatus(MailContentStatus.NORMAL);
		 entity.setUpdateUserName(adminUser.getName());
		 
		SpringValidatorHolder.validate(entity);
		 mailContentDao.insert(entity);
		 
		 MailTemplete mailTemplete = (MailTemplete) mailTempleteDao.selectById(entity.getTempleteId(), MailTemplete.class);
			if(mailTemplete != null){
				entity.setTempleteName(mailTemplete.getTempleteName());
			}
		 
		
		return entity;
	}

	public MailContent doUpdateMailContent(MailContent entity,
			AdminUser adminUser) throws SQLException, BindException {
		Date now = new Date();
		entity.setUpdateTime(now);
		entity.setUpdateUserId(adminUser.getId());
		entity.setStatus(MailContentStatus.NORMAL);
		 entity.setUpdateUserName(adminUser.getName());
		 
		 SpringValidatorHolder.validate(entity);
		 
		mailContentDao.update(entity);
		MailTemplete mailTemplete = (MailTemplete) mailTempleteDao.selectById(entity.getTempleteId(), MailTemplete.class);
		if(mailTemplete != null){
			entity.setTempleteName(mailTemplete.getTempleteName());
		}
		return entity;
	}

	public boolean doDelMailContent(int id, AdminUser adminUser)
			throws SQLException {
		 
		MailContent entity = (MailContent) mailContentDao.selectById(id, MailContent.class);
		if(entity == null) return false;
		Date now = new Date();
		entity.setUpdateTime(now);
		entity.setUpdateUserId(adminUser.getId());
		entity.setStatus(MailContentStatus.DELETE);
		mailContentDao.update(entity);
		
		return true;
	}

	public MailContent getMailContentById(int id) throws SQLException {
		 
		MailContent entity = (MailContent) mailContentDao.selectById(id, MailContent.class);
		AdminUser adminUser = (AdminUser) adminUserDao.selectById(id, AdminUser.class);
		if(adminUser != null){
			entity.setUpdateUserName(adminUser.getName());
		}
		MailTemplete mailTemplete = (MailTemplete) mailTempleteDao.selectById(entity.getTempleteId(), MailTemplete.class);
		if(mailTemplete != null){
			entity.setTempleteName(mailTemplete.getTempleteName());
		}
		return entity;
	}

	 
	public ListPager doPage(int pageno, int pagesize, String title)
			throws SQLException {
		 
		return mailContentDao.doPage( pageno,  pagesize,  title);
	}

}
