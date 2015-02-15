package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import com.gxkj.taobaoservice.entitys.YanzhengmaLog;
import com.gxkj.taobaoservice.enums.YanZhengMaTypes;

import org.springframework.validation.BindException;


public interface RegLogService {
	
	public  YanzhengmaLog addRegLog(YanzhengmaLog entity) throws SQLException, BindException;

	/**
	 * 设置为不可用状态
	 * @param mail
	 * @throws SQLException
	 * @throws BindException
	 */
	public void updateEmaiToNoEnable(String mail)throws SQLException, BindException;

	/**
	 * 根据类型和值查询注册结果操作
	 * @param type
	 * @param email
	 * @return
	 * @throws SQLException 
	 */
	public YanzhengmaLog getRegLogByTypeAndValue(YanZhengMaTypes type, String email) throws SQLException;
}
