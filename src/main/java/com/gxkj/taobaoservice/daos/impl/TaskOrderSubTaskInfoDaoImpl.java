package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.taobaoservice.daos.TaskOrderSubTaskInfoDao;
import com.gxkj.taobaoservice.entitys.TaskOrderSubTaskInfo;
@Repository
public class TaskOrderSubTaskInfoDaoImpl extends BaseDAOImpl implements
		TaskOrderSubTaskInfoDao {

	 
	private String getSubTaskInfoByOrderIdHql = "from TaskOrderSubTaskInfo where taskOrderId = ? ";
	
	private String deleteByOrderIdSql = "delete from TaskOrderSubTaskInfo where taskOrderId = ? ";
	
	/**
	 * 根据订单ID查看订单关联的任务
	 * @param orderId
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<TaskOrderSubTaskInfo> getSubTaskInfoByOrderId(Integer orderId)
			throws SQLException {
		 
		return ((List<TaskOrderSubTaskInfo>) this.selectByHQL(getSubTaskInfoByOrderIdHql, new Object[]{orderId}));
	}

	/**
	 *	根据订单ID，删除增值和基本任务
	 * @param orderId
	 * @throws SQLException
	 */
	public void deleteByOrderId(Integer orderId) throws SQLException {
		 
		this.executeUpdateByHql(deleteByOrderIdSql, new Object[]{orderId});
	}

}
