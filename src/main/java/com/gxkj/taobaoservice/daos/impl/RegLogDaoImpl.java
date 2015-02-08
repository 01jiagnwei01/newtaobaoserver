package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.validation.BindException;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.taobaoservice.daos.RegLogDao;
import com.gxkj.taobaoservice.entitys.RegLog;
import com.gxkj.taobaoservice.enums.RegLogTypes;
@Repository
public class RegLogDaoImpl extends BaseDAOImpl implements RegLogDao {

	private static final String updateEmaiToNoEnableSql = "update reg_log set enabled = 0 where value = ? and type = ?";
	private final static String getRegLogByTypeAndValueSql = "from RegLog where type=:type and value=:value and enabled = true order by id desc";
	
	public void updateEmaiToNoEnable(String mail) throws SQLException,
			BindException {
		 this.executeUpdateBySql(updateEmaiToNoEnableSql, new Object[] {mail,RegLogTypes.email.toString()});
	}
	 
	 
	public RegLog getRegLogByTypeAndValue(RegLogTypes type, String value) throws SQLException {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("type", type);
		parameter.put("value", value);
		return (RegLog) this.selectOneByHQL(getRegLogByTypeAndValueSql,parameter);
	}


	 

}
