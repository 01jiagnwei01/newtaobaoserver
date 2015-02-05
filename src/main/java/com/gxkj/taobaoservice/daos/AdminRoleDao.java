package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;


public interface AdminRoleDao extends BaseDAO {
		
	public ListPager doPage(int pageno, int pagesize, String name,int status) throws SQLException;
	
	public void deleteEntity(int id)throws SQLException;

	public void updateStatus(int parseInt, int status)throws SQLException;
}
