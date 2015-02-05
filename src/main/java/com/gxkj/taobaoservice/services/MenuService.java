package com.gxkj.taobaoservice.services;


import java.util.List;

import com.gxkj.taobaoservice.entitys.AdminMenu;
 
public interface MenuService {

	public List<AdminMenu> getAllMenu()throws Exception;

	/**
	 * 增加菜单
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public AdminMenu addMenu(AdminMenu entity)throws Exception;

	/**
	 * 修改菜单 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public AdminMenu updateMenu(AdminMenu entity)throws Exception;

	/**
	 * 删除菜单 
	 * @param id
	 * @throws Exception
	 */
	public void deleteMenuById(int id)throws Exception;
}
