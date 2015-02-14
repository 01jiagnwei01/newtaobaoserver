package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import javax.mail.MessagingException;

import org.springframework.validation.BindException;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.entitys.UserBase;

public interface PasswordService {

	void doSendMail(UserBase userBase,String tomail) throws BusinessException, SQLException, BindException, MessagingException;

	/**
	 * 通过邮箱修改绑定邮箱
	 * @param userBase
	 * @param email
	 * @param caozuoma
	 * @param yanzhengma
	 * @throws BusinessException 
	 * @throws SQLException 
	 */
	void doupdateByEmail(UserBase userBase, String email, String caozuoma,
			String yanzhengma) throws BusinessException, SQLException;

}
