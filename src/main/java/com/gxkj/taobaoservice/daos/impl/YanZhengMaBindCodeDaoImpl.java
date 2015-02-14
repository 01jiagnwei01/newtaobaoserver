package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.validation.BindException;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.taobaoservice.daos.YanZhengMaBindCodeDao;
import com.gxkj.taobaoservice.entitys.YanZhengMaBindCode;
import com.gxkj.taobaoservice.enums.YanZhengMaTypes;
import com.gxkj.taobaoservice.enums.YanZhengMaTranType;
@Repository
public class YanZhengMaBindCodeDaoImpl extends BaseDAOImpl implements
		YanZhengMaBindCodeDao {

	private static final String updateEmaiToNoEnableSql = "update yanzhengma_bind_code set enabled = false where value = ? and type = ?";
	
	private final  static String getRegLogByUserIdAndTransAndTypeAndValueHql = "from YanZhengMaBindCode where type=:type and value=:value and enabled = true and userId=:userId and tranType=:tranType order by id desc";
	
	public void updateEmaiToNoEnable(String mail) throws SQLException,
			BindException {
		 this.executeUpdateBySql(updateEmaiToNoEnableSql, new Object[] {mail,YanZhengMaTypes.email.toString()});
	}

	 
	public YanZhengMaBindCode getRegLogByUserIdAndTransAndTypeAndValue(
			Integer userId, YanZhengMaTranType yanZhengMaTranType, YanZhengMaTypes yanZhengMaType,
			String value) throws SQLException {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("type", yanZhengMaTranType);
		parameter.put("value", value);
		parameter.put("userId", userId);
		parameter.put("tranType", yanZhengMaTranType);
		return (YanZhengMaBindCode) this.selectOneByHQL(getRegLogByUserIdAndTransAndTypeAndValueHql, parameter);
	}
}
