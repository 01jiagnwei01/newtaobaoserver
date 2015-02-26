package com.gxkj.taobaoservice.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
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

	/**
	 * 分页查看提现申请
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
