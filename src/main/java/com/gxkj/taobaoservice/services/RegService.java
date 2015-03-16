package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import javax.mail.MessagingException;

import org.springframework.validation.BindException;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.dto.RegObjDTO;

public interface RegService {
	/**
	 * 向邮箱发送注册码
	 * @param mail
	 * @return
	 * @throws MessagingException 
	 */
	public boolean doSendMail(String mail)throws SQLException, BusinessException, BindException, MessagingException ;

	/**
	 * 注册用户
	 * @param regObjDTO
	 * @throws SQLException 
	 * @throws BusinessException 
	 * @throws BindException 
	 */
	public void doRegFn(RegObjDTO regObjDTO) throws SQLException, BusinessException;

	/**
	 * 向手机号发送验证码
	 * @param phone
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public void doSendPhone(String phone)throws SQLException, BusinessException;

}
