package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.entitys.UserBase;

public interface TelService {
	
	/**
	 * 向手机发验证码
	 * @param userBase
	 * @param telNo
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public void doSendTelCodeForBind(UserBase userBase,String telNo) throws BusinessException, SQLException;

	/**
	 * 绑定手机号
	 * @param userBase
	 * @param telNo
	 * @param caozuoma
	 * @param yanzhengma
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public void doUpdateByPhone(UserBase userBase, String telNo,
			String caozuoma, String yanzhengma)throws BusinessException, SQLException;

}
