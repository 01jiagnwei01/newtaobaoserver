package com.gxkj.taobaoservice.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.ApplyDraw;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.RechargeApplyStatus;

public interface ApplyDrawService {
	
	/**
	 * 
	 * @param amount	取款金额
	 * @param userBase	申请人
	 * @throws SQLException 
	 */
	 public ApplyDraw addApplyDraw( BigDecimal  amount,UserBase userBase) throws SQLException;

	/**
	  * 
	  * 审核拒绝
	 * @param applyId
	 * @param adminUser
	 * @param reason
	 * @throws SQLException
	 */
	 public ApplyDraw doRefuseApplyDraw(Integer applyId,AdminUser  adminUser,String reason) throws SQLException;
	 
	 /**
	  * 审核通过
	  * @param applyId
	  * @param adminUser
	  * @throws SQLException
	 * @throws BusinessException 
	  */
	 public ApplyDraw doAgreeApplyDraw(Integer applyId,AdminUser  adminUser,String thirdOrderNo) throws Exception;
	 
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
	 public ListPager doPage(int pageno, int pagesize, String thirdOrderNo, BigDecimal amount,Integer userId,RechargeApplyStatus status,
			 Date createBeginTime,Date createEndTime,Date reviewBeginTime,Date reviewEndTime,Integer auditorId
			 ) throws SQLException;

	 

	
}
