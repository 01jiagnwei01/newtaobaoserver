package com.gxkj.taobaoservice.daos.impl;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.UserBaseStatus;
import com.gxkj.taobaoservice.enums.YANS;
@Repository
public class UserBaseDaoImpl extends BaseDAOImpl implements UserBaseDao {

	private static final String  userNameIsRegedHQL = " from UserBase where userName =:userName ";
	
	private static final String  emailIsRegedHQL = " from UserBase where bindEmail =:bindEmail ";
	
	private static final String updateUserCaoZuoMaHql = "update UserBase set caoZuoMa = ? where id = ?";
	
	public UserBase getUsersByUserName(String userName) throws SQLException {
		String hql = "from UserBase where userName = ?";
		return (UserBase) this.selectOneByHQL(hql, new Object[] {userName});
	}

	public ListPager doPage(int pageno, int pagesize, String name,
			UserBaseStatus status, Date regBeignTime, Date regEndTime,YANS supplyMoneystatus,BigDecimal supplyMoney)
			throws SQLException {
		 String hql = "from UserBase where 1=1";
		 Map<String,Object> param = new HashMap<String,Object>();
		 if(StringUtils.isNotBlank(name)){
			 hql += " and userName =:name";
			 param.put("name", name);
		 }
		 if(status != null){
			 hql += " and status =:status";
			 param.put("status", status);
		 }
		 if(regBeignTime != null){
			 hql += " and regTime >=:regBeignTime";
			 param.put("regBeignTime", regBeignTime);
		 }
		 if(regEndTime != null){
			 hql += " and regTime <=:regBeignTime";
			 param.put("regEndTime", regEndTime);
		}
		 if(supplyMoneystatus != null){
			 if(supplyMoneystatus == YANS.NO){
				 hql += " and supplyMoney =:supplyMoneyS";
				 param.put("supplyMoneyS", 0); 
			 }else  if(supplyMoneystatus == YANS.YES){
				 hql += " and supplyMoney >:supplyMoneyS";
				 param.put("supplyMoneyS", 0); 
			 }
		 }
		 if (supplyMoney != null && BigDecimal.ZERO.compareTo(supplyMoney)<0){
			 hql += " and supplyMoney =:supplyMoney";
			 param.put("supplyMoney", supplyMoney); 
		 }
		 
		if( MapUtils.isEmpty(param)){
			 hql = hql.replace("where 1=1", "");
		 }
		ListPager pager = new ListPager();
		pager.setPageNo(pageno);
		pager.setRowsPerPage(pagesize );
		ListPager page = this.selectPageByHql(hql, param,pager);
		return page;
	}

	 
	@SuppressWarnings("unchecked")
	public List<UserBase> getAllSupplyUsers() throws SQLException {
		 String hql = "from UserBase where supplyMoney >0";
		return (List<UserBase>) this.selectByHQL(hql);
	}

	public boolean userNameIsReged(String userName) throws SQLException {
		 Map<String,Object> parameters = new HashMap<String,Object>();
		 parameters.put("userName", userName);
		UserBase base = (UserBase) this.selectOneByHQL(userNameIsRegedHQL, parameters);
		return base == null?false:true;
	}
	public boolean emailIsReged(String email) throws SQLException {
		 Map<String,Object> parameters = new HashMap<String,Object>();
		 parameters.put("bindEmail", email);
		UserBase base = (UserBase) this.selectOneByHQL(emailIsRegedHQL, parameters);
		return base == null?false:true;
	}

 
	public void updateUserCaoZuoMa(Integer userId, String md5CaoZuoMa)
			throws SQLException {
		 
		this.executeUpdateByHql(updateUserCaoZuoMaHql, new Object[] {md5CaoZuoMa,userId});
	}

 
	public boolean emailIsRegdByOtherPeople(Integer id, String tomail)
			throws SQLException {
		 Map<String,Object> parameters = new HashMap<String,Object>();
		 parameters.put("bindEmail", tomail);
		UserBase base = (UserBase) this.selectOneByHQL(emailIsRegedHQL, parameters);
		return base == null?false:base.getId().equals(id)?false:true;
	
	}

 
	public UserBase getUsersByBindEmail(String email) throws SQLException {
		Map<String,Object> parameters = new HashMap<String,Object>();
		 parameters.put("bindEmail", email);
		UserBase base = (UserBase) this.selectOneByHQL(emailIsRegedHQL, parameters);
		return base;
	}

	 

	 
	

	 

}
