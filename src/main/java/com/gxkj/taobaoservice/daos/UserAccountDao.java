package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.taobaoservice.entitys.UserAccount;

public interface UserAccountDao  extends BaseDAO{

	/**
	 * 根据用户ID查询用户帐务信息
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	UserAccount getUserAccountByUserId(Integer id) throws SQLException;



}
