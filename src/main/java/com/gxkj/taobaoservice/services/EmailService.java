package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

public interface EmailService {
	
	/**
	 * 邮箱已经被注册过
	 * @param email 
	 * @return
	 * @throws SQLException 
	 */
	public boolean emailIsRegd(String email ) throws SQLException;

}
