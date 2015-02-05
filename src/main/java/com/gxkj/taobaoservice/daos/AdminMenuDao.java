package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;
import java.util.List;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.taobaoservice.entitys.AdminMenu;


public interface AdminMenuDao extends BaseDAO {
	
	public List<AdminMenu> getAllMenu() throws SQLException;

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws SQLException
	 */
	public List<AdminMenu> getMenuWithSameBtnflag(AdminMenu entity) throws SQLException;
	
	 

}
