package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.constants.StatusConstant;
import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.AdminUserDao;
import com.gxkj.taobaoservice.entitys.AdminUser;

 
@Repository
public class AdminUserDaoImpl extends BaseDAOImpl implements AdminUserDao {

	 
	public ListPager doPage(int pageno, int pagesize, String name, int status)
			throws SQLException {
		 StringBuffer sql = new StringBuffer("select * from admin_user where ");
		 List<Object> parameters = new ArrayList<Object>();
		 if(status == 0){
			 sql.append(" status != ").append(StatusConstant.isdelete);
		 }else{
			 sql.append(" status = ?");
			 parameters.add(status);
		 }
		 if(StringUtils.isNotBlank(name )){
			 sql.append(" and real_name like ?");
			 parameters.add("%"+name+"%");
		 }
		sql.append(" order by  id ");
		ListPager pager = new ListPager();
		pager.setPageNo(pageno);
		pager.setRowsPerPage(pagesize );
		ListPager page = this.selectPageBySQL(sql.toString(), parameters.toArray(),AdminUser.class,pager);
		return page;
	}


	public void updateStatus(int status, int id) throws SQLException {
		 StringBuffer sql = new StringBuffer("update admin_user set status=? where id= ?");
		 List<Object> parameters = new ArrayList<Object>();
		 parameters.add(status);
		 parameters.add(id);
		 this.executeUpdate(sql.toString(),  parameters.toArray());
		
	}


	@SuppressWarnings("unchecked")
	public List<AdminUser> getAdminUserByName(String name) throws SQLException {
		 StringBuffer sql = new StringBuffer("select * from admin_user where name = ?");
		 List<Object> parameters = new ArrayList<Object>();
		 parameters.add(name);
		 return ((List<AdminUser>) this.selectBySql (sql.toString(), parameters.toArray(), AdminUser.class));
	}


 
	public void updatePasswordById(int id, String password) throws SQLException {
		 StringBuffer sql = new StringBuffer("update  admin_user set password = ? where id = ?");
		 List<Object> parameters = new ArrayList<Object>();
		 parameters.add(password);
		 parameters.add(id);
		 this.executeUpdate(sql.toString(), parameters.toArray());
		 
	}


	 
	public AdminUser getOneAdminUserByName(String name) throws SQLException {
		 StringBuffer hql = new StringBuffer(" from AdminUser where name = ?");
		return (AdminUser) this.selectOneByHQL(hql.toString(), new Object[]{name});
	}

}
