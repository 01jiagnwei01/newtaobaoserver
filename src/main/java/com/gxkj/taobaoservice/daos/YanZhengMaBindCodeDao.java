package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import org.springframework.validation.BindException;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.taobaoservice.entitys.YanZhengMaBindCode;
import com.gxkj.taobaoservice.enums.YanZhengMaTypes;
import com.gxkj.taobaoservice.enums.YanZhengMaTranType;

public interface YanZhengMaBindCodeDao extends BaseDAO {

	void updateEmaiToNoEnable(String mail)throws SQLException, BindException;

	public YanZhengMaBindCode getRegLogByUserIdAndTransAndTypeAndValue(
			Integer userId, YanZhengMaTranType yanZhengMaTranType, YanZhengMaTypes yanZhengMaTypes,
			String value) throws SQLException;

}
