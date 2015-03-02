package com.gxkj.taobaoservice.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.UserAccountTypes;

public interface UserAccountService {

	/**
	 * 关联修改用户账户信息，并完成log日志
	 * @param userBase
	 * @param payamount  付款金额
	 * @param lockAmount  锁定金额
	 * @param payPoints	 付款点数
	 * @param lockPoints	锁定点数
	 * @param operateType
	 * @param refTableId
	 * @return
	 * @throws BusinessException 
	 * @throws SQLException 
	 */
	public boolean updateUserAccount(UserBase  userBase,BigDecimal payamount,BigDecimal lockAmount,
			BigDecimal payPoints,BigDecimal lockPoints,UserAccountTypes operateType,Integer refTableId,Integer adminUserId) throws BusinessException, SQLException;

	/**
	 * 查询用户指定时间段内的资金变化记录
	 * @param userBase
	 * @param pageno
	 * @param pagesize
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public ListPager doPage(UserBase userBase, int pageno, int pagesize,
			Date startTime, Date endTime)throws BusinessException, SQLException;
}
