package com.gxkj.taobaoservice.daos.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.taobaoservice.daos.TaskBasicLogDao;
import com.gxkj.taobaoservice.entitys.TaskBasicLog;
import com.gxkj.taobaoservice.enums.TaskBasicLogUserType;
import com.gxkj.taobaoservice.enums.TaskStatus;
@Repository
public class TaskBasicLogDaoImpl extends BaseDAOImpl implements TaskBasicLogDao {

	private  String getOnePersonCountReceivedTaskInOneDaySql = "select count(id) from task_basic_log where user_id = ? and  user_type = ? and task_state = ? and create_time between ? and ? ";
 
	public BigInteger getOnePersonCountReceivedTaskInOneDay(Integer userId,
			Date time) throws SQLException {
		 String beginTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(time);
		 String endTime = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(time);
		
		return (BigInteger) this.selectOneBySQL(getOnePersonCountReceivedTaskInOneDaySql, new Object[]{
				userId,TaskBasicLogUserType.RECEIVER,TaskStatus.Have_Bean_Received,beginTime,endTime
		}, Integer.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskBasicLog> getTaskBasicLogByTaskId(Integer taskId)
			throws SQLException {
		String hql = "from TaskBasicLog where taskBasicId = ? order by id";
		return (List<TaskBasicLog>) this.selectByHQL(hql, new Object[]{taskId});
	}

	
	/**
	 * 判断用户是否已经使用IP接过创单人员的单子
	 */
	public boolean haveReceivedWithThisIP(Integer recieverID, Integer createId,
			String receiverIp, Date fromDate) throws SQLException, ParseException {
		String hql = "from TaskBasicLog where  userId =:recieverID and taskBasicCreaterId=:createId and receiverIp=:receiverIp and createTime>=:createTime";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String beginTime = formatter.format(fromDate);
		Date createTime = formatter.parse(beginTime);
		 Map<String,Object> par  = new HashMap<String,Object>();
		 par.put("recieverID", recieverID);
		 par.put("createId", createId);
		 par.put("receiverIp", receiverIp);
		 par.put("createTime", createTime);
		 TaskBasicLog taskBasicLog = (TaskBasicLog) this.selectOneByHQL(hql, par);
		
		return taskBasicLog == null ? false:true;
	}

	

}
