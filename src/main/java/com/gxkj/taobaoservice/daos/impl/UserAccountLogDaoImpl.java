package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.UserAccountLogDao;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserAccountLog;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.UserAccountTypes;
@Repository
public class UserAccountLogDaoImpl extends BaseDAOImpl implements
		UserAccountLogDao {
	
 
	public ListPager doPage(int pageno, int pagesize, Integer userID,
			Integer adminId, UserAccountTypes userAccountTypes, Date beginTime,
			Date endTime) throws SQLException {
		 
		String sql = "select user_account_log.*,user_base.user_name as userName, admin_user.name as adminName  from user_account_log,user_base,admin_user where admin_user.id = user_account_log.admin_user_id and user_account_log.user_id = user_base.id ";
				sql += " and user_account_log.type ='"+userAccountTypes+"'";
		List<Object> param = new ArrayList<Object>();
		 
		if(userID !=null){
			sql += " and user_account_log.user_id = ? ";
			param.add(userID);
		}
		if(adminId !=null){
			sql += " and user_account_log.admin_user_id = ? ";
			param.add(adminId);
		}
		if(beginTime != null){
			sql += " and user_account_log.createTime >= ? ";
			param.add(beginTime);
		}
		if(endTime != null){
			sql += " and user_account_log.createTime <= ? ";
			param.add(endTime);
		}
		sql += " order by user_account_log.id desc";
		ListPager pager = new ListPager();
		pager.setPageNo(pageno);
		pager.setRowsPerPage(pagesize);
		 
		return this.selectPageBySQL(sql, param.toArray(),UserAccountLog.class, pager);
	}

	public ListPager doPageForSite(UserBase userBase, int pageno, int pagesize,
			Date startTime, Date endTime) throws SQLException {
	 
		 StringBuffer hql = new StringBuffer("from UserAccountLog  where userId = ? ");
		 List<Object> params = new ArrayList<Object>();
		 params.add(userBase.getId());
		 if(startTime != null ){
				hql.append( " and createTime >= ?" );
				params.add(startTime);
				 
		}
		if(endTime != null ){
			hql.append( " and createTime <= ?" );
			params.add(endTime);
		 
		}
		hql.append(" order by id desc ");
		String hqlString = hql.toString();
		ListPager pager = new ListPager();
		pager.setPageNo(pageno);
		pager.setRowsPerPage(pagesize);
		
		this.selectPageByHql(hqlString, params.toArray(), pager);
		if(CollectionUtils.isNotEmpty(pager.getPageData())){
			//List<UserAccountLog> datas = (List<UserAccountLog>)pager.getPageData();
			Collections.reverse(pager.getPageData());
			//pager.setPageData(datas);
		}
		return pager;
	}

	 
	public UserAccount getUserAccountByUserBaseId(Integer userBaseId)
			throws SQLException {
		StringBuffer hql = new StringBuffer("from UserAccountLog  where userId = ? order by id  "); 
		return (UserAccount) this.selectOneByHQL(hql.toString(), new Object[]{userBaseId});
	}
}
