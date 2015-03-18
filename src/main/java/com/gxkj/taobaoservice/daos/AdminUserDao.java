package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;
import java.util.List;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.AdminUser;

public interface AdminUserDao extends BaseDAO {

	public ListPager doPage(int pageno, int pagesize, String name ,int status) throws SQLException;
	
	public void updateStatus(int status,int id)throws SQLException;
	
	public List<AdminUser> getAdminUserByName(String name) throws SQLException;
	
	/**
	 * 根据用户名查找用户
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public AdminUser getOneAdminUserByName(String name)throws SQLException;

	/**
	 * 重置密码
	 * @param id
	 * @param password
	 * @throws Exception
	 */
	public void updatePasswordById(int id, String password)throws SQLException;
	
}
