package com.gxkj.taobaoservice.services;

import java.util.List;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.AdminUser;

 

public interface AdminUserService {
	

	public ListPager doPage(int pageno, int pagesize, String name ,int status) throws Exception;
	
	public void updateStatus(int status,int id)throws Exception;
	
	public AdminUser addAdminUser(AdminUser user)throws Exception;
	
	public AdminUser updateAdminUser(AdminUser user)throws Exception;
	
	public List<AdminUser> getAdminUserByName(String name) throws Exception;

	/***
	 * 重置密码
	 * @param id
	 * @param generateKen
	 * @throws Exception
	 */
	public void updatePasswordById(int id, String password)throws Exception;

}
