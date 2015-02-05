package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.taobaoservice.daos.NotificationDao;
import com.gxkj.taobaoservice.entitys.Notification;
import com.gxkj.taobaoservice.services.NotificationService;
@Service("notificationService")
public class NotificationServiceImpl implements  
		NotificationService {

	@Autowired
	private NotificationDao notificationDao;
	
	
	public int getCount() throws SQLException{
		return this.notificationDao.getCount();
	}


	public void saveNotification(Notification entity) throws SQLException {
		notificationDao.insert(entity);
	}
	 

}
