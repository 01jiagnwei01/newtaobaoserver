package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.entitys.UserBase;

public interface BindAlipayService {

	/**
	 * 绑定新的支付宝账号
	 * @param userBase
	 * @param alipay
	 * @param caozuoma
	 * @param yanzhengma
	 * @param yanzhengMaInSession
	 * @return
	 * @throws BusinessException
	 * @throws SQLException
	 */
	UserBase doBindAlipay(UserBase userBase, String alipay, String caozuoma,
			String yanzhengma, String yanzhengMaInSession)throws BusinessException, SQLException;

}
