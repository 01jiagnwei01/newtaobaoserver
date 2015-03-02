package com.gxkj.taobaoservice.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.UserAccountDao;
import com.gxkj.taobaoservice.daos.UserAccountLogDao;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserAccountLog;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.UserAccountTypes;
import com.gxkj.taobaoservice.services.UserAccountService;
@Service
public class UserAccountServiceImpl implements UserAccountService {

	private static final Log log = 
			LogFactory.getLog(UserAccountServiceImpl.class);
	
	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private UserAccountLogDao userAccountLogDao;
	 
	public boolean updateUserAccount(UserBase userBase, BigDecimal amount,
			BigDecimal points, UserAccountTypes operateType, Integer refTableId,Integer adminUserId) throws BusinessException, SQLException {
	 
		if(userBase == null){
			log.info(String.format("参数错误,userBase=null"));
			 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userBase");
		}
		if(userBase.getId() == null || userBase.getId().intValue() <=0){
			log.info(String.format("参数错误,userBase.getId()=%d",userBase.getId()));
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userBase");
		}
		if(operateType == null){
			log.info(String.format("操作方式为空,operateType = null"));
			throw new BusinessException(BusinessExceptionInfos.UserAccountTypes_IS_NULL,"operateType");
		}
		UserAccount uerAccount = userAccountDao.getUserAccountByUserId(userBase.getId());
		
		//当前可用余额
		BigDecimal currentBalance = uerAccount.getCurrentBalance();
		//当前锁定金额
		BigDecimal currentLockedBalance = uerAccount.getLockedBalance();
		//当前可用点数
		BigDecimal currentPoints = uerAccount.getCurrentRestPoints();
		//当前锁定点数
		BigDecimal currentLockedPoints = uerAccount.getLockedPoints();
		
		//记录用户账户变化
		UserAccountLog userAccountLog = new UserAccountLog();
		userAccountLog.setType(operateType);
		//操作前锁定金额
		userAccountLog.setBeforeLockedAmount(currentLockedBalance);
		//操作前锁定点数
		userAccountLog.setBeforeLockedPoints(currentLockedPoints);
		//操作前可用金额
		userAccountLog.setBeforeRestAmount(currentBalance);
		//操作前可用点数
		userAccountLog.setBeforeRestPoints(currentPoints);
		
		userAccountLog.setAdminUserId(adminUserId);
		userAccountLog.setUserId(userBase.getId());
		Date now = new Date();
		userAccountLog.setCreateTime(now);
		
		/**
		 * 操作后可用金额，可用点数,锁定金额，锁定点数
		 */
		BigDecimal afterAmount = currentBalance;
		BigDecimal afterPoints = currentPoints;
		BigDecimal afterLockedAmount = currentLockedBalance;
		BigDecimal afterLockedPoints = currentLockedPoints;
		
		
		switch(operateType){
			case DEPOSIT:
				/**
				 * 充值成功,
				 */
				if(amount.compareTo(BigDecimal.ZERO)<=0){
					log.info(String.format("参数错误,amount需要是正数,amount=%d",amount));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"amount");
				}
				if(refTableId == null || refTableId.intValue()<=0){
					log.info(String.format("参数错误,关联充值记录表ID需要是正数,refTableId=%d",refTableId));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"refTableId");
				}
				/**
				 * 账户金额增加，其他不变
				 */
				afterAmount = currentBalance.add(amount);
				
				//关联充值表
				userAccountLog.setDepositApplyLogId(refTableId);
				
				break;
			case WITHDRAW_APPLY:
				/**
				 * 取款申请,
				 */
				if(amount.compareTo(BigDecimal.ZERO)<=0){
					log.info(String.format("参数错误,amount需要是正数,amount=%d",amount));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"amount");
				}
				if(refTableId == null || refTableId.intValue()<=0){
					log.info(String.format("参数错误,关联取款申请记录表ID需要是正数,refTableId=%d",refTableId));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"refTableId");
				}
				if(currentBalance.compareTo(amount)<0){
					log.info(String.format("余额不足，取款金额是：%d,当前余额是:%d",amount.doubleValue(),currentBalance.doubleValue()));
					 throw new BusinessException(BusinessExceptionInfos.AMOUNT_MONEY_NOT_ENOUGH,"amount");
				}
				/**
				 * 锁定金额增加，可用余额减少
				 */
				afterAmount = currentBalance.subtract(amount);
				afterLockedAmount = currentLockedBalance.add(amount);
				// 关联取款申请表
				userAccountLog.setDrawLogId(refTableId);
				break;
			case WITHDRAW_FAILURE:
				//取款申请失败
				if(amount.compareTo(BigDecimal.ZERO)<=0){
					log.info(String.format("参数错误,amount需要是正数,amount=%d",amount));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"amount");
				}
				if(refTableId == null || refTableId.intValue()<=0){
					log.info(String.format("参数错误,关联取款申请记录表ID需要是正数,refTableId=%d",refTableId));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"refTableId");
				}
				
				/**
				 * 取款申请失败,
				 * 可用余额增加,锁定金额减少
				 *
				 */
				afterAmount = currentBalance.add(amount);
				afterLockedAmount = currentLockedBalance.subtract(amount);
				// 关联取款申请表
				userAccountLog.setDrawLogId(refTableId);
				break;
			case WITHDRAW_SUCCESS:
				//取款成功
				if(amount.compareTo(BigDecimal.ZERO)<=0){
					log.info(String.format("参数错误,amount需要是正数,amount=%d",amount));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"amount");
				}
				if(refTableId == null || refTableId.intValue()<=0){
					log.info(String.format("参数错误,关联取款申请记录表ID需要是正数,refTableId=%d",refTableId));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"refTableId");
				}
				
				/**
				 * 取款申请成功,
				 * 锁定金额减少，其他不变
				 *
				 */
				afterLockedAmount = currentLockedBalance.subtract(amount);
				// 关联取款申请表
				userAccountLog.setDrawLogId(refTableId);
				break;
			case BUY_POINTS:
				/**
				 * 买点卡
				 */
				if(amount.compareTo(BigDecimal.ZERO)<=0){
					log.info(String.format("参数错误,amount需要是正数,amount=%d",amount));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"amount");
				}
				if(points.compareTo(BigDecimal.ZERO)<=0){
					log.info(String.format("参数错误,points需要是正数,points=%d",points));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"points");
				}
				if(currentBalance.compareTo(amount)<0){
					log.info(String.format("余额不足，取款金额是：%d,当前余额是:%d",amount.doubleValue(),currentBalance.doubleValue()));
					 throw new BusinessException(BusinessExceptionInfos.AMOUNT_MONEY_NOT_ENOUGH,"amount");
				}
				/**
				 * 可用金额减少,可用点数增加
				 */
				afterAmount = currentBalance.subtract(amount);
				afterPoints = currentPoints.add(points);
				
				break;
			case Task_Order_SURE:
				/**
				 * 订单确认
				 */
				break;
				
			
		
		}
		if( operateType != UserAccountTypes.DEPOSIT 
				&&  operateType != UserAccountTypes.WITHDRAW_APPLY 
				&&  operateType != UserAccountTypes.WITHDRAW_FAILURE 
				&&  operateType != UserAccountTypes.WITHDRAW_SUCCESS
				&&  operateType != UserAccountTypes.BUY_POINTS){
			/**
			 * 标识出支持的
			 */
			 throw new BusinessException(BusinessExceptionInfos.undo,"operateType");
		}
		/**
		 * 操作后验证
		 */
		if(afterAmount.compareTo(BigDecimal.ZERO)<0){
			log.error(String.format("参数错误,可用金额不能为负数,操作金额为amount=%ld，操作前金额为：%ld",amount.doubleValue(),currentBalance.doubleValue()));
			 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"amount");
		}
		if(afterPoints.compareTo(BigDecimal.ZERO)<0){
			log.error(String.format("参数错误,可用点数不能为负数,操作点数为points=%ld，操作前点数为：%ld",points.doubleValue(),currentPoints.doubleValue()));
			 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"points");
		}
		
		if(afterLockedAmount.compareTo(BigDecimal.ZERO)<0){
			log.error(String.format("参数错误,锁定金额不能为负数,操作金额=%ld，操作前锁定金额为：%ld",amount.doubleValue(),currentLockedBalance.doubleValue()));
			 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"amount");
		}
		
		if(afterLockedPoints.compareTo(BigDecimal.ZERO)<0){
			log.error(String.format("参数错误,锁定点数不能为负数,操作点数为points=%ld，操作前锁定点数为：%ld",points.doubleValue(),currentLockedPoints.doubleValue()));
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"points");
		}
		uerAccount.setCurrentBalance(afterAmount);
		uerAccount.setCurrentRestPoints(afterPoints);
		uerAccount.setLockedBalance(afterLockedAmount);
		uerAccount.setLockedPoints(afterLockedPoints);
		userAccountDao.update(uerAccount);
		
		//操作金额
		userAccountLog.setAmount(amount);
		//操作后可用金额
		userAccountLog.setAfterRestAmount(afterAmount);
		//操作后锁定金额
		userAccountLog.setAfterLockedAmount(afterLockedAmount);
		//操作后可用点数
		userAccountLog.setAfterRestPoints(afterPoints);
		//操作后锁定点数
		userAccountLog.setAfterLockedPoints(afterLockedPoints);
		
		userAccountLogDao.insert(userAccountLog);
		
		userBase.setUerAccount(uerAccount);
		return true;
	}

	 
	public ListPager doPage(UserBase userBase, int pageno, int pagesize,
			Date startTime, Date endTime) throws BusinessException,
			SQLException {
		 
		return userAccountLogDao.doPageForSite( userBase,  pageno,  pagesize,
				 startTime,  endTime);
	}

}
