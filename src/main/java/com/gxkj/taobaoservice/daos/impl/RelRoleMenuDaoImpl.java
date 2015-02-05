package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.taobaoservice.daos.RelRoleMenuDao;
import com.gxkj.taobaoservice.entitys.AdminMenu;

 
 
@Repository
public class RelRoleMenuDaoImpl extends BaseDAOImpl implements RelRoleMenuDao {

	public void deleteRelationByRoleId(int roleid) throws SQLException {
		 String sql = String.format("delete from rel_role_menu where roleid = %d", roleid);
		 this.executeUpdate(sql,null);
		
	}

	@SuppressWarnings("unchecked")
	public List<AdminMenu> getMenuByRoleId(int roleid) throws SQLException {
		 String sql =String.format("select menu.* from admin_menu menu,rel_role_menu rel where rel.roleid=%d and rel.menuid = menu.id ", roleid);
	 
		 return ((List<AdminMenu>) this.selectBySQL(sql, AdminMenu.class));
	}

	 

}
