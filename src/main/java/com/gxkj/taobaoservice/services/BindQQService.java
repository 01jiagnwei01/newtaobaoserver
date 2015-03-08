package com.gxkj.taobaoservice.services;

import java.sql.SQLException;
 
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.entitys.UserBase;

public interface BindQQService {
	
	/**
	 * 绑定QQ
	 * @param userBase 
	 * @param newQQ							需要设置的qq号码
	 * @param caozuoma						操作码
	 * @param yanzhengma					验证码
	 * @param yanzhengMaInSession			session里的验证码
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public UserBase doBindQQ(UserBase userBase, String newQQ, String caozuoma, String yanzhengma, String yanzhengMaInSession)throws BusinessException, SQLException;

}
