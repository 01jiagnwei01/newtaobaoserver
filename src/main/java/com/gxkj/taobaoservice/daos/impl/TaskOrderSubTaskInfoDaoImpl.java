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
	
	@SuppressWarnings("unchecked")
	public List<TaskOrderSubTaskInfo> getSubTaskInfoByOrderId(Integer orderId)
			throws SQLException {
		 
		return ((List<TaskOrderSubTaskInfo>) this.selectByHQL(getSubTaskInfoByOrderIdHql, new Object[]{orderId}));
	}

}
