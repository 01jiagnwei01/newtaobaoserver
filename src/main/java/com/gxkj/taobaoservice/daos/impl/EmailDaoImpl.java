package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.taobaoservice.daos.EmailDao;
@Repository
public class EmailDaoImpl extends BaseDAOImpl implements EmailDao {

	private static final String emailIsReqSql = "select id from user_link where link_type = 'EMAIL' and  link_value = ?";

	 
	public boolean emailIsRegd(String email) throws SQLException {
		Integer  result = (Integer) this.selectOneBySQL(emailIsReqSql, new Object[]{email}, Integer.class);
		if (result == null) return false;
		return result.compareTo(new Integer("0"))>0?true: false;
	}
}
