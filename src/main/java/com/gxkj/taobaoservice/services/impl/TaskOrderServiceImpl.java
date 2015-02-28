package com.gxkj.taobaoservice.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.SystemGlobals;
import com.gxkj.taobaoservice.daos.TaskOrderDao;
import com.gxkj.taobaoservice.daos.TaskOrderSubTaskInfoDao;
import com.gxkj.taobaoservice.daos.UserAccountDao;
import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.entitys.SubTaskInfo;
import com.gxkj.taobaoservice.entitys.TaskOrder;
import com.gxkj.taobaoservice.entitys.TaskOrderSubTaskInfo;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.SubTaskInfoBenefitPerson;
import com.gxkj.taobaoservice.enums.SubTaskInfoBenefitTypes;
import com.gxkj.taobaoservice.enums.TaskOrderStatus;
import com.gxkj.taobaoservice.services.TaskOrderService;
import com.gxkj.taobaoservice.util.EntityTransFormUtil;
import com.gxkj.taobaoservice.util.MoneyCalculateUtil;
import com.gxkj.taobaoservice.util.SystemDbData;
@Service
public class TaskOrderServiceImpl implements TaskOrderService {

	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private Validator validator; 
	@Autowired
	private TaskOrderDao taskOrderDao;
	
	@Autowired
	private UserBaseDao userBaseDao;
	
	@Autowired
	private TaskOrderSubTaskInfoDao taskOrderSubTaskInfoDao;
	
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
	public TaskOrder doAddTaskOrder(UserBase userBase,
			String taobaoXiaohao, String userQq, String productTitle,
			String productLink, BigDecimal guaranteePrice,
			BigDecimal encourage, String goodCommentTimeLimit,
			String goodCommentContent, boolean needWangWangTalk,
			boolean noRepeatTalk, boolean needZhiDingJieShouRen,
			Integer jieShouRenId, boolean needZhiDingSouHuoDiZhi,
			String shouHuoDiZhi, Integer piLiangFabuCount) throws SQLException,
			BusinessException {
		if(userBase == null){
			throw new BusinessException(BusinessExceptionInfos.USER_IS_BLANK,"userBase");
		}
		if(StringUtils.isBlank(taobaoXiaohao)){
			throw new BusinessException(BusinessExceptionInfos.TAO_BAO_XIAO_HAO_IS_BLANK,"taobaoXiaohao");
		}
		if(StringUtils.isBlank(userQq)){
			throw new BusinessException(BusinessExceptionInfos.QQ_IS_BLANK,"userQq");
		}
		if(StringUtils.isBlank(productTitle)){
			throw new BusinessException(BusinessExceptionInfos.PRODUCT_TITLE_IS_BLANK,"productTitle");
		}
		if(StringUtils.isBlank(productLink)){
			throw new BusinessException(BusinessExceptionInfos.QQ_IS_BLANK,"productLink");
		}
		if(taobaoXiaohao.length()>50){
			throw new BusinessException(BusinessExceptionInfos.TAO_BAO_XIAO_HAO_Length_MORE_THAN,"taobaoXiaohao");
		}
		if(userQq.length()>15){
			throw new BusinessException(BusinessExceptionInfos.QQ_IS_BLANK,"userQq");
		}
		if(productTitle.length()>50){
			throw new BusinessException(BusinessExceptionInfos.PRODUCT_TITLE_LENGTH_MORE_THAN,"productTitle");
		}
		if(productLink.length()>150){
			throw new BusinessException(BusinessExceptionInfos.PRODUCT_URL_LENGTH_MORE_THAN,"productLink");
		}
		if( BigDecimal.ZERO.compareTo(guaranteePrice) >0){
			throw new BusinessException(BusinessExceptionInfos.guaranteePrice__SHOULD_BE_POSITIVE,"guaranteePrice");
		}
		int userId = 0;
		if(needZhiDingJieShouRen){
			
			if(jieShouRenId == null){
				userId = 0;
			}
			if(userId == 0){
				throw new BusinessException(BusinessExceptionInfos.jieShouRenId_ERROR,"jieShouRenId");
			}
			
			
			UserBase  user  = (UserBase) userBaseDao.selectById(userId, UserBase.class);
			if(user == null){
				throw new BusinessException(BusinessExceptionInfos.jieShouRenId_ERROR,"jieShouRenId");
			}
		}
		if(needZhiDingSouHuoDiZhi){
			if(StringUtils.isBlank(shouHuoDiZhi)){
				throw new BusinessException(BusinessExceptionInfos.shouHuoDiZhi_IS_BLANK,"shouHuoDiZhi");
			}
		}
		int piLiangTimes = 1;
		if(piLiangFabuCount != null){
			piLiangTimes = piLiangFabuCount.intValue();
			if(piLiangTimes <=0){
				throw new BusinessException(BusinessExceptionInfos.PI_LIANG_COUNT_SHOULD_BE_POSITIVE,"piLiangFabuCount");
			}else if(piLiangTimes >50){
				throw new BusinessException(BusinessExceptionInfos.PI_LIANG_COUNT_MORE_THAN_TIMES,"piLiangFabuCount");
			}
		}
		
		String orderGrantPoint = SystemGlobals.getPreference("taobao.order.grant.point", "0");
		BigDecimal basicPingtaiGainPoint = new BigDecimal(orderGrantPoint);
		if(basicPingtaiGainPoint.compareTo(BigDecimal.ZERO)<0){
			throw new BusinessException("发布任务时，平台受益点数为负数，应该为正数或者0");
		}
		TaskOrder order = new TaskOrder();
		/**
		 * 基本任务和增值任务的集合
		 */
		 List<TaskOrderSubTaskInfo> taskOrderSubTaskInfos = new ArrayList<TaskOrderSubTaskInfo>();
		 List<SubTaskInfo> subTaskInfos = new ArrayList<SubTaskInfo>();
		/**
		 * 平台受益点数
		 */
		order.setBasicPingtaiGainPoint(basicPingtaiGainPoint);
		/**
		 * 每单接手方受益金额
		 */
		order.setBasicReceiverGainMoney(MoneyCalculateUtil.caculateOrderReceiverGainMoney(guaranteePrice.doubleValue()));
		/**
		 * 每单完成基本任务，接手方受益点数
		 */
		BigDecimal basicReceiverGainPoint = BigDecimal.ZERO;
		
		order.setBasicReceiverGainPoint(basicReceiverGainPoint);
		
		/**
		 * 创建时间
		 */
		Date now = new Date();
		order.setCreateTime(now);
		/**
		 * 奖励接手人
		 */
		order.setEncourage(encourage);
		/**
		 *  每单担保金
		 */
		order.setGuaranteePrice(guaranteePrice);
		/**
		 * 商品地址
		 */
		order.setProductLink(productLink);
		/**
		 * 商品标题
		 */
		order.setProductTitle(productTitle);
		/**
		 * 批量发布条数
		 */
		order.setRepeateTimes(piLiangTimes);
		/**
		 * 状态
		 */
		order.setStatus(TaskOrderStatus.WAIT_FOR_SURE);
		/**
		 * 淘宝小号
		 */
		order.setTaobaoXiaohao(taobaoXiaohao);
		order.setUserId(userBase.getId());
		/**
		 * 用户QQ
		 */
		order.setUserQq(userQq);
		/**
		 * 每单完成增值任务，平台受益点数
		 */
		BigDecimal zengzhiPingtaiGainPoints = BigDecimal.ZERO;
		if(needZhiDingJieShouRen){
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("ZHI_DING_JIE_SHOU_REN");
			if(item4.getBenefitPersion() == SubTaskInfoBenefitPerson.PLATFORM && item4.getBenefitType() == SubTaskInfoBenefitTypes.POINT){
				zengzhiPingtaiGainPoints = zengzhiPingtaiGainPoints.add(item4.getAmount());
				
				TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
				taskOrderSubTaskInfo.setInputValue(userId+"");
				taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
				subTaskInfos.add(item4);
			}
			
		}
		if(piLiangTimes>1){
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("PI_LIANG_FA_BU");
			if(item4.getBenefitPersion() == SubTaskInfoBenefitPerson.PLATFORM && item4.getBenefitType() == SubTaskInfoBenefitTypes.POINT){
				zengzhiPingtaiGainPoints = zengzhiPingtaiGainPoints.add(item4.getAmount());
				
				TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
				taskOrderSubTaskInfo.setInputValue(piLiangTimes+"");
				taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
				subTaskInfos.add(item4);
			}
			
		}
		if(piLiangTimes>1){
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("NO_REPEAT_TASK");
			if(item4.getBenefitPersion() == SubTaskInfoBenefitPerson.PLATFORM && item4.getBenefitType() == SubTaskInfoBenefitTypes.POINT){
				zengzhiPingtaiGainPoints = zengzhiPingtaiGainPoints.add(item4.getAmount());
				
				TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
				taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
				subTaskInfos.add(item4);
			}
		}
		order.setZengzhiPingtaiGainPoints(zengzhiPingtaiGainPoints);
		
		/**
		 *  每单完成增值任务，接手方受益金额
		 */
		BigDecimal zengzhiReceiverGainMoney = BigDecimal.ZERO;
		if(needWangWangTalk){
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("NEED_WANGWANG_TALK" );
			if(item4.getBenefitPersion() == SubTaskInfoBenefitPerson.RECEIVER && item4.getBenefitType() == SubTaskInfoBenefitTypes.Money){
				zengzhiReceiverGainMoney = zengzhiReceiverGainMoney.add(item4.getAmount());
				
				TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
				taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
				subTaskInfos.add(item4);
			}
		}
		if(needZhiDingSouHuoDiZhi){
			 
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("ZHI_DING_SHOU_HUO_DI_ZHI");
			if(item4.getBenefitPersion() == SubTaskInfoBenefitPerson.RECEIVER && item4.getBenefitType() == SubTaskInfoBenefitTypes.Money){
				zengzhiReceiverGainMoney = zengzhiReceiverGainMoney.add(item4.getAmount());
				
				
				TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
				taskOrderSubTaskInfo.setInputValue(shouHuoDiZhi);
				taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
				subTaskInfos.add(item4);
			}
		}
		order.setZengzhiReceiverGainMoney(zengzhiReceiverGainMoney);
		
		
		/**
		 * 每单完成增值任务，接手方受益点数
		 */
		BigDecimal zengzhiReceiverGainPoints = BigDecimal.ZERO;
		if(needWangWangTalk){
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("NEED_WANGWANG_TALK" );
			if(item4.getBenefitPersion() == SubTaskInfoBenefitPerson.RECEIVER && item4.getBenefitType() == SubTaskInfoBenefitTypes.Money){
				zengzhiReceiverGainPoints = zengzhiReceiverGainPoints.add(item4.getAmount());
				TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
				taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
				subTaskInfos.add(item4);
			}
		}
		if(needZhiDingSouHuoDiZhi){
			 
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("ZHI_DING_SHOU_HUO_DI_ZHI");
			if(item4.getBenefitPersion() == SubTaskInfoBenefitPerson.RECEIVER && item4.getBenefitType() == SubTaskInfoBenefitTypes.Money){
				zengzhiReceiverGainPoints = zengzhiReceiverGainPoints.add(item4.getAmount());
				TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
				taskOrderSubTaskInfo.setInputValue(shouHuoDiZhi);
				taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
				subTaskInfos.add(item4);
			}
		}
		order.setZengzhiReceiverGainPoints(zengzhiReceiverGainPoints);
		
		/**
		 * 订单持久化
		 */
		taskOrderDao.insert(order);
		/**
		 * 增值任务和基本任务持久化
		 */
		for(TaskOrderSubTaskInfo item : taskOrderSubTaskInfos){
			 item.setTaskOrderId(order.getId());
			 taskOrderSubTaskInfoDao.insert(item);
		}
		order.setTasks(subTaskInfos);
		MoneyCalculateUtil.caculateOrderAccount(order);
		
		return order;
	}
	
	

}
