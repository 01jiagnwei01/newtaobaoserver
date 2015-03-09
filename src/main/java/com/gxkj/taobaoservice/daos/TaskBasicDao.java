package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.TaskBasic;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.TaskStatus;

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
			int pagesize, Date startTime, Date endTime,TaskStatus status)throws SQLException;

	/**
	 * 查看某用户某天接过某用户的单
	 * @param now
	 * @param receiverId
	 * @param createrId
	 * @return
	 * @throws SQLException
	 * @throws ParseException 
	 */
	TaskBasic getTaskBasicCountReceivedByReceiverId(Date now, Integer receiverId,Integer createrId)throws SQLException, ParseException;
	/**
	 * 分页查看用户接的任务
	 * @param userBase
	 * @param orderId			订单ID
	 * @param pageno			页码
	 * @param pagesize			每页显示条数
	 * @param startTime			起始日期
	 * @param endTime			结束日期
	 * @return
	 * @throws SQLException
	 */
	ListPager doPageForSiteAndReceive(UserBase userBase, Integer orderId,
			int pageno, int pagesize, Date startTime, Date endTime,TaskStatus status) throws SQLException;

	/**
	 * 我完成的任务列表
	 * @param userBase
	 * @param pageno
	 * @param pagesize
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws SQLException
	 */
	ListPager doPageForMyfinishedTask(UserBase userBase, int pageno,
			int pagesize, Date startTime, Date endTime)throws SQLException;

	/**
	 * 后台管理员查看任务
	 * @param pageno
	 * @param pagesize
	 * @param producttittle
	 * @param status
	 * @param userId
	 * @param beginTime
	 * @param endTime
	 * @param taobao
	 * @param qq
	 * @param receivetaobao
	 * @param receiveqq
	 * @param receivebeginTime
	 * @param receiveendTime
	 * @return
	 * @throws SQLException
	 */
	ListPager doPageForAdmin(int pageno, int pagesize, String producttittle,
			TaskStatus status, Integer userId, Date beginTime, Date endTime,
			String taobao, String qq, String receivetaobao, String receiveqq,
			Date receivebeginTime, Date receiveendTime)throws SQLException;

}
