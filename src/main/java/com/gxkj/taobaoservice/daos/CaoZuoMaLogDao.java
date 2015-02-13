package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.taobaoservice.entitys.CaoZuoMaLog;
import com.gxkj.taobaoservice.enums.RegLogTypes;

public interface CaoZuoMaLogDao extends BaseDAO {

	/**
	 * 使过去发送的验证码失效
	 * @param id
	 * @param email
	 * @throws SQLException 
	 */
	void setEnableCodeToMail(Integer id, RegLogTypes regLogType) throws SQLException;

	/**
	 * 查询用户的验证码
	 * @param userId
	 * @param bindEmail
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	CaoZuoMaLog getCodeByUserIdAndType(Integer userId, String value,
			RegLogTypes type)throws SQLException;

}
