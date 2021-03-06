package com.gxkj.taobaoservice.daos;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.DepositAppLog;
import com.gxkj.taobaoservice.enums.RechargeApplyStatus;

public interface DepositApplyLogDao extends BaseDAO {

	/**
	 * 查询id不是参数applyId 但是流水号为thirdOrderNo的申请且已经申请通过的
	 * @param thirdOrderNo
	 * @param applyId
	 * @return List<RechargeApply> 
	 * @throws SQLException 
	 */
	List<DepositAppLog> getRechargeApplyByThirdOrderNoAndNotIDndPassed(
			String thirdOrderNo, Integer applyId) throws SQLException;

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
	ListPager doPage(int pageno, int pagesize, String thirdOrderNo,
			BigDecimal amount, Integer userId, RechargeApplyStatus status,
			Date createBeginTime, Date createEndTime, Date reviewBeginTime,
			Date reviewEndTime, Integer auditorId) throws SQLException;
	
	/**
	 * 判断订单号是否已经存在
	 * @param thirdOrderNo
	 * @return
	 * @throws SQLException
	 */
	boolean orderNoIsExist(String thirdOrderNo)throws SQLException;
	
	/**
	 * 前台分页查看查询数量
	 * @param pageno
	 * @param pagesize
	 * @param userId
	 * @param createBeginTime
	 * @param createEndTime
	 * @return
	 * @throws SQLException
	 */
	ListPager doPageFoeFront(int pageno, int pagesize, Integer userId ,
			Date createBeginTime, Date createEndTime) throws SQLException;

}
