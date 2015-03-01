package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;
import java.util.List;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.taobaoservice.entitys.TaskOrderSubTaskInfo;

public interface TaskOrderSubTaskInfoDao extends BaseDAO {

	/**
	 * 根据订单ID查看订单关联的任务
	 * @param orderId
	 * @return
	 * @throws SQLException
	 */
	List<TaskOrderSubTaskInfo> getSubTaskInfoByOrderId(Integer orderId)throws SQLException;

}
