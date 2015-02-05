package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;
import java.util.List;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.taobaoservice.entitys.AdminMenu;


public interface RelRoleMenuDao extends BaseDAO {
	
	public List<AdminMenu> getMenuByRoleId(int roleid)throws SQLException ;
	
	public void deleteRelationByRoleId(int roleid)throws SQLException ;

}
