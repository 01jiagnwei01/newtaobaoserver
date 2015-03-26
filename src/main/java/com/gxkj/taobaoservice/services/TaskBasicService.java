package com.gxkj.taobaoservice.services;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.TaskBasic;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.TaskStatus;

public interface TaskBasicService {
	
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
	 * @throws BusinessException 
	 */
	public ListPager doPage(UserBase userBase,Integer orderId, int pageno, int pagesize,
			Date startTime, Date endTime)throws SQLException, BusinessException;

	/**
	 * 根据 任务ID查看任务
	 * @param orderId
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public TaskBasic getTaskByIdForSite (  Integer taskId)throws SQLException, BusinessException;
	
	/**
	 * 根据 任务ID查看任务
	 * @param orderId
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public TaskBasic getTaskByIdForAdmin (  Integer taskId)throws SQLException, BusinessException;

	/**
	 * 任务大厅
	 * @param pageno
	 * @param pagesize
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public ListPager doPageForDaTing(int pageno, int pagesize, Date startTime,
			Date endTime)throws SQLException, BusinessException;

	/**
	 * 接单
	 * @param userBase
	 * @param taskid
	 * @param receiverIp	用户IP	 
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 * @throws ParseException 
	 */
	public TaskBasic doReceiveTask(UserBase userBase, int taskid,String receiverIp)throws SQLException, BusinessException, ParseException;

	/**
	 * 接单人确定订单完成
	 * @param userBase
	 * @param taskid
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public TaskBasic doRecierCompleteTask(UserBase userBase, int taskid)throws SQLException, BusinessException;

	/**
	 * 任务创建人确定任务完成
	 * @param userBase
	 * @param taskid
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public TaskBasic doSureRecierCompleteTask(UserBase userBase, int taskid)throws SQLException, BusinessException;

	/**
	 * 接单人放弃任务
	 * @param userBase
	 * @param taskid
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public TaskBasic doRergiveupTask(UserBase userBase, int taskid)throws SQLException, BusinessException;

	/**
	 * 我接的任务列表
	 * @param userBase
	 * @param orderId
	 * @param pageno
	 * @param pagesize
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public ListPager doMyReceiveTaskPage(UserBase userBase, Integer orderId,
			int pageno, int pagesize, Date startTime, Date endTime)throws SQLException, BusinessException;

	/**
	 * 查看我完成的任务
	 * @param userBase
	 * @param pageno
	 * @param pagesize
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public ListPager getMyCompletelistPage(UserBase userBase, int pageno,
			int pagesize, Date startTime, Date endTime)throws SQLException, BusinessException;

 
	/**
	 * 管理员分页查看任务
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
	 * @throws BusinessException
	 */
	public ListPager doPageForAdmin(int pageno, int pagesize,
			String producttittle, TaskStatus status, Integer userId,
			Date beginTime, Date endTime, String taobao, String qq,
			String receivetaobao, String receiveqq, Date receivebeginTime,
			Date receiveendTime)throws SQLException, BusinessException;

}
