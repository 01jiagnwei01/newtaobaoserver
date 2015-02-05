package com.gxkj.taobaoservice.daos.impl;

import java.math.BigInteger;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.taobaoservice.daos.NotificationDao;
@Repository
public class NotificationDaoImpl extends BaseDAOImpl implements NotificationDao {

	 
	public int getCount() throws SQLException {
		String sql = "select  count(id) from notifications "; 
		BigInteger resutl = (BigInteger) this.selectOneBySQL(sql, BigInteger.class);
		return resutl.intValue();
	}

}
