package com.gxkj.taobaoservice.services;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.gxkj.common.exceptions.BusinessException;
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
}
