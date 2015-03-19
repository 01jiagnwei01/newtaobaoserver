package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.common.util.PWDGenter;
import com.gxkj.common.util.SystemGlobals;
import com.gxkj.taobaoservice.daos.AdminUserDao;
import com.gxkj.taobaoservice.daos.RelAdminUserRoleDao;
import com.gxkj.taobaoservice.entitys.AdminRole;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.RelAdminUserRole;
import com.gxkj.taobaoservice.services.AdminUserService;


@Service
public class AdminUserServiceImpl implements AdminUserService {
	
	@Autowired
	private AdminUserDao adminUserDao;
	
	@Autowired
	private RelAdminUserRoleDao relAdminUserRoleDao;
	
	public AdminUser addAdminUser(AdminUser user) throws SQLException, BusinessException  {
		
		String uname = user.getName();
		if(StringUtils.isBlank(uname)){
			throw new BusinessException(BusinessExceptionInfos.USER_NAME_IS_BLANK,"username");
		}
		//判断登陆名是否重复
		AdminUser dbAdminUser = adminUserDao.getOneAdminUserByName(uname);
		if(dbAdminUser!=null){
			throw new BusinessException(BusinessExceptionInfos.USER_NAME_IS_REGED,"username");
		}
		String password = SystemGlobals.getPreference("admin.user.init.password");
		password = PWDGenter.generateKen(password);
		user.setPassword(password);
		adminUserDao.insert(user);
		
		if(user.getRole()!=null){
			RelAdminUserRole rel = new RelAdminUserRole();
			rel.setAdminRoleId(user.getRole().getId());
			rel.setAdminUserId(user.getId());
			relAdminUserRoleDao.insert(rel);
		}
		
		return user;
	}

	@SuppressWarnings("unchecked")
	public ListPager doPage(int pageno, int pagesize, String name, int status)
			throws Exception {
		ListPager pager =   adminUserDao.doPage(pageno, pagesize, name, status);
		/**查找关联关系 */
		if(CollectionUtils.isNotEmpty(pager.getPageData())){
			List<AdminUser>  data =  (List<AdminUser>) pager.getPageData();
			List<AdminUser>  newdata =  new ArrayList<AdminUser>();
			for(AdminUser user:data){
				AdminRole role =	relAdminUserRoleDao.getRoleByUserId(user.getId());
				user.setRole(role);
				newdata.add(user);
			}
			pager.setPageData(newdata);
		}
		return pager;
	}

	public AdminUser updateAdminUser(AdminUser user) throws SQLException, BusinessException  {
		String uname = user.getName();
		if(StringUtils.isBlank(uname)){
			throw new BusinessException(BusinessExceptionInfos.USER_NAME_IS_BLANK,"username");
		}
//		判断登陆名是否重复
		AdminUser dbAdminUser = adminUserDao.getOneAdminUserByName(uname);
		if(dbAdminUser!=null && dbAdminUser.getId().equals(user.getId())==false){
			throw new BusinessException(BusinessExceptionInfos.USER_NAME_IS_REGED,"username");
		}
		
		 dbAdminUser = (AdminUser) adminUserDao.selectById(user.getId(), AdminUser.class);
		user.setPassword(dbAdminUser.getPassword());
		adminUserDao.getSessionFactory().getCurrentSession().clear();
 
		
		adminUserDao.update(user);
		//删除过去的与角色关系
		relAdminUserRoleDao.delRelationByUserId(user.getId());
		//建立新关系
		if(user.getRole()!=null){
			RelAdminUserRole rel = new RelAdminUserRole();
			rel.setAdminRoleId(user.getRole().getId());
			rel.setAdminUserId(user.getId());
			relAdminUserRoleDao.insert(rel);
		}
		return user;
	}

	public void updateStatus(int status, int id) throws Exception {
		adminUserDao.updateStatus(status,id);
	}

	public List<AdminUser> getAdminUserByName(String name) throws Exception {
		 
		return adminUserDao.getAdminUserByName(name);
	}
	
	/**
	 * 重置密码
	 */
	public void updatePasswordById(int id, String password) throws Exception {
		adminUserDao.updatePasswordById(id,password);
		
	}

}
