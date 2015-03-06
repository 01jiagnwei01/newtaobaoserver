package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;
import java.util.Date;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.TaskBasic;
import com.gxkj.taobaoservice.entitys.UserBase;

public interface TaskBasicDao extends BaseDAO {

	/**
	 * 分页查看用户发布的任务
	 * @param userBase
	 * @param orderId			订单ID
	 * @param pageno			页码
	 * @param pagesize			每页显示条数
	 * @param startTime			起始日期
	 * @param endTime			结束日期
	 * @return
	 * @throws SQLException
	 */
	ListPager doPageForSite(UserBase userBase, Integer orderId, int pageno,
			int pagesize, Date startTime, Date endTime)throws SQLException;

	/**
	 * 查看某用户某天接过某用户的单
	 * @param now
	 * @param receiverId
	 * @param createrId
	 * @return
	 * @throws SQLException
	 */
	TaskBasic getTaskBasicCountReceivedByReceiverId(Date now, Integer receiverId,Integer createrId)throws SQLException;

}
