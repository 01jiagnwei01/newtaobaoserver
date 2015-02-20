package com.gxkj.taobaoservice.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.ApplyDrawDao;
import com.gxkj.taobaoservice.daos.UserAccountDao;
import com.gxkj.taobaoservice.daos.UserAccountLogDao;
import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.ApplyDrawLog;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserAccountLog;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.RechargeApplyStatus;
import com.gxkj.taobaoservice.enums.UserAccountTypes;
import com.gxkj.taobaoservice.services.ApplyDrawService;
@Service
public class ApplyDrawServiceImpl implements ApplyDrawService {

	@Autowired
	 private ApplyDrawDao applyDrawDao;
	
	@Autowired
	private UserBaseDao userBaseDao;
	
	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private UserAccountLogDao userAccountLogDao;
	
	/**
	 *	取款申请
	 */
	 public ApplyDrawLog addApplyDraw( BigDecimal  amount,UserBase userBase) throws SQLException{
		 ApplyDrawLog apply = new ApplyDrawLog();
		 apply.setAmount(amount); 
		 Date now = new Date();
		 apply.setCreateTime(now);
		 apply.setStatus(RechargeApplyStatus.WAIT_FOR_AUDIT);
		 apply.setUserId(userBase.getId());
		 applyDrawDao.insert(apply);
		 return apply;
	 }

	 /**
	  * 审核拒绝
	  */
	public ApplyDrawLog doRefuseApplyDraw(Integer applyId, AdminUser adminUser,
			String reason) throws SQLException {
		 
		ApplyDrawLog apply = (ApplyDrawLog) applyDrawDao.selectById(applyId, ApplyDrawLog.class);
		apply.setRefuseReason(reason);
		apply.setAuditorId(adminUser.getId());
		apply.setAuditorName(adminUser.getRealName());
		Date now = new Date();
		apply.setReviewTime(now);
		apply.setStatus(RechargeApplyStatus.REFUSE);
		
		applyDrawDao.update(apply);
		
		return apply;
	}

	/**
	 * 审核通过
	 */
	public ApplyDrawLog doAgreeApplyDraw(Integer applyId, AdminUser adminUser,String thirdOrderNo)
			throws Exception {
		 
		ApplyDrawLog apply = (ApplyDrawLog) applyDrawDao.selectById(applyId, ApplyDrawLog.class);
		/**
		 * 状态需要是待审核
		 */
		if (apply.getStatus() != RechargeApplyStatus.WAIT_FOR_AUDIT){
			throw new BusinessException(BusinessExceptionInfos.DRAWPAPPLY_STATUS_NOT_WAIT_FOR);
		}
		/**
		 * 流水号不能为空
		 */
		 if(StringUtils.isBlank( thirdOrderNo)){
			 throw new BusinessException(BusinessExceptionInfos.THIRD_ORDER_NO_IS_NULL);
		 }
		 /**
		  * 账户金额要足够
		  */
		 Integer userId =  apply.getUserId();
		 BigDecimal amount = apply.getAmount();
		 UserAccount userAccount = userAccountDao.getUserAccountByUserId(userId);
		 BigDecimal accountAmount = userAccount.getCurrentBalance();
		 if(accountAmount.compareTo(amount)<0){
			 throw new BusinessException(BusinessExceptionInfos.ACCOUNT_MONEY_NO_ENOUGH);
		 }
		 
		/**
		 * 流水号不能重复
		 */
		List<ApplyDrawLog> rechargeApplys =  applyDrawDao.getApplyDrawByThirdOrderNoAndNotIDndPassed(thirdOrderNo,applyId);
		if(CollectionUtils.isNotEmpty(rechargeApplys)){
			throw new BusinessException(BusinessExceptionInfos.DRAWPAPPLY_THIRDORDERNO_IS_USED);
		}
		/**
		 * 记录通过
		 */
		Date now = new Date();
		apply.setReviewTime(now);
		apply.setStatus(RechargeApplyStatus.APPROVE);
		apply.setAuditorId(adminUser.getId());
		apply.setAuditorName(adminUser.getRealName());
		apply.setThirdOrderNo(thirdOrderNo);
		applyDrawDao.update(apply);
		
		/**
		 *  用户账户扣除资金 
		 */
		
		 
		//取款前金额
		BigDecimal beforeBalance =  userAccount.getCurrentBalance();
		userAccount.setCurrentBalance(beforeBalance.subtract(apply.getAmount()));
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
		log.setType(UserAccountTypes.WITHDRAW);
		log.setUserId(apply.getUserId());
		
 		userAccountLogDao.insert(log);
 		return apply;
		
		
	}

	public ListPager doPage(int pageno, int pagesize, String thirdOrderNo,
			BigDecimal amount, Integer userId, RechargeApplyStatus status,
			Date createBeginTime, Date createEndTime, Date reviewBeginTime,
			Date reviewEndTime, Integer auditorId) throws SQLException {
		
		return applyDrawDao.doPage( pageno,  pagesize,  thirdOrderNo,
				 amount,  userId,  status,
				 createBeginTime,  createEndTime,  reviewBeginTime,
				 reviewEndTime,  auditorId);
	}

	 
	 
	 

}
