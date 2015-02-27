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
	 * @param amount
	 * @param points
	 * @param operateType
	 * @param refTableId
	 * @return
	 * @throws BusinessException 
	 * @throws SQLException 
	 */
	public boolean updateUserAccount(UserBase  userBase,BigDecimal amount, BigDecimal  points,UserAccountTypes operateType,Integer refTableId,Integer adminUserId) throws BusinessException, SQLException;

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
