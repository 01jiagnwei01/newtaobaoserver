package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.constants.StatusConstant;
import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.AdminRoleDao;
import com.gxkj.taobaoservice.entitys.AdminRole;

@Repository
public class AdminRoleDaoImpl extends BaseDAOImpl implements AdminRoleDao {

	
	public ListPager doPage(int pageno, int pagesize, String name,int status) throws SQLException {
			 
		
			List<Object> parameters = new ArrayList<Object>();
			StringBuffer sql = new StringBuffer("select * from admin_role   ");
			if(status != 0){
				sql.append(" where status  = ?");
				parameters.add(status);
			}else {
				sql.append(" where status !=   ").append(StatusConstant.isdelete);
			}
			if(StringUtils.isNotBlank(name)){
				sql.append(" and  name like ?");
				parameters.add("%"+name+"%");
			}
			sql.append(" order by id  ");
			
			ListPager pager = new ListPager();
			pager.setPageNo(pageno);
			pager.setRowsPerPage(pagesize );
			
			 pager = (ListPager) this.selectPageBySQL(sql.toString(), parameters.toArray(),AdminRole.class,pager);
			return pager;
		}

	public void deleteEntity(int id) throws SQLException {
		StringBuffer sql = new StringBuffer("update admin_role set status=").append(StatusConstant.isdelete).append(" where id= ?");
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(id);
		this.executeUpdate(sql.toString(), parameters.toArray());
	}

 
	public void updateStatus(int id, int status) throws SQLException {
		String  sql = String.format("update admin_role set status=%d where id= %d",status,id);
		this.executeUpdate(sql,null);
		
	}
}
