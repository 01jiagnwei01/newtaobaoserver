package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import org.springframework.validation.BindException;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.taobaoservice.entitys.RegLog;
import com.gxkj.taobaoservice.enums.YanZhengMaTypes;

public interface RegLogDao extends BaseDAO {

	void updateEmaiToNoEnable(String mail)throws SQLException, BindException;
	/**
	 * 根据类型和值查询注册结果操作
	 * @param type
	 * @param email
	 * @return
	 * @throws SQLException 
	 */
	RegLog getRegLogByTypeAndValue(YanZhengMaTypes type, String value) throws SQLException;
	 

}
