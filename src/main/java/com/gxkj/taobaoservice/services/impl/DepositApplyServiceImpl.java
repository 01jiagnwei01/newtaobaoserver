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
import com.gxkj.taobaoservice.daos.DepositApplyDao;
import com.gxkj.taobaoservice.daos.UserAccountDao;
import com.gxkj.taobaoservice.daos.UserAccountLogDao;
import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.DepositApply;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserAccountLog;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.RechargeApplyStatus;
import com.gxkj.taobaoservice.enums.UserAccountTypes;
import com.gxkj.taobaoservice.services.DepositApplyService;
@Service
public class DepositApplyServiceImpl implements DepositApplyService {

	@Autowired
	 private DepositApplyDao rechargeApplyDao;
	
	@Autowired
	private UserBaseDao userBaseDao;
	
	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private UserAccountLogDao userAccountLogDao;
	
	/**
	 * 充值申请
	 */
	 public DepositApply addRechargeApply(String thirdOrderNo,BigDecimal  amount,UserBase userBase) throws SQLException{
		 DepositApply apply = new DepositApply();
		 apply.setAmount(amount);
		 apply.setThirdOrderNo(thirdOrderNo);
		 Date now = new Date();
		 apply.setCreateTime(now);
		 apply.setStatus(RechargeApplyStatus.WAIT_FOR_AUDIT);
		 apply.setUserId(userBase.getId());
		 rechargeApplyDao.insert(apply);
		 return apply;
	 }

	 /**
	  * 审核拒绝
	  */
	public DepositApply doRefuseRechargeApply(Integer applyId, AdminUser adminUser,
			String reason) throws SQLException {
		 
		DepositApply apply = (DepositApply) rechargeApplyDao.selectById(applyId, DepositApply.class);
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
	 * 审核通过
	 */
	public DepositApply doAgreeRechargeApply(Integer applyId, AdminUser adminUser)
			throws SQLException, BusinessException {
		 
		DepositApply apply = (DepositApply) rechargeApplyDao.selectById(applyId, DepositApply.class);
		if (apply.getStatus() != RechargeApplyStatus.WAIT_FOR_AUDIT){
			throw new BusinessException(BusinessExceptionInfos.RECHARDAPPLY_STATUS_NOT_WAIT_FOR);
		}
		String thirdOrderNo = apply.getThirdOrderNo();
		List<DepositApply> rechargeApplys =  rechargeApplyDao.getRechargeApplyByThirdOrderNoAndNotIDndPassed(thirdOrderNo,applyId);
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
		
		UserAccount userAccount = userAccountDao.getUserAccountByUserId(apply.getUserId());
		//充值前金额
		BigDecimal beforeBalance =  userAccount.getCurrentBalance();
		userAccount.setCurrentBalance(beforeBalance.add(apply.getAmount()));
		userAccountDao.update(userAccount);
		
		/**
		 * 记录充值日志
		 */
		UserAccountLog log = new UserAccountLog();
		log.setAdminUserId(adminUser.getId());
		log.setAfterLockedAmount(userAccount.getLockedBalance());
		log.setAfterLockedPoints(userAccount.getLockedPoints());
		log.setAfterRestAmount(userAccount.getCurrentBalance() );
		log.setAfterRestPoints( userAccount.getCurrentRestPoints());
		log.setAmount(apply.getAmount());
		log.setBeforeLockedAmount(userAccount.getLockedBalance());
		log.setBeforeLockedPoints(userAccount.getLockedPoints());
		log.setBeforeRestAmount(beforeBalance);
		log.setBeforeRestPoints(userAccount.getLockedPoints());
		log.setCreateTime(now);
		log.setType(UserAccountTypes.DEPOSIT);
		log.setUserId(apply.getUserId());
		
 		userAccountLogDao.insert(log);
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
