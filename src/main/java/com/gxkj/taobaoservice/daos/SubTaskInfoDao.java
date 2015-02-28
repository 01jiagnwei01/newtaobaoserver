package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;
import java.util.List;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.taobaoservice.entitys.SubTaskInfo;

public interface SubTaskInfoDao extends BaseDAO {
	
	/**
	 * 查询可用的子任务
	 * @return
	 * @throws SQLException
	 */
	public List<SubTaskInfo> getAllEnableSubTaskInfo() throws SQLException;

}
