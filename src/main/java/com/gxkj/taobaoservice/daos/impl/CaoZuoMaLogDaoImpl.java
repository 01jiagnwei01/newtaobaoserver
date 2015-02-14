package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.taobaoservice.daos.CaoZuoMaLogDao;
import com.gxkj.taobaoservice.entitys.CaoZuoMaLog;
import com.gxkj.taobaoservice.enums.YanZhengMaTypes;
@Repository
public class CaoZuoMaLogDaoImpl extends BaseDAOImpl implements CaoZuoMaLogDao {

	 private static final  String setEnableCodeToMailHql = "update CaoZuoMaLog set enabled = false where userd=? and type=? ";
	 
	 private static final  String getCodeByUserIdAndTypeHql = "from CaoZuoMaLog where userd=? and type=? and value = ? and  enabled = true order by id desc ";
	 
	 
	public void setEnableCodeToMail(Integer id, YanZhengMaTypes regLogType) throws SQLException {
		 this.executeUpdateByHql(setEnableCodeToMailHql, new Object[] {id,regLogType});
	}

	 
	public CaoZuoMaLog getCodeByUserIdAndType(Integer userId, String value,
			YanZhengMaTypes type) throws SQLException {
		CaoZuoMaLog log = (CaoZuoMaLog) this.selectOneByHQL(getCodeByUserIdAndTypeHql, new Object[] {userId,type,value});
		return log;
	}

}
