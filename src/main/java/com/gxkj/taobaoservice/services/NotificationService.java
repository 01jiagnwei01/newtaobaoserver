package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import com.gxkj.taobaoservice.entitys.Notification;

public interface NotificationService {
	public int getCount() throws SQLException;
	
	public void saveNotification(Notification entity)throws SQLException;
}
