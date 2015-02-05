package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.AdminRole;

 

public interface RoleService {
	
	public ListPager doPage(int pageno, int pagesize, String name,int status) throws SQLException;
	
	public void deleteEntity(int id)throws SQLException;

	public void addRole(AdminRole role,String[] menuids)throws Exception;

	public void updateRole(AdminRole role,String[] menuids)throws SQLException;
	
	public AdminRole getRoleById(int id)throws SQLException;

	public void updateStatus(String[] ids, int status)throws SQLException;

	

}
