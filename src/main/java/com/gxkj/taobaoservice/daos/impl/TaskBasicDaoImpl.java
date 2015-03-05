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
import com.gxkj.taobaoservice.daos.TaskBasicDao;
import com.gxkj.taobaoservice.entitys.UserBase;
@Repository
public class TaskBasicDaoImpl extends BaseDAOImpl implements TaskBasicDao {

	 
	public ListPager doPageForSite(UserBase userBase, Integer orderId,
			int pageno, int pagesize, Date startTime, Date endTime)
			throws SQLException {
		StringBuffer hql = new StringBuffer("from TaskBasic  where  userId = ? ");
		 List<Object> params = new ArrayList<Object>();
		 boolean haveParam = false;
		 if(userBase != null){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			 hql.append( "  userId = ?" );
			 params.add(userBase.getId());
		 }
		
		 if(startTime != null ){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
				hql.append( "  createTime >= ?" );
				params.add(startTime);
				 
		}
		if(endTime != null ){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			hql.append( "  createTime <= ?" );
			params.add(endTime);
		 
		}
		if(orderId != null && orderId!=1){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			hql.append( "  taskOrderId = ?" );
			params.add(orderId);
		}
		
		hql.append(" order by id desc ");
		String hqlString = hql.toString();
		ListPager pager = new ListPager();
		pager.setPageNo(pageno);
		pager.setRowsPerPage(pagesize);
		
		this.selectPageByHql(hqlString, params.toArray(), pager);
		/**
		 * 做翻转
		 */
		if(CollectionUtils.isNotEmpty(pager.getPageData())){
			Collections.reverse(pager.getPageData()); 
		}
		return pager;
	}

}
