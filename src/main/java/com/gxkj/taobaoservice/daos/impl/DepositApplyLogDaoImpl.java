package com.gxkj.taobaoservice.daos.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.DepositApplyLogDao;
import com.gxkj.taobaoservice.entitys.DepositAppLog;
import com.gxkj.taobaoservice.enums.RechargeApplyStatus;
@Repository
public class DepositApplyLogDaoImpl extends BaseDAOImpl implements
		DepositApplyLogDao {

	/**
	 * 查询id不是参数applyId 但是流水号为thirdOrderNo的申请且已经申请通过的
	 * @param thirdOrderNo
	 * @param applyId
	 * @return List<RechargeApply> 
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public List<DepositAppLog> getRechargeApplyByThirdOrderNoAndNotIDndPassed(
			String thirdOrderNo, Integer applyId) throws SQLException {
		String hql = "from DepositAppLog where thirdOrderNo = ? and id != ? and status = '"+RechargeApplyStatus.APPROVE+"'";
		return (List<DepositAppLog>) this.selectByHQL(hql, new Object[]{thirdOrderNo,applyId});
	}

	/**
	  * 分页查看充值申请
	  * @param pageno 
	  * @param pagesize
	  * @param thirdOrderNo
	  * @param amount  充值金额
	  * @param userId  充值用户ID
	  * @param status  充值申请状态
	  * @param createBeginTime	创建时间起始时间
	  * @param createEndTime	创建时间结束时间
	  * @param reviewBeginTime  审核起始时间
	  * @param reviewEndTime	审核时间结束时间
	  * @param auditorId		审核人ID
	  * @return
	 * @throws SQLException 
	  */
	public ListPager doPage(int pageno, int pagesize, String thirdOrderNo,
			BigDecimal amount, Integer userId, RechargeApplyStatus status,
			Date createBeginTime, Date createEndTime, Date reviewBeginTime,
			Date reviewEndTime, Integer auditorId) throws SQLException {
		
		StringBuffer hql = new StringBuffer("  from DepositAppLog where 1=1");
		List<Object> params = new ArrayList<Object>();
		boolean contain = false;
		if(StringUtils.isNotBlank(thirdOrderNo)){
			hql.append( " and thirdOrderNo = ?" );
			params.add(thirdOrderNo);
			contain = true;
		}
		if(amount != null && amount.intValue() != 0){
			hql.append( " and amount = ?" );
			params.add(amount);
			contain = true;
		}
		if(userId != null && userId.intValue() != 0){
			hql.append( " and userId = ?" );
			params.add(userId);
			contain = true;
		}
		
		if(status != null ){
			hql.append( " and status = ?" );
			params.add(status);
			contain = true;
		}
		
		if(createBeginTime != null ){
			hql.append( " and createTime >= ?" );
			params.add(createBeginTime);
			contain = true;
		}
		
		if(createEndTime != null ){
			hql.append( " and createTime <= ?" );
			params.add(createEndTime);
			contain = true;
		}
		
		if(reviewBeginTime != null ){
			hql.append( " and reviewTime >= ?" );
			params.add(reviewBeginTime);
			contain = true;
		}
		
		if(reviewEndTime != null ){
			hql.append( " and reviewTime <= ?" );
			params.add(reviewEndTime);
			contain = true;
		}
		
		if(auditorId != null && auditorId.intValue() != 0){
			hql.append( " and auditorId = ?" );
			params.add(auditorId);
			contain = true;
		}
		String hqlString = hql.toString();
		if(!contain) hqlString = hqlString.replace("where 1=1", "");
		ListPager pager = new ListPager();
		pager.setPageNo(pageno);
		pager.setRowsPerPage(pagesize);
		
		return this.selectPageByHql(hqlString, params.toArray(), pager);
	}

	 
	public boolean orderNoIsExist(String thirdOrderNo) throws SQLException {
		StringBuffer hql = new StringBuffer("  from DepositAppLog where thirdOrderNo=:thirdOrderNo");
		Map params = new HashMap<String,Object>();
		params.put("thirdOrderNo", thirdOrderNo);
		DepositAppLog log = (DepositAppLog)this.selectOneByHQL(hql.toString(), params);
		return log == null?false:true;
	}

}
