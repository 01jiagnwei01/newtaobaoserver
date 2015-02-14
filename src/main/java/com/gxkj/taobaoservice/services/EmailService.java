package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import com.gxkj.taobaoservice.entitys.UserBase;

public interface EmailService {
	
	/**
	 * 邮箱已经被注册过
	 * @param email 
	 * @return
	 * @throws SQLException 
	 */
	public boolean emailIsRegd(String email ) throws SQLException;

	/**
	 * 判断email是否被别人正在使用中
	 * @param userBase
	 * @param tomail
	 * @return
	 * @throws SQLException 
	 */
	public boolean emailIsRegdByOtherPeople(UserBase userBase, String tomail) throws SQLException;

}
