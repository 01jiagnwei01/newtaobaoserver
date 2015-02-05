package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.taobaoservice.daos.ValidInfoDao;
import com.gxkj.taobaoservice.entitys.ValidInfo;
import com.gxkj.taobaoservice.enums.ValidType;
@Repository
public class ValidInfoDaoImpl extends BaseDAOImpl implements ValidInfoDao {

	@SuppressWarnings("unchecked")
	public ValidInfo getValidInfoByTypeAndTypeValue(ValidType type, String typeValue) throws SQLException {
		 
		String hql = "from ValidInfo where type=:type and typeValue=:typeValue and (activeTime == null or activeTime<:now) and enabled =:enabled order by id desc";
		Map<String,Object> parameter = new HashMap<String,Object> ();
		parameter.put("type", type);
		parameter.put("typeValue", typeValue); 
		parameter.put("now", new Date());
		parameter.put("enabled", true);
		List<ValidInfo> validInfos =  ((List<ValidInfo>) this.selectByHQL(hql, parameter));
		return CollectionUtils.isEmpty(validInfos)?null:validInfos.get(0);
	}

	 
	public int deleteValidInfoUnActiveAfterValidTime() throws SQLException {
		 
		String hql = "update ValidInfo set enabled =:NoEnable where enabled =:CanEnabled   and  :now >= invalidTime order by id desc";
		Map<String,Object> parameter = new HashMap<String,Object> ();
		parameter.put("NoEnable", false);
		parameter.put("CanEnabled", true);
		parameter.put("now", new Date());
		return this.executeUpdateByHql(hql, parameter);
	}


	

}
