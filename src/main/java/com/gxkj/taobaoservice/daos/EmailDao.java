package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import com.gxkj.common.dao.BaseDAO;

public interface EmailDao extends BaseDAO {

	/**
	 * 
	 * @param email
	 * @return
	 * @throws SQLException 
	 */
	public boolean emailIsRegd(String email ) throws SQLException;
}
