package com.gxkj.taobaoservice.daos;

import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
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

	/**
	 * 判断用户指定日期内是否已经接过发单人的单
	 * @param recieverID
	 * @param userId
	 * @param receiverIp
	 * @param froDate
	 * @return
	 * @throws SQLException
	 * @throws ParseException 
	 */
	public boolean haveReceivedWithThisIP(Integer recieverID, Integer userId,
			String receiverIp, Date froDate)throws SQLException, ParseException;
	
	
	
}
