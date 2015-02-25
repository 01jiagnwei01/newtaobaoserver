package com.gxkj.taobaoservice.services.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.PWDGenter;
import com.gxkj.taobaoservice.daos.ApplyDrawDao;
import com.gxkj.taobaoservice.daos.UserAccountDao;
import com.gxkj.taobaoservice.daos.UserAccountLogDao;
import com.gxkj.taobaoservice.entitys.ApplyDrawLog;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserAccountLog;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.RechargeApplyStatus;
import com.gxkj.taobaoservice.enums.UserAccountTypes;
import com.gxkj.taobaoservice.services.TiXianService;

@Service
public class TiXianServiceImpl implements TiXianService {

	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private  ApplyDrawDao applyDrawDao;
	
	@Autowired
	private UserAccountLogDao userAccountLogDao;
	
	/**
	 * @throws BusinessException 
	 * @throws SQLException 
	 * 
	 */
	public UserBase doapply(UserBase userBase, String alipyAccount,
			BigDecimal amount, String yanzhengma, String yanzhengmaInsession,
			String caozuoma) throws BusinessException, SQLException {
		if(userBase == null){
			throw new BusinessException(BusinessExceptionInfos.USER_IS_BLANK,"user");
		}
		if(StringUtils.isBlank(caozuoma)){
			throw new BusinessException(BusinessExceptionInfos.CAO_ZUO_MA_IS_BLANK,"caozuoma");
		}
		String dbCaozuoma = userBase.getCaoZuoMa();
		String md5CaoZuoMa = PWDGenter.generateKen(caozuoma);
		if( !dbCaozuoma.equals(md5CaoZuoMa) ){
			throw new BusinessException(BusinessExceptionInfos.CAO_ZUO_MA_IS_ERROR,"caozuoma");
		}
		
		if(StringUtils.isBlank(yanzhengma)){
			throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_IS_BLANK,"yanzhengma");
		}
		if(StringUtils.isBlank(yanzhengmaInsession)){
			throw new BusinessException(BusinessExceptionInfos.DEVELOP_PARAM_SESSION_YANZHENGMA_ERROR,"yanzhengmaInsession");
		}
		if(!yanzhengmaInsession .equalsIgnoreCase(yanzhengma)){
			throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_ERROR,"yanzhengma");
		}
		if(StringUtils.isBlank(alipyAccount)){
			throw new BusinessException(BusinessExceptionInfos.ALIPAY_ACCOUNT_IS_BLANK,"alipay");
		}
		if(amount == null){
			throw new BusinessException(BusinessExceptionInfos.DEVELOP_PARAM_SESSION_YANZHENGMA_ERROR,"yanzhengmaInsession");
		}
		if(amount.compareTo(BigDecimal.ZERO)<=0){
			throw new BusinessException(BusinessExceptionInfos.AMOUNT_MUST_IS_POSITIVE,"amount");
		}
		
		//从数据库中取出用户现在可用余额
		UserAccount uerAccount = userAccountDao.getUserAccountByUserId(userBase.getId());
		BigDecimal currentBalance = uerAccount.getCurrentBalance();
		if(currentBalance.compareTo(amount)<0){
			throw new BusinessException(BusinessExceptionInfos.AMOUNT_MONEY_NOT_ENOUGH,"amount");
		}
		//开始进行入库操作
		//记录取款申请
		Date now = new Date();
		ApplyDrawLog applyDrawLog = new ApplyDrawLog();
		applyDrawLog.setAmount(amount);
		applyDrawLog.setCreateTime(now);
		applyDrawLog.setStatus(RechargeApplyStatus.WAIT_FOR_AUDIT);
		applyDrawLog.setThirdOrderNo(alipyAccount);
		applyDrawLog.setUserId(userBase.getId());
		applyDrawDao.insert(applyDrawLog);
		
		
		BigDecimal beforeLockedAmount = uerAccount.getLockedBalance();
		BigDecimal beforeRestAmount = currentBalance;
		//锁定用户金额  可用金额减少，锁定金额增加
		uerAccount.setCurrentBalance(beforeRestAmount.subtract(amount));
		uerAccount.setLockedBalance(uerAccount.getLockedBalance().add(amount));
		userAccountDao.update(uerAccount);
		userBase.setUerAccount(uerAccount);
		
		//记录用户账户变化
		UserAccountLog userAccountLog = new UserAccountLog();
		userAccountLog.setAfterLockedAmount(uerAccount.getLockedBalance());
		userAccountLog.setAfterLockedPoints(uerAccount.getLockedPoints());
		userAccountLog.setAfterRestAmount(uerAccount.getCurrentBalance());
		userAccountLog.setAfterRestPoints(uerAccount.getCurrentRestPoints());
		userAccountLog.setAmount(amount);
		userAccountLog.setBeforeLockedAmount(beforeLockedAmount);
		userAccountLog.setBeforeLockedPoints(uerAccount.getLockedPoints());
		userAccountLog.setBeforeRestAmount(beforeRestAmount);
		userAccountLog.setBeforeRestPoints(uerAccount.getCurrentRestPoints());
		userAccountLog.setCreateTime(now);
		userAccountLog.setType(UserAccountTypes.WITHDRAW_APPLY);
		userAccountLog.setUserId(userBase.getId());
		userAccountLog.setUserName(userBase.getUserName());
		userAccountLogDao.insert(userAccountLog);
		
		return userBase;
		 
	}

}
