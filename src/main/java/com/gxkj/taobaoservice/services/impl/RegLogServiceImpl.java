package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.gxkj.common.util.SpringValidatorHolder;
import com.gxkj.taobaoservice.daos.YanZhengMaLogDao;
import com.gxkj.taobaoservice.entitys.YanzhengmaLog;
import com.gxkj.taobaoservice.enums.YanZhengMaLogTranTypes;
import com.gxkj.taobaoservice.enums.YanZhengMaTypes;
import com.gxkj.taobaoservice.services.RegLogService;
@Service
public class RegLogServiceImpl implements RegLogService {

	@Autowired
	private YanZhengMaLogDao regLogDao;
	

	
	public YanzhengmaLog addRegLog(YanzhengmaLog entity) throws SQLException, BindException {
		SpringValidatorHolder.validate(entity);
		regLogDao.insert(entity);
		return entity;
	}

	 
	public void updateEmaiToNoEnable(String mail) throws SQLException,
			BindException {
		regLogDao.updateEmaiToNoEnable( mail) ;
		
	}


	 
	public YanzhengmaLog getRegLogByTypeAndValue(YanZhengMaTypes type, String value) throws SQLException {
		
		
		return regLogDao.getRegLogByTypeAndValue(type,YanZhengMaLogTranTypes.Reg,  value);
	}

}
