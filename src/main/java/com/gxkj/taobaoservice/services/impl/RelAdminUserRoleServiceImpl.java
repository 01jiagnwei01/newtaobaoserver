package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.taobaoservice.daos.RelAdminUserRoleDao;
import com.gxkj.taobaoservice.entitys.AdminRole;
import com.gxkj.taobaoservice.services.RelAdminUserRoleService;
 
@Service
public class RelAdminUserRoleServiceImpl implements RelAdminUserRoleService {

	@Autowired
	private RelAdminUserRoleDao relAdminUserRoleDao;
	
	public void delRelationByUserId(int userId) throws SQLException {
		relAdminUserRoleDao.delRelationByUserId(userId);

	}

	public AdminRole getRoleByUserId(int userId) throws SQLException {
		 
		return relAdminUserRoleDao.getRoleByUserId(userId);
	}

}
