package com.gxkj.taobaoservice.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.daos.DepositApplyLogDao;
import com.gxkj.taobaoservice.entitys.DepositAppLog;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.RechargeApplyStatus;
import com.gxkj.taobaoservice.services.ChongZhiService;
@Service
public class ChongZhiServiceImpl implements ChongZhiService {

	@Autowired
	public  DepositApplyLogDao depositApplyLogDao;
 
	public void doChongZhiForNoInstant(UserBase userBase, BigDecimal amount,
			String orderNo) throws BusinessException, SQLException,
			BindException {
		if(amount == null || amount.compareTo(BigDecimal.ZERO)<= 0 ) {
			throw new BusinessException(BusinessExceptionInfos.AMOUNT_SHOULD_BE_POSITIVE,"amount");
		}
		if(StringUtils.isBlank(orderNo)) {
			throw new BusinessException(BusinessExceptionInfos.ORDER_IS_BLANK,"orderno");
		}
		if(orderNo.indexOf(" ")>=0) {
			throw new BusinessException(BusinessExceptionInfos.ORDER_IS_ERROR,"orderno");
		}
		try {
			 new BigDecimal(orderNo);
		}catch(Exception e) {
			e.printStackTrace();
			throw new BusinessException(BusinessExceptionInfos.ORDER_IS_ERROR,"orderno");
		}
		int length = orderNo.length();
		if (length!= 17 && length!= 19 &&
		         length!= 18 && length!= 30 &&
		         length!= 28 && length!= 32
		        ) {
		        throw new BusinessException(BusinessExceptionInfos.ORDER_SHOULD_BE_VALID_LENGTH,"orderno");
		}
		boolean isExist = depositApplyLogDao.orderNoIsExist(orderNo);
		if(isExist) {
			 throw new BusinessException(BusinessExceptionInfos.ORDERNO_IS_USED,"orderno");
		}
		/**
		 * 写入申请日志
		 */
		Date now = new Date();
		DepositAppLog depositAppLog = new DepositAppLog();
		depositAppLog.setAmount(amount);
		depositAppLog.setCreateTime(now);
		depositAppLog.setStatus(RechargeApplyStatus.WAIT_FOR_AUDIT);
		depositAppLog.setThirdOrderNo(orderNo);
		depositAppLog.setUserId(userBase.getId());
		depositApplyLogDao.insert(depositAppLog);

	}

}
