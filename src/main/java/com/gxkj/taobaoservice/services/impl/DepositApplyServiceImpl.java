package com.gxkj.taobaoservice.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.DepositApplyLogDao;
import com.gxkj.taobaoservice.daos.UserAccountDao;
import com.gxkj.taobaoservice.daos.UserAccountLogDao;
import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.DepositAppLog;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.RechargeApplyStatus;
import com.gxkj.taobaoservice.enums.UserAccountTypes;
import com.gxkj.taobaoservice.services.DepositApplyService;
import com.gxkj.taobaoservice.services.UserAccountService;
/**
 * 
 *充值审核
 *
 */
@Service
public class DepositApplyServiceImpl implements DepositApplyService {

	@Autowired
	 private DepositApplyLogDao rechargeApplyDao;
	
	@Autowired
	private UserBaseDao userBaseDao;
	
	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private UserAccountLogDao userAccountLogDao;
	
	@Autowired
	private UserAccountService userAccountService;
	 /**
	  * 审核拒绝
	  */
	public DepositAppLog doRefuseRechargeApply(Integer applyId, AdminUser adminUser,
			String reason) throws SQLException {
		 
		DepositAppLog apply = (DepositAppLog) rechargeApplyDao.selectById(applyId, DepositAppLog.class);
		apply.setRefuseReason(reason);
		apply.setAuditorId(adminUser.getId());
		apply.setAuditorName(adminUser.getRealName());
		Date now = new Date();
		apply.setReviewTime(now);
		apply.setStatus(RechargeApplyStatus.REFUSE);
		
		rechargeApplyDao.update(apply);
		
		return apply;
	}

	/**
	 * 充值审核通过
	 */
	public DepositAppLog doAgreeRechargeApply(Integer applyId, AdminUser adminUser)
			throws SQLException, BusinessException {
		 
		DepositAppLog apply = (DepositAppLog) rechargeApplyDao.selectById(applyId, DepositAppLog.class);
		if (apply.getStatus() != RechargeApplyStatus.WAIT_FOR_AUDIT){
			throw new BusinessException(BusinessExceptionInfos.STATUS_NOT_WAIT_FOR);
		}
		String thirdOrderNo = apply.getThirdOrderNo();
		List<DepositAppLog> rechargeApplys =  rechargeApplyDao.getRechargeApplyByThirdOrderNoAndNotIDndPassed(thirdOrderNo,applyId);
		if(CollectionUtils.isNotEmpty(rechargeApplys)){
			throw new BusinessException(BusinessExceptionInfos.THIRDORDERNO_IS_USED);
		}
		/**
		 * 记录通过
		 */
		Date now = new Date();
		apply.setReviewTime(now);
		apply.setStatus(RechargeApplyStatus.APPROVE);
		apply.setAuditorId(adminUser.getId());
		apply.setAuditorName(adminUser.getRealName());
		rechargeApplyDao.update(apply);
		
		/**
		 * 充值资金到用户账户
		 */
 		UserBase userBase = (UserBase) userBaseDao.selectById(apply.getUserId(), UserBase.class);
 		userAccountService.updateUserAccount(userBase, apply.getAmount(),   BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,  UserAccountTypes.DEPOSIT, apply.getId(), adminUser.getId());
 		return apply;
		
		
	}

	public ListPager doPage(int pageno, int pagesize, String thirdOrderNo,
			BigDecimal amount, Integer userId, RechargeApplyStatus status,
			Date createBeginTime, Date createEndTime, Date reviewBeginTime,
			Date reviewEndTime, Integer auditorId) throws SQLException {
		
		return rechargeApplyDao.doPage( pageno,  pagesize,  thirdOrderNo,
				 amount,  userId,  status,
				 createBeginTime,  createEndTime,  reviewBeginTime,
				 reviewEndTime,  auditorId);
	}

	 
	 
	 

}
