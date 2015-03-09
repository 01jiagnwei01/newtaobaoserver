package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.TaskOrderDao;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.TaskOrderStatus;
@Repository
public class TaskOrderDaoImpl extends BaseDAOImpl implements TaskOrderDao {

 
	/**
	 * 查看用户发布的订单，供前台用户使用
	 */
	public ListPager doPageForSite(UserBase userBase, int pageno, int pagesize,
			Date startTime, Date endTime) throws SQLException {
		 
		StringBuffer hql = new StringBuffer("from TaskOrder  where userId = ? ");
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
		/**
		 * 做翻转
		 */
		if(CollectionUtils.isNotEmpty(pager.getPageData())){
			Collections.reverse(pager.getPageData()); 
		}
		return pager;
	}

 
	public ListPager doPageForAdmin(int pageno, int pagesize, String product_title,
			TaskOrderStatus status, Integer userId, Date beginTime, Date endTime,String taobao,String qq)
			throws SQLException {
		 
		StringBuffer hql = new StringBuffer("from TaskOrder  where 1=1 ");
		 List<Object> params = new ArrayList<Object>();
		 if(StringUtils.isNotBlank( product_title)){
			 hql.append( " and productTitle  like  ?" );
				params.add("%"+product_title+"%");
		 }
		 if(StringUtils.isNotBlank( taobao)){
			 hql.append( " and taobaoXiaohao  =  ?" );
				params.add(taobao);
		 }
		 if(StringUtils.isNotBlank( qq)){
			 hql.append( " and userQq  =  ?" );
				params.add(qq);
		 }
		 if(status != null){
			 hql.append( " and status  =  ?" );
				params.add(status);
		 }
		 if(userId != null  && userId.intValue() != 0){
				hql.append( " and userId  = ?" );
				params.add(userId);
			 
		}
		 
		 if(beginTime != null ){
				hql.append( " and createTime >= ?" );
				params.add(beginTime);
				 
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
		/**
		 * 做翻转
		 */
		if(CollectionUtils.isNotEmpty(pager.getPageData())){
			Collections.reverse(pager.getPageData()); 
		}
		return pager;
	}

}
