package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.taobaoservice.daos.SubTaskInfoDao;
import com.gxkj.taobaoservice.entitys.SubTaskInfo;
import com.gxkj.taobaoservice.enums.SubTaskInfoStatus;
@Repository
public class SubTaskInfoDaoImpl extends BaseDAOImpl implements SubTaskInfoDao {

	private String getAllEnableSubTaskInfoHql = "from SubTaskInfo where status=? order by priority desc";
	
	
	public List<SubTaskInfo> getAllEnableSubTaskInfo() throws SQLException {
		 
		return ((List<SubTaskInfo>) this.selectByHQL(getAllEnableSubTaskInfoHql, new Object[]{SubTaskInfoStatus.NORMAL}));
	}

}
