package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.taobaoservice.entitys.AdminRole;

public interface RelAdminUserRoleDao extends BaseDAO {
	
	public AdminRole getRoleByUserId(int userId)throws SQLException;
	
	public void delRelationByUserId(int userId) throws SQLException  ;
	

}
