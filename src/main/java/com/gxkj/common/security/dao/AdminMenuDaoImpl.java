package com.gxkj.common.security.dao;

import java.util.ArrayList;
import java.util.List;

import com.gxkj.common.security.entitys.AdminMenu;


 

public class AdminMenuDaoImpl   {

	public static List<AdminMenu> menus = new ArrayList<AdminMenu>();
	
	static{
		/**
		 * 初始化可访问资源
		 */
		AdminMenu menu1 = new AdminMenu();
		menu1.setId(1);
		menu1.setName("Admin用户列表");
		menu1.setPath("/admin/adminuser/list");
		menu1.setSpringSecurityRole("admin_adminuser_list");
		
		AdminMenu menu2 = new AdminMenu();
		menu2.setId(2);
		menu2.setName("Admin用户详情页");
		menu2.setPath("/admin/adminuser/get/*");
		menu2.setSpringSecurityRole("admin_adminuser_get_id");
		
		AdminMenu menu3 = new AdminMenu();
		menu3.setId(3);
		menu3.setName("登陆页面");
		menu3.setPath("/admin/login");
		menu3.setSpringSecurityRole("anonymous");
		
		
		menus.add(menu1);
		menus.add(menu2);
	}
	public List<AdminMenu> getAllAdminMenu() {
		 
		return AdminMenuDaoImpl.menus;
	}

}
