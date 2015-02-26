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
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.RechargeApplyStatus;
import com.gxkj.taobaoservice.enums.UserAccountTypes;
import com.gxkj.taobaoservice.services.ApplyDrawService;
import com.gxkj.taobaoservice.services.UserAccountService;
/**
 * 取款申请
 *
 */
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
	
	@Autowired
	private UserAccountService userAccountService;

	 /**
	  * 取款审核拒绝
	 * @throws BusinessException 
	  */
	public ApplyDrawLog doRefuseApplyDraw(Integer applyId, AdminUser adminUser,
			String reason) throws SQLException, BusinessException {
		 
		ApplyDrawLog apply = (ApplyDrawLog) applyDrawDao.selectById(applyId, ApplyDrawLog.class);
		if(apply.getStatus() != RechargeApplyStatus.WAIT_FOR_AUDIT){
			throw new BusinessException(BusinessExceptionInfos.STATUS_NOT_WAIT_FOR);
		}
			
		apply.setRefuseReason(reason);
		apply.setAuditorId(adminUser.getId());
		apply.setAuditorName(adminUser.getRealName());
		Date now = new Date();
		apply.setReviewTime(now);
		apply.setStatus(RechargeApplyStatus.REFUSE);
		
		applyDrawDao.update(apply);
		
		
		/**
		 * 将钱回退到用户账户并记录日志
		 */
		UserBase userBase = (UserBase) userBaseDao.selectById(apply.getUserId(), UserBase.class);
		userAccountService.updateUserAccount(userBase, apply.getAmount(), null, UserAccountTypes.WITHDRAW_FAILURE, apply.getId(), adminUser.getId());
		 
		return apply;
	}

	/**
	 * 取款申请审核通过
	 */
	public ApplyDrawLog doAgreeApplyDraw(Integer applyId, AdminUser adminUser,String thirdOrderNo)
			throws Exception {
		 
		ApplyDrawLog apply = (ApplyDrawLog) applyDrawDao.selectById(applyId, ApplyDrawLog.class);
		/**
		 * 状态需要是待审核
		 */
		if (apply.getStatus() != RechargeApplyStatus.WAIT_FOR_AUDIT){
			throw new BusinessException(BusinessExceptionInfos.STATUS_NOT_WAIT_FOR);
		}
		/**
		 * 流水号不能为空
		 */
		 if(StringUtils.isBlank( thirdOrderNo)){
			 throw new BusinessException(BusinessExceptionInfos.THIRD_ORDER_NO_IS_NULL);
		 }
		 int length = thirdOrderNo.length();
		if (length!= 17 && length!= 19 &&
			         length!= 18 && length!= 30 &&
			         length!= 28 && length!= 32
			        ) {
			        throw new BusinessException(BusinessExceptionInfos.ORDER_SHOULD_BE_VALID_LENGTH,"orderno");
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
		UserBase userBase = (UserBase) userBaseDao.selectById(apply.getUserId(), UserBase.class);
		userAccountService.updateUserAccount(userBase, apply.getAmount(), null, UserAccountTypes.WITHDRAW_SUCCESS, apply.getId(), adminUser.getId());
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
