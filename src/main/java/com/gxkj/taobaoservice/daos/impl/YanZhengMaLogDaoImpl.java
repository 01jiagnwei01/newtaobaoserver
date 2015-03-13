package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.taobaoservice.daos.YanZhengMaLogDao;
import com.gxkj.taobaoservice.entitys.YanzhengmaLog;
import com.gxkj.taobaoservice.enums.YanZhengMaLogTranTypes;
import com.gxkj.taobaoservice.enums.YanZhengMaTypes;
@Repository
public class YanZhengMaLogDaoImpl extends BaseDAOImpl implements YanZhengMaLogDao {

	private  final static String updateEmaiToNoEnableSql = "update yanzhengma_log set enabled = 0 where value = ? and type = ?";
	private final static String getRegLogByTypeAndValueSql = "from YanzhengmaLog where tranType = :tranType and type=:type and value=:value and enabled = true order by id desc";
	private  final static String getRegLogByUserIdAndTransAndTypeAndValueSql = "from YanzhengmaLog where userId=:userId and tranType =:tranType  and type=:type and value=:value and enabled = true order by id desc";
	public void updateEmaiToNoEnable(String mail,YanZhengMaTypes type) throws SQLException  {
		 this.executeUpdateBySql(updateEmaiToNoEnableSql, new Object[] {mail,type.toString()});
	}
	 
	 
	public YanzhengmaLog getRegLogByTypeAndValue(YanZhengMaTypes type,YanZhengMaLogTranTypes tranType , String value) throws SQLException {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("type", type);
		parameter.put("value", value);
		parameter.put("tranType", tranType);
		return (YanzhengmaLog) this.selectOneByHQL(getRegLogByTypeAndValueSql,parameter);
	}


	/**
	 * 根据用户ID,业务类型，验证码类型,联系方式
	 * @param userId
	 * @param tranType
	 * @param type
	 * @param linkValue
	 * @return
	 * @throws SQLException 
	 */
	public YanzhengmaLog getRegLogByUserIdAndTransAndTypeAndValue(
			Integer userId, YanZhengMaLogTranTypes tranType,
			YanZhengMaTypes type, String linkValue) throws SQLException {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("userId", userId);
		parameter.put("tranType", tranType);
		parameter.put("type", type);
		parameter.put("value", linkValue);
		return (YanzhengmaLog) this.selectOneByHQL(getRegLogByUserIdAndTransAndTypeAndValueSql,parameter);
	}


	 
	 


	 

}
