package com.gxkj.taobaoservice.services.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.common.util.PWDGenter;
import com.gxkj.taobaoservice.daos.ApplyDrawDao;
import com.gxkj.taobaoservice.daos.UserAccountDao;
import com.gxkj.taobaoservice.daos.UserAccountLogDao;
import com.gxkj.taobaoservice.entitys.ApplyDrawLog;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.RechargeApplyStatus;
import com.gxkj.taobaoservice.enums.UserAccountTypes;
import com.gxkj.taobaoservice.services.TiXianService;
import com.gxkj.taobaoservice.services.UserAccountService;

@Service
public class TiXianServiceImpl implements TiXianService {

	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private  ApplyDrawDao applyDrawDao;
	
	@Autowired
	private UserAccountLogDao userAccountLogDao;
	
	@Autowired
	private UserAccountService userAccountService;
	
	/**
	 * @throws BusinessException 
	 * @throws SQLException 
	 * 取款申请
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
		applyDrawLog.setAccountNo(alipyAccount);
		applyDrawLog.setUserId(userBase.getId());
		applyDrawDao.insert(applyDrawLog);
		
		
		userAccountService.updateUserAccount(userBase, amount, null, UserAccountTypes.WITHDRAW_APPLY, applyDrawLog.getId(),null);
		 
		return userBase;
		 
	}

	/**
	 * 分页查看提现申请记录
	 */
	public ListPager doPage(UserBase userBase, int pageno, int pagesize,
			Date startTime, Date endTime) throws BusinessException,
			SQLException {
		 
		return applyDrawDao.doPageFoeFront(pageno, pagesize, userBase.getId(), startTime, endTime);
	}

}
