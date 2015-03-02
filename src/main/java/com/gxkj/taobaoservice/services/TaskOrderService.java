package com.gxkj.taobaoservice.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.TaskOrder;
import com.gxkj.taobaoservice.entitys.UserBase;

public interface TaskOrderService {
	
	 
	/**
	 * 创建任务订单
	 * @param userBase  用户
	 * @param taobaoXiaohao  淘宝小号
	 * @param userQq	用户QQ
	 * @param productTitle 产品标题
	 * @param productLink  产品链接
	 * @param guaranteePrice 担保金额
	 * @param encourage		奖励接手人
	 * @param goodCommentTimeLimit	好评时效
	 * @param goodCommentContent	好评内容
	 * @param needWangWangTalk		需要旺旺聊天
	 * @param noRepeatTalk			禁止重复接任务
	 * @param needZhiDingJieShouRen		需要指定接手人
	 * @param jieShouRenId				指定接手人的ID
	 * @param needZhiDingSouHuoDiZhi	需要指定收货地址
	 * @param shouHuoDiZhi				指定的收货地址
	 * @param piLiangFabuCount		发布任务条数
	 * @return
	 * @throws SQLException
	 */
	public TaskOrder doAddTaskOrder( UserBase userBase,String taobaoXiaohao,String userQq,String productTitle,String productLink,
			BigDecimal guaranteePrice, BigDecimal encourage,String goodCommentTimeLimit,
			String goodCommentContent,boolean needWangWangTalk,boolean  noRepeatTalk,boolean needZhiDingJieShouRen,Integer jieShouRenId,
			boolean needZhiDingSouHuoDiZhi ,String shouHuoDiZhi,Integer piLiangFabuCount
			) throws SQLException,BusinessException;

	/**
	 * 分页查看用户发布的订单
	 * @param userBase
	 * @param pageno
	 * @param pagesize
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws SQLException
	 */
	public ListPager doPage(UserBase userBase, int pageno, int pagesize,
			Date startTime, Date endTime)throws SQLException;

	/**
	 * 根据订单id和用户id查询订单信息
	 * @param userId
	 * @param orderId
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public TaskOrder getTaskOrderByOrderIdAndUserId(Integer userId, Integer orderId)throws SQLException,BusinessException;

	/**
	 * 取消订单，
	 * @param userId
	 * @param orderId
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public void docancelTaskOrderByOrderIdAndUserId(Integer userId, Integer orderId)throws SQLException,BusinessException;

	/**
	 * 确认订单
	 * @param userId
	 * @param orderId
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public void doapplyTaskOrderByOrderIdAndUserId(UserBase userBase, Integer orderId)throws SQLException,BusinessException;

}
