package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.gxkj.common.util.ListPager;
import com.gxkj.common.util.SpringValidatorHolder;
import com.gxkj.taobaoservice.daos.MailTempleteDao;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.MailTemplete;
import com.gxkj.taobaoservice.enums.MailTempleteStatus;
import com.gxkj.taobaoservice.services.MailTempleteService;
@Service
public class MailTempleteServiceImpl implements MailTempleteService {

	@Autowired
	private MailTempleteDao mailTempleteDao;

	public MailTemplete doAddMailTemplete(MailTemplete entity,AdminUser adminUser) throws SQLException, BindException {
		Date now = new Date();
		entity.setUpdateTime(now);
		entity.setUpdateUserId(adminUser.getId());
		entity.setStatus(MailTempleteStatus.NORMAL);
		SpringValidatorHolder.validate(entity);
		mailTempleteDao.insert(entity);
		return entity;
	}

	public MailTemplete doUpdateMailTemplete(MailTemplete entity,AdminUser adminUser) throws SQLException, BindException {
		Date now = new Date();
		entity.setUpdateTime(now);
		entity.setUpdateUserId(adminUser.getId());
		entity.setStatus(MailTempleteStatus.NORMAL);
		
		SpringValidatorHolder.validate(entity);
		mailTempleteDao.update(entity);
		return entity;
	}

	 
	public boolean doDelMailTemplete(int id,AdminUser adminUser) throws SQLException {
		MailTemplete entity = (MailTemplete) mailTempleteDao.selectById(id, MailTemplete.class);
		if(entity == null) return false;
		Date now = new Date();
		entity.setUpdateTime(now);
		entity.setUpdateUserId(adminUser.getId());
		entity.setStatus(MailTempleteStatus.DELETE);
		
		mailTempleteDao.update(entity);
		return true;
	}

	public MailTemplete getMailTempleteById(int id) throws SQLException {
		MailTemplete entity = (MailTemplete) mailTempleteDao.selectById(id, MailTemplete.class);
		return entity;
	}

 
	public ListPager doPage(int pageno, int pagesize, String templeteName)
			 throws SQLException{
	 
		return mailTempleteDao.doPage(  pageno,   pagesize,   templeteName);
	}
}
