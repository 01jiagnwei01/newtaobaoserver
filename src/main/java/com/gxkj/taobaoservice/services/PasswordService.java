package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import javax.mail.MessagingException;

import org.springframework.validation.BindException;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.entitys.UserBase;

public interface PasswordService {

	void doSendMail(UserBase userBase,String tomail) throws BusinessException, SQLException, BindException, MessagingException;

}
