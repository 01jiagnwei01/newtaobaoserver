package com.gxkj.taobaoservice.services;


import java.sql.SQLException;

import com.gxkj.taobaoservice.entitys.AdminRole;

 


public interface RelAdminUserRoleService {
	
	public AdminRole getRoleByUserId(int userId)throws SQLException;
	
	public void delRelationByUserId(int userId) throws SQLException  ;

}
