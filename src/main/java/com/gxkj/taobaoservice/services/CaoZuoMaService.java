package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import javax.mail.MessagingException;

import org.springframework.validation.BindException;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.entitys.UserBase;

public interface CaoZuoMaService {

	/**
	 * 向用户绑定邮箱发送注册码
	 * @param mail
	 * @return
	 * @throws MessagingException 
	 */
	public boolean doSendMail(UserBase base)throws SQLException, BusinessException, BindException, MessagingException ;

	/**
	 * 使用有邮箱修改操作码
	 * @param base
	 * @param caozuoma
	 * @param recaozuoma
	 * @param code
	 * @return
	 * @throws BusinessException 
	 * @throws SQLException 
	 */
	public String doMailSubmitCaoZuoMa(UserBase base, String caozuoma, String recaozuoma,
			String code) throws BusinessException, SQLException;

	/**
	 * 使用旧的操作码来设置新的操作码
	 * @param userBase
	 * @param caozuoma
	 * @param recaozuoma
	 * @param oldcode
	 * @return
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public String doCaoZuoMaSubmitCaoZuoMa(UserBase userBase, String caozuoma,
			String recaozuoma, String oldcode)throws BusinessException, SQLException;

	/**
	 * 向手机发送验证短信
	 */
	public void doSendPhone(UserBase base)throws BusinessException, SQLException;

	/**
	 * 使用手机提交
	 * @param userBase
	 * @param caozuoma
	 * @param recaozuoma
	 * @param code
	 * @return
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public String doPhoneSubmitCaoZuoMa(UserBase userBase, String caozuoma,
			String recaozuoma, String code)throws BusinessException, SQLException;

}
