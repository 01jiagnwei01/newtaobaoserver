package com.gxkj.taobaoservice.daos;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;

import com.gxkj.common.dao.BaseDAO;

public interface TaskBasicLogDao extends BaseDAO {
	/**
	 * 查看用户某天已接单的总数
	 * @param userId
	 * @param time
	 * @return
	 * @throws SQLException
	 */
	public BigInteger getOnePersonCountReceivedTaskInOneDay(Integer userId,Date time)throws SQLException;
	
}
