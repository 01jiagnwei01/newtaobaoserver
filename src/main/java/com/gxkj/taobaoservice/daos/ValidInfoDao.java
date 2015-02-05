package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.taobaoservice.entitys.ValidInfo;
import com.gxkj.taobaoservice.enums.ValidType;

public interface ValidInfoDao extends BaseDAO {
	
	/**
	 * 根据指定类型和对应的值查询验证信息
	 * @param type
	 * @param value
	 * @return
	 * @throws SQLException 
	 */
	public ValidInfo getValidInfoByTypeAndTypeValue(ValidType type,String typeValue) throws SQLException;
	
	/**
	 * 删除有效日期前没有激活的记录 
	 * @throws SQLException
	 */
	public int deleteValidInfoUnActiveAfterValidTime()  throws SQLException;

}
