package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import com.gxkj.common.dao.BaseDAO;

public interface NotificationDao extends BaseDAO {
	
	public int getCount() throws SQLException;

}
