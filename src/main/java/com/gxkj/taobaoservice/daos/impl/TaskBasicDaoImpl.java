package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.TaskBasicDao;
import com.gxkj.taobaoservice.entitys.TaskBasic;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.TaskStatus;
@Repository
public class TaskBasicDaoImpl extends BaseDAOImpl implements TaskBasicDao {

	 
	 private String getTaskBasicCountReceivedByReceiverIdHQL = "from TaskBasic where userId = :userId and receiverId=:receiverId and receiverTime is not null and receiverTime >= :beginTime  order by id desc";
	
//	private String getTaskBasicCountReceivedByReceiverIdSQL = "select * from task_basic where user_id = ? and receiver_id=? and receiver_time is not null and receiver_time between ? and ? order by id desc";
	
	public ListPager doPageForSite(UserBase userBase, Integer orderId,
			int pageno, int pagesize, Date startTime, Date endTime,TaskStatus status)
			throws SQLException {
		StringBuffer hql = new StringBuffer("from TaskBasic   ");
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
		if(status != null){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			hql.append( "  status  = ?" );
			params.add(status);
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

	/**
	 * @throws ParseException 
	 * 
	 */
	public TaskBasic getTaskBasicCountReceivedByReceiverId(Date now,
			Integer receiverId, Integer createrId) throws SQLException, ParseException {
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		 Date beginTime = formatter1.parse(formatter1.format(now));
		 
		 List<Object> params = new ArrayList<Object>();
		 
		 
//		 DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
//		 Date endTime = formatter2.parse(formatter2.format(now));
//		 
		 Map<String,Object> parameters = new HashMap<String,Object> ();
		 parameters.put("userId", createrId);
		 
		 parameters.put("receiverId", receiverId);
		 parameters.put("beginTime", beginTime);
//		 parameters.put("endTime", endTime);
		 
		 params.add( createrId);
		 params.add(receiverId );
		 params.add(formatter1.format(now) );
//		 params.add( formatter2.format(now));
		 
		
		 return (TaskBasic) this.selectOneByHQL(getTaskBasicCountReceivedByReceiverIdHQL, parameters);
		  
		// return (TaskBasic) this.selectOneBySQL(getTaskBasicCountReceivedByReceiverIdSQL, params.toArray(),TaskBasic.class);
	}

	 
	public ListPager doPageForSiteAndReceive(UserBase userBase,
			Integer orderId, int pageno, int pagesize, Date startTime,
			Date endTime,TaskStatus status) throws SQLException {
		StringBuffer hql = new StringBuffer("from TaskBasic   ");
		 List<Object> params = new ArrayList<Object>();
		 boolean haveParam = false;
		 if(userBase != null){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			 hql.append( "  receiverId = ?" );
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
		if(status != null){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			hql.append( "  status = ?" );
			params.add(status);
		}
		
		hql.append(" order by receiverTime desc ");
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

	/**
	 * 我完成的任务列表
	 */
	public ListPager doPageForMyfinishedTask(UserBase userBase, int pageno,
			int pagesize, Date startTime, Date endTime) throws SQLException {
		StringBuffer hql = new StringBuffer("from TaskBasic  where  (status = ? or status = ? )");
		 List<Object> params = new ArrayList<Object>();
		 params.add(TaskStatus.Receive_Complete);
		 params.add(TaskStatus.Creater_Sure);
		 if(userBase != null){
			  
			 hql.append( "  and  receiverId = ?" );
			 params.add(userBase.getId());
		 }
		
		 if(startTime != null ){
				hql.append( " and  createTime >= ?" );
				params.add(startTime);
		}
		if(endTime != null ){
			 
			hql.append( "  and  createTime <= ?" );
			params.add(endTime);
		 
		}
		 
		hql.append(" order by receiverTime desc ");
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

 
	public ListPager doPageForAdmin(int pageno, int pagesize,
			String producttittle, TaskStatus status, Integer userId,
			Date beginTime, Date endTime, String taobao, String qq,
			String receivetaobao, String receiveqq, Date receivebeginTime,
			Date receiveendTime) throws SQLException {
		 
		StringBuffer hql = new StringBuffer("from TaskBasic   ");
		 List<Object> params = new ArrayList<Object>();
		 boolean haveParam = false;
		 if(StringUtils.isNotBlank(producttittle)){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			 hql.append( "  productTitle like ?" );
			 params.add("%"+producttittle+"%");
		 }
		 if(status!=null){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			 hql.append( "  status = ?" );
			 params.add(status);
		 }
		 if(userId !=null && userId.intValue() != 0){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			 hql.append( "  userId = ?" );
			 params.add(userId);
		 }
		
		 if(beginTime != null ){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
				hql.append( "  createTime >= ?" );
				params.add(beginTime);
				 
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
		if(StringUtils.isNotBlank(taobao)){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			hql.append( "  taobaoXiaohao = ?" );
			params.add(taobao);
		}
		if(StringUtils.isNotBlank(qq)){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			hql.append( "  userQq = ?" );
			params.add(qq);
		}
		if(StringUtils.isNotBlank(receivetaobao)){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			hql.append( "  receiverAlipay = ?" );
			params.add(receivetaobao);
		}
		if(StringUtils.isNotBlank(receiveqq)){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			hql.append( "  receiverQq = ?" );
			params.add(receiveqq);
		}
		if(receivebeginTime != null ){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
				hql.append( "  receiverTime >= ?" );
				params.add(receivebeginTime);
				 
		}
		if(receiveendTime != null ){
			 if(!haveParam){
				 haveParam = true;
				 hql.append( "  where " );
			 }else {
				 hql.append( "  and " );
			 }
			hql.append( "  receiverTime <= ?" );
			params.add(receiveendTime);
		 
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
