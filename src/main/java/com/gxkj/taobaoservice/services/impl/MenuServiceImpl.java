package com.gxkj.taobaoservice.services.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.taobaoservice.daos.AdminMenuDao;
import com.gxkj.taobaoservice.entitys.AdminMenu;
import com.gxkj.taobaoservice.services.MenuService;
 

 
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private AdminMenuDao menuDao;
	
	public List<AdminMenu> getAllMenu() throws Exception {
		return menuDao.getAllMenu();
	}

	public AdminMenu addMenu(AdminMenu entity) throws Exception {
		if(entity.getIsbutton()== 1){
			entity.setId(0);
			List <AdminMenu> menus = menuDao.getMenuWithSameBtnflag(entity);
			if(CollectionUtils.isNotEmpty(menus)){
				return null;
			}
		}
		menuDao.insert(entity);
		return entity;
	}

	public AdminMenu updateMenu(AdminMenu entity) throws Exception {
		if(entity.getIsbutton()== 1){
			List <AdminMenu> menus = menuDao.getMenuWithSameBtnflag(entity);
			if(CollectionUtils.isNotEmpty(menus)){
				return null;
			}
		}
		menuDao.update(entity);
		return entity;
	}

	public void deleteMenuById(int id) throws Exception {
		
		menuDao.deleteById(id, AdminMenu.class);
		
	}

}
