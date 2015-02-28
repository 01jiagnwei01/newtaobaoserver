package com.gxkj.taobaoservice.services;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.dto.EntityReturnData;
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

}
