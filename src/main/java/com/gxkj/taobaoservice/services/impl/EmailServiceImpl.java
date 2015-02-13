package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.services.EmailService;
@Service
public class EmailServiceImpl  implements EmailService{

	@Autowired
	private UserBaseDao userBaseDao;
	
	public boolean emailIsRegd(String email ) throws SQLException {
	 
		return userBaseDao.emailIsReged(email);
	}

}
