package com.gxkj.common.security.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gxkj.common.security.entitys.AdminMenu;
import com.gxkj.common.security.entitys.AdminRole;
import com.gxkj.common.security.entitys.RelAdminRoleMenu;
import com.gxkj.common.security.entitys.RelUserRole;

 

public class RelUserRoleDaoImpl  {
	
	private static List<RelUserRole> rels = new ArrayList<RelUserRole>();
	
	private static List<AdminRole> roles = new ArrayList<AdminRole>();
	
	
 
	private static List<RelAdminRoleMenu> relRoleMenus = new ArrayList<RelAdminRoleMenu>();
	
	private static  Map<String ,AdminRole> roleMap = new HashMap<String ,AdminRole>();
	
	private static  Map<String ,AdminMenu> menuMap = new HashMap<String ,AdminMenu>();
	
	static{
		/**
		 * 初始化系统角色
		 */
		AdminRole role1 = new AdminRole();
		role1.setId(1);
		role1.setRoleName("系统管理员");
		
		AdminRole role2 = new AdminRole();
		role2.setId(2);
		role2.setRoleName("普通用户");
		
		roles.add(role1);
		roles.add(role2);
		
		/**
		 * 初始化角色用户关系
		 */
		RelUserRole r1 = new RelUserRole();
		r1.setId(1);
		r1.setRoleId(1);
		r1.setUserId(1);
		
		RelUserRole r2 = new RelUserRole();
		r2.setId(1);
		r2.setRoleId(1);
		r2.setUserId(1);
		
		RelUserRole r3 = new RelUserRole();
		r3.setId(1);
		r3.setRoleId(1);
		r3.setUserId(1);
		
		rels.add(r1);
		rels.add(r2);
		rels.add(r3);
		
		
		
		/**
		 * 初始化角色菜单关系
		 */
		RelAdminRoleMenu rm1 = new RelAdminRoleMenu();
		rm1.setId(1);
		rm1.setMenuid(1);
		rm1.setRoleId(1);
		
		RelAdminRoleMenu rm2 = new RelAdminRoleMenu();
		rm2.setId(2);
		rm2.setMenuid(2);
		rm2.setRoleId(1);
		
		relRoleMenus.add(rm1);
		relRoleMenus.add(rm2);
		
		for(AdminRole role :roles){
			roleMap.put("role_"+role.getId(), role);
		}
		
		for(AdminMenu menu :AdminMenuDaoImpl.menus){
			menuMap.put("menu_"+menu.getId(), menu);
		}
		
		
		
		
	}
	/**
	 * 根据用户ID,查询用户可以访问的资源
	 */
	public List<AdminMenu> getAdminMenuByUserId(int userId) {
		
		 List<AdminRole> rels_temp = new ArrayList<AdminRole>();
		 for(RelUserRole rel :rels){
			 if(rel.getUserId() == userId ){
				 rels_temp.add(roleMap.get("role_"+rel.getRoleId()));
			 }
		 }
		 List<AdminMenu>  menus_temp = new ArrayList<AdminMenu>();
		 for(AdminRole role :rels_temp){
			 for(RelAdminRoleMenu menuRel : relRoleMenus){
				 if(menuRel.getRoleId() == role.getId()){
					 if(!menus_temp.contains( menuMap.get("menu_"+menuRel.getMenuid())))
					 {
						 menus_temp.add(menuMap.get("menu_"+menuRel.getMenuid()));
					 }
						
				 }
			 }
		 }
		
		return menus_temp;
	}

}
