package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.AdminRoleDao;
import com.gxkj.taobaoservice.daos.RelRoleMenuDao;
import com.gxkj.taobaoservice.entitys.AdminMenu;
import com.gxkj.taobaoservice.entitys.AdminRole;
import com.gxkj.taobaoservice.entitys.RelRoleMenu;
import com.gxkj.taobaoservice.services.RoleService;

 
 
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private AdminRoleDao roleDao;
	
	@Autowired
	private  RelRoleMenuDao relRoleMenuDao;
	
	public void deleteEntity(int id) throws SQLException {
		roleDao.delete(id);
		relRoleMenuDao.deleteRelationByRoleId(id);
	}

	public ListPager doPage(int pageno, int pagesize, String name, int status)
			throws SQLException {
		return roleDao.doPage(pageno, pagesize, name, status);
	}

	public void addRole(AdminRole entity,String[]menuids) throws Exception {
		roleDao.insert(entity);
		for(int i=0,l=menuids.length;i<l;i++){
			String menuid = menuids[i];
			RelRoleMenu ent = new RelRoleMenu();
			ent.setMenuid(Integer.parseInt(menuid));
			ent.setRoleid(entity.getId());
			relRoleMenuDao.insert(ent);
		} 
		
	}

	public void updateRole(AdminRole entity,String[]menuids) throws SQLException {
		roleDao.update(entity);
		relRoleMenuDao.deleteRelationByRoleId(entity.getId());
		if(menuids!=null)
		for(int i=0,l=menuids.length;i<l;i++){
			String menuid = menuids[i];
			RelRoleMenu ent = new RelRoleMenu();
			ent.setMenuid(Integer.parseInt(menuid));
			ent.setRoleid(entity.getId());
			relRoleMenuDao.insert(ent);
		} 
		
	}

	 
	public AdminRole getRoleById(int id) throws SQLException {
		AdminRole entity = (AdminRole) roleDao.selectById(id, AdminRole.class);
		if(entity !=null){
			List<AdminMenu> menus = relRoleMenuDao.getMenuByRoleId(id);
			entity.setRelMenus(menus);
		}
		return entity;
	}

	 
	public void updateStatus(String[] ids, int status) throws SQLException {
		 
		for(int i=0,l=ids.length;i<l;i++){
			String id = ids[i];
			roleDao.updateStatus(Integer.parseInt(id),status);
		}
	}

}
