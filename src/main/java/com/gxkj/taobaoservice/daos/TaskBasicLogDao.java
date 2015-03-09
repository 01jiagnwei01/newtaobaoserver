package com.gxkj.taobaoservice.daos;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.taobaoservice.entitys.TaskBasicLog;

public interface TaskBasicLogDao extends BaseDAO {
	/**
	 * 查看用户某天已接单的总数
	 * @param userId
	 * @param time
	 * @return
	 * @throws SQLException
	 */
	public BigInteger getOnePersonCountReceivedTaskInOneDay(Integer userId,Date time)throws SQLException;

	/**
	 * 
	 * @param taskId
	 * @return
	 * @throws SQLException
	 */
	public List<TaskBasicLog> getTaskBasicLogByTaskId(Integer taskId)throws SQLException;
	
}
