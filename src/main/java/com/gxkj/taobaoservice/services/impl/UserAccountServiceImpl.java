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
				if(currentBalance.compareTo(amount)<=0){
					log.info(String.format("余额不足，取款金额是：%d,当前余额是:%d",amount,currentBalance));
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
				
			
		
		}
		if( operateType != UserAccountTypes.DEPOSIT 
				&&  operateType != UserAccountTypes.WITHDRAW_APPLY 
				&&  operateType != UserAccountTypes.WITHDRAW_FAILURE 
				&&  operateType != UserAccountTypes.WITHDRAW_SUCCESS ){
			/**
			 * 标识出支持的
			 */
			 throw new BusinessException(BusinessExceptionInfos.undo,"operateType");
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

}
