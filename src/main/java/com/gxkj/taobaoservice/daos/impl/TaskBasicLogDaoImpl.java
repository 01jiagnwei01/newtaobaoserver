package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.taobaoservice.daos.TaskBasicLogDao;
import com.gxkj.taobaoservice.enums.TaskBasicLogUserType;
import com.gxkj.taobaoservice.enums.TaskStatus;
@Repository
public class TaskBasicLogDaoImpl extends BaseDAOImpl implements TaskBasicLogDao {

	private  String getOnePersonCountReceivedTaskInOneDaySql = "select count(id) from task_basic_log where user_id = ? and  user_type = ? and task_state = ? and create_time between ? and ? ";
 
	public Integer getOnePersonCountReceivedTaskInOneDay(Integer userId,
			Date time) throws SQLException {
		 String beginTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(time);
		 String endTime = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(time);
		
		return (Integer) this.selectOneBySQL(getOnePersonCountReceivedTaskInOneDaySql, new Object[]{
				userId,TaskBasicLogUserType.RECEIVER,TaskStatus.Have_Bean_Received,beginTime,endTime
		}, Integer.class);
	}

}
