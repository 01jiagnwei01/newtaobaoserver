package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.taobaoservice.entitys.YanzhengmaLog;
import com.gxkj.taobaoservice.enums.YanZhengMaLogTranTypes;
import com.gxkj.taobaoservice.enums.YanZhengMaTypes;

public interface YanZhengMaLogDao extends BaseDAO {

	void updateToNoEnable(String mail,YanZhengMaTypes type)throws SQLException;
	/**
	 * 根据类型和值查询注册结果操作
	 * @param type
	 * @param email
	 * @return
	 * @throws SQLException 
	 */
	YanzhengmaLog getRegLogByTypeAndValue(YanZhengMaTypes type, YanZhengMaLogTranTypes transType ,String value) throws SQLException;
	/**
	 * 根据用户ID,业务类型，验证码类型,联系方式
	 * @param userId
	 * @param updateBindType
	 * @param tranType
	 * @param linkValue
	 * @return
	 * @throws SQLException 
	 */
	YanzhengmaLog getRegLogByUserIdAndTransAndTypeAndValue(Integer userId,
			YanZhengMaLogTranTypes tranType, YanZhengMaTypes type,
			String linkValue) throws SQLException;
	 

}
