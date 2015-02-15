package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import javax.mail.MessagingException;

import org.springframework.validation.BindException;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.entitys.UserBase;

public interface EmailService {
	
	/**
	 * 邮箱已经被注册过
	 * @param email 
	 * @return
	 * @throws SQLException 
	 */
	public boolean emailIsRegd(String email ) throws SQLException;

	/**
	 * 判断email是否被别人正在使用中
	 * @param userBase
	 * @param tomail
	 * @return
	 * @throws SQLException 
	 */
	public boolean emailIsRegdByOtherPeople(UserBase userBase, String tomail) throws SQLException;

	/**
	 * 向邮箱发送验证码
	 * @param userBase
	 * @param tomail
	 * @throws BusinessException
	 * @throws SQLException
	 * @throws BindException
	 * @throws MessagingException
	 */
	public void doSendMailCode(UserBase userBase, String tomail) throws BusinessException, SQLException, BindException, MessagingException;

	/**
	 * 更新用户邮箱
	 * @param userBase
	 * @param email
	 * @param caozuoma
	 * @param yanzhengma
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public void doUpdateByEmail(UserBase userBase, String email,
			String caozuoma, String yanzhengma) throws BusinessException, SQLException;
}
