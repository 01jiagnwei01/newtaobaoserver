package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.gxkj.common.util.SpringValidatorHolder;
import com.gxkj.taobaoservice.daos.RegLogDao;
import com.gxkj.taobaoservice.entitys.RegLog;
import com.gxkj.taobaoservice.enums.RegLogTypes;
import com.gxkj.taobaoservice.services.RegLogService;
@Service
public class RegLogServiceImpl implements RegLogService {

	@Autowired
	private RegLogDao regLogDao;
	

	
	public RegLog addRegLog(RegLog entity) throws SQLException, BindException {
		SpringValidatorHolder.validate(entity);
		regLogDao.insert(entity);
		return entity;
	}

	 
	public void updateEmaiToNoEnable(String mail) throws SQLException,
			BindException {
		regLogDao.updateEmaiToNoEnable( mail) ;
		
	}


	 
	public RegLog getRegLogByTypeAndValue(RegLogTypes type, String value) throws SQLException {
		
		
		return regLogDao.getRegLogByTypeAndValue(type,value);
	}

}
