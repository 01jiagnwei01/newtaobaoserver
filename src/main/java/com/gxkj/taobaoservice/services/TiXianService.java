package com.gxkj.taobaoservice.services;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.entitys.UserBase;

public interface TiXianService {
	/**
	 * 
	 * @param userBase		提现申请用户
	 * @param alipyAccount	支付宝账号
	 * @param amount		申请提现金额
	 * @param yanzhengma	用户输入的验证码
	 * @param yanzhengmaInsession	session里的验证码
	 * @param caozuoma		操作码
	 * @throws BusinessException 
	 * @throws SQLException 
	 */
	public UserBase doapply(UserBase userBase, String alipyAccount,
			BigDecimal amount, String yanzhengma, String yanzhengmaInsession,
			String caozuoma) throws BusinessException, SQLException;

}
