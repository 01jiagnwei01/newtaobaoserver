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
import com.gxkj.taobaoservice.daos.TaskOrderDao;
import com.gxkj.taobaoservice.entitys.UserBase;
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

}
