package com.gxkj.taobaoservice.services.impl;

import groovy.json.JsonOutput;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Validator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.common.util.SystemGlobals;
import com.gxkj.taobaoservice.daos.TaskBasicDao;
import com.gxkj.taobaoservice.daos.TaskBasicLogDao;
import com.gxkj.taobaoservice.daos.TaskOrderDao;
import com.gxkj.taobaoservice.daos.TaskOrderSubTaskInfoDao;
import com.gxkj.taobaoservice.daos.UserAccountDao;
import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.entitys.SubTaskInfo;
import com.gxkj.taobaoservice.entitys.TaskBasic;
import com.gxkj.taobaoservice.entitys.TaskBasicLog;
import com.gxkj.taobaoservice.entitys.TaskOrder;
import com.gxkj.taobaoservice.entitys.TaskOrderSubTaskInfo;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.SubTaskInfoBenefitPerson;
import com.gxkj.taobaoservice.enums.SubTaskInfoBenefitTypes;
import com.gxkj.taobaoservice.enums.TaskBasicLogUserType;
import com.gxkj.taobaoservice.enums.TaskOrderStatus;
import com.gxkj.taobaoservice.enums.TaskStatus;
import com.gxkj.taobaoservice.enums.UserAccountTypes;
import com.gxkj.taobaoservice.services.TaskOrderService;
import com.gxkj.taobaoservice.services.UserAccountService;
import com.gxkj.taobaoservice.util.EntityTransFormUtil;
import com.gxkj.taobaoservice.util.MoneyCalculateUtil;
import com.gxkj.taobaoservice.util.SystemDbData;
@Service
public class TaskOrderServiceImpl implements TaskOrderService {

	private static final Log log = 
			LogFactory.getLog(TaskOrderServiceImpl.class);
	
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
	
	@Autowired
	private TaskBasicDao taskBasicDao;
	
	@Autowired
	private TaskBasicLogDao taskBasicLogDao;
	
	@Autowired
	private UserAccountService userAccountService;
	
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
				jieShouRenId = 0;
			} 
			userId = jieShouRenId;
			 
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
		BigDecimal repeatPlarformGrainPoint = SystemDbData.subTaskInfoMap.get("PI_LIANG_FA_BU").getAmount();
		
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
		
		if(StringUtils.isNotBlank(goodCommentTimeLimit)){
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("GOOD_COMMENT_TIME_LIMIT");
			
			TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
			taskOrderSubTaskInfo.setInputValue(goodCommentTimeLimit);
			taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
			subTaskInfos.add(item4);
		}
		
		if(StringUtils.isNotBlank(goodCommentContent)){
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("GOOD_COMMENT_CONTENT");
			
			TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
			taskOrderSubTaskInfo.setInputValue(goodCommentContent);
			taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
			subTaskInfos.add(item4);
		}
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
			/**
			 * 批量发布
			 */
			order.setRepeatPlarformGrainPoint(repeatPlarformGrainPoint);
			 
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("PI_LIANG_FA_BU");
			if(item4.getBenefitPersion() == SubTaskInfoBenefitPerson.PLATFORM && item4.getBenefitType() == SubTaskInfoBenefitTypes.POINT){
				
				TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
				taskOrderSubTaskInfo.setInputValue(piLiangTimes+"");
				taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
				subTaskInfos.add(item4);
			}
			
		}
		if(noRepeatTalk){
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("NO_REPEAT_TASK");
			if(item4.getBenefitPersion() == SubTaskInfoBenefitPerson.PLATFORM && item4.getBenefitType() == SubTaskInfoBenefitTypes.POINT){
				zengzhiPingtaiGainPoints = zengzhiPingtaiGainPoints.add(item4.getAmount());
				
				TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
				taskOrderSubTaskInfo.setInputValue("1");
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
				taskOrderSubTaskInfo.setInputValue("1");
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
			if(item4.getBenefitPersion() == SubTaskInfoBenefitPerson.RECEIVER && item4.getBenefitType() == SubTaskInfoBenefitTypes.POINT){
				zengzhiReceiverGainPoints = zengzhiReceiverGainPoints.add(item4.getAmount());
				TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
				taskOrderSubTaskInfo.setInputValue("1");
				taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
				subTaskInfos.add(item4);
			}
		}
		if(needZhiDingSouHuoDiZhi){
			 
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("ZHI_DING_SHOU_HUO_DI_ZHI");
			if(item4.getBenefitPersion() == SubTaskInfoBenefitPerson.RECEIVER && item4.getBenefitType() == SubTaskInfoBenefitTypes.POINT){
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
			 log.info(JsonOutput.toJson(item));
			taskOrderSubTaskInfoDao.insert(item);
		}
		order.setTasks(subTaskInfos);
		order.setTaskOrderSubTaskInfos(taskOrderSubTaskInfos);
		MoneyCalculateUtil.caculateOrderAccount(order);
		
		return order;
	}
	
	 
 
	@SuppressWarnings("unchecked")
	public ListPager doPage(UserBase userBase, int pageno, int pagesize,
			Date startTime, Date endTime) throws SQLException {
		 
		ListPager pager = taskOrderDao.doPageForSite( userBase,  pageno,  pagesize,
				 startTime,  endTime);
		if(CollectionUtils.isNotEmpty(pager.getPageData())){
			List <TaskOrder> taskOrders = ((List<TaskOrder>) pager.getPageData());
			int length = taskOrders.size();
			TaskOrder order = null;
			for(int i=0;i<length;i++){
				order = taskOrders.get(i);
				MoneyCalculateUtil.caculateOrderAccount(order);
				List<TaskOrderSubTaskInfo> taskOrderSubTaskInfos = taskOrderSubTaskInfoDao.getSubTaskInfoByOrderId(order.getId());
				order.setTaskOrderSubTaskInfos(taskOrderSubTaskInfos);
			}
		}
		return pager;
	}

	/**
	 * 根据订单ID和用户ID查询订单信息
	 */
	public TaskOrder getTaskOrderByOrderIdAndUserId(Integer userId, Integer orderId)
			throws SQLException, BusinessException {
		TaskOrder taskOrder =  (TaskOrder) taskOrderDao.selectById(orderId, TaskOrder.class);
		if(taskOrder == null ){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"orderId");
		}
		if(!taskOrder.getUserId().equals(userId) ){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userId");
		}
		//查询订单关联的增值任务和基本任务
		List<TaskOrderSubTaskInfo>  taskOrderSubTaskInfos = taskOrderSubTaskInfoDao.getSubTaskInfoByOrderId(orderId);
		taskOrder.setTaskOrderSubTaskInfos(taskOrderSubTaskInfos);
		return taskOrder;
	}

	 /**
	  * 前台取消订单
	  */
	public void docancelTaskOrderByOrderIdAndUserId(Integer userId,
			Integer orderId) throws SQLException, BusinessException {
		 
		TaskOrder taskOrder =  (TaskOrder) taskOrderDao.selectById(orderId, TaskOrder.class);
		if(taskOrder == null ){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"orderId");
		}
		if(taskOrder.getUserId() != userId){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userId");
		}
		if(taskOrder.getStatus() != TaskOrderStatus.WAIT_FOR_SURE){
			throw new BusinessException(BusinessExceptionInfos.STATUS_NOT_WAIT,"status");
		}
		taskOrder.setStatus(TaskOrderStatus.CANCEL);
		taskOrderDao.update(taskOrder);
	}

	/**
	 * 前台确认订单
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void doapplyTaskOrderByOrderIdAndUserId(UserBase userBase, Integer orderId)
			throws SQLException, BusinessException, IllegalAccessException, InvocationTargetException {
		TaskOrder taskOrder =  (TaskOrder) taskOrderDao.selectById(orderId, TaskOrder.class);
		if(taskOrder == null ){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"orderId");
		}
		if(!userBase.getId().equals(taskOrder.getUserId() ) ){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userId");
		}
		if(taskOrder.getStatus() != TaskOrderStatus.WAIT_FOR_SURE){
			throw new BusinessException(BusinessExceptionInfos.STATUS_NOT_WAIT,"status");
		}
		/**
		 * 创建任务
		 */
		
		TaskBasic taskBasic = new TaskBasic();
		/**
		 * 平台受益点数
		 */
		taskBasic.setBasicPingtaiGainPoint(taskOrder.getBasicPingtaiGainPoint());
		/**
		 * 接手人佣金金额
		 */
		taskBasic.setBasicReceiverGainMoney(taskOrder.getBasicReceiverGainMoney());
		/**
		 * 接手人受益点数
		 */
		taskBasic.setBasicReceiverGainPoint(taskOrder.getBasicReceiverGainPoint());
		
		Date now = new Date();
		taskBasic.setCreateTime(now);
		
		taskBasic.setEncourage(taskOrder.getEncourage());
		/**
		 * 担保金
		 */
		taskBasic.setGuaranteePrice(taskOrder.getGuaranteePrice());
		
		taskBasic.setProductLink(taskOrder.getProductLink());
		taskBasic.setProductTitle(taskOrder.getProductTitle());
		taskBasic.setStatus(TaskStatus.Wait_For_Receive);
		taskBasic.setTaobaoXiaohao(taskOrder.getTaobaoXiaohao());
		taskBasic.setTaskOrderId(taskOrder.getId());
		taskBasic.setUserId( userBase.getId());
		taskBasic.setUserQq(taskOrder.getUserQq());
		taskBasic.setZengzhiPingtaiGainPoints(taskOrder.getZengzhiPingtaiGainPoints());
		
		/**
		 * 判断是否重复，重复次数
		 */
		Integer repeatTime =  taskOrder.getRepeateTimes();
		
		/**
		 * 确认订单
		 */
		taskOrder.setStatus(TaskOrderStatus.SURE);
		taskOrderDao.update(taskOrder);
		
		 
		/**
		 * 计算需要支付的费用，需要绑定的费用
		 * 需要支付的点数，需要绑定的点数
		 */
		
		BigDecimal lockMoney = (taskOrder.getGuaranteePrice()
				.add(taskOrder.getBasicReceiverGainMoney())
				.add(taskOrder.getEncourage())
				.add(taskOrder.getZengzhiReceiverGainMoney()))
				.multiply(new BigDecimal(taskOrder.getRepeateTimes()));
		
		BigDecimal payPoint = taskOrder.getBasicPingtaiGainPoint();
		
		BigDecimal lockPoint = (taskOrder.getBasicReceiverGainPoint()
				.add(taskOrder.getZengzhiPingtaiGainPoints())
				.add(taskOrder.getZengzhiReceiverGainPoints()))
				.multiply(new BigDecimal(taskOrder.getRepeateTimes()));
		
		
		
		
		if(repeatTime!=null && repeatTime.intValue()>1){
			/**
			 * 重复任务需要支付的平台费用
			 */
			payPoint = payPoint.add(SystemDbData.subTaskInfoMap.get("PI_LIANG_FA_BU").getAmount());
			
			for(int i=0;i<repeatTime.intValue();i++){
				TaskBasic newtaskBasic = new TaskBasic();
				BeanUtils.copyProperties(newtaskBasic, taskBasic);
				taskBasicDao.insert(newtaskBasic);
				
				TaskBasicLog taskBasicLog = new TaskBasicLog(newtaskBasic.getId(),userBase.getId(),TaskBasicLogUserType.CREATER,TaskStatus.Wait_For_Receive,now);
				taskBasicLogDao.insert(taskBasicLog);
			}
			
		}else {
			taskBasicDao.insert(taskBasic);
			
			TaskBasicLog taskBasicLog = new TaskBasicLog(taskBasic.getId(),userBase.getId(),TaskBasicLogUserType.CREATER,TaskStatus.Wait_For_Receive,now);
			 
			taskBasicLogDao.insert(taskBasicLog);
		} 
		MoneyCalculateUtil.caculateOrderAccount(taskOrder);
		
		userAccountService.updateUserAccount(userBase, BigDecimal.ZERO,lockMoney,payPoint,lockPoint,
				UserAccountTypes.Task_Order_SURE, taskOrder.getId(), null);
		
		
		 
	}



 
	public TaskOrder doUpdateTaskOrder(Integer dbOrderId, UserBase userBase,
			String taobaoXiaohao, String userQq, String productTitle,
			String productLink, BigDecimal guaranteePrice,
			BigDecimal encourage, String goodCommentTimeLimit,
			String goodCommentContent, boolean needWangWangTalk,
			boolean noRepeatTalk, boolean needZhiDingJieShouRen,
			Integer jieShouRenId, boolean needZhiDingSouHuoDiZhi,
			String shouHuoDiZhi, Integer piLiangFabuCount) throws BusinessException, SQLException {
		 
		if(userBase == null){
			throw new BusinessException(BusinessExceptionInfos.USER_IS_BLANK,"userBase");
		}
		if(dbOrderId == null || dbOrderId == 0){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"dbOrderId");
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
				jieShouRenId = 0;
			} 
			userId = jieShouRenId;
			 
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
		TaskOrder order  = 	 (TaskOrder) taskOrderDao.selectById(dbOrderId, TaskOrder.class);
		if(order == null ){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"dbOrderId");
		}
		if(!order.getUserId().equals(userBase.getId())){
			throw new BusinessException(BusinessExceptionInfos.NOT_SELF_ORDER,"userid");
		}
		if(order.getStatus() != TaskOrderStatus.WAIT_FOR_SURE ){
			throw new BusinessException(BusinessExceptionInfos.STATUS_NOT_WAIT,"status");
		}
		 
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
		BigDecimal repeatPlarformGrainPoint = SystemDbData.subTaskInfoMap.get("PI_LIANG_FA_BU").getAmount();
		
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
		
		if(StringUtils.isNotBlank(goodCommentTimeLimit)){
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("GOOD_COMMENT_TIME_LIMIT");
			
			TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
			taskOrderSubTaskInfo.setInputValue(goodCommentTimeLimit);
			taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
			subTaskInfos.add(item4);
		}
		
		if(StringUtils.isNotBlank(goodCommentContent)){
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("GOOD_COMMENT_CONTENT");
			
			TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
			taskOrderSubTaskInfo.setInputValue(goodCommentContent);
			taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
			subTaskInfos.add(item4);
		}
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
			/**
			 * 批量发布
			 */
			order.setRepeatPlarformGrainPoint(repeatPlarformGrainPoint);
			 
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("PI_LIANG_FA_BU");
			if(item4.getBenefitPersion() == SubTaskInfoBenefitPerson.PLATFORM && item4.getBenefitType() == SubTaskInfoBenefitTypes.POINT){
				
				TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
				taskOrderSubTaskInfo.setInputValue(piLiangTimes+"");
				taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
				subTaskInfos.add(item4);
			}
			
		}
		if(noRepeatTalk){
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("NO_REPEAT_TASK");
			if(item4.getBenefitPersion() == SubTaskInfoBenefitPerson.PLATFORM && item4.getBenefitType() == SubTaskInfoBenefitTypes.POINT){
				zengzhiPingtaiGainPoints = zengzhiPingtaiGainPoints.add(item4.getAmount());
				
				TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
				taskOrderSubTaskInfo.setInputValue("1");
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
				taskOrderSubTaskInfo.setInputValue("1");
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
			if(item4.getBenefitPersion() == SubTaskInfoBenefitPerson.RECEIVER && item4.getBenefitType() == SubTaskInfoBenefitTypes.POINT){
				zengzhiReceiverGainPoints = zengzhiReceiverGainPoints.add(item4.getAmount());
				TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item4);
				taskOrderSubTaskInfo.setInputValue("1");
				taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
				subTaskInfos.add(item4);
			}
		}
		if(needZhiDingSouHuoDiZhi){
			 
			SubTaskInfo item4 = SystemDbData.subTaskInfoMap.get("ZHI_DING_SHOU_HUO_DI_ZHI");
			if(item4.getBenefitPersion() == SubTaskInfoBenefitPerson.RECEIVER && item4.getBenefitType() == SubTaskInfoBenefitTypes.POINT){
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
		 * 	先删除过去的增值和基本任务
		 */
		taskOrderSubTaskInfoDao.deleteByOrderId(order.getId());
		taskOrderDao.update(order);
		/**
		 * 增值任务和基本任务持久化
		 */
		for(TaskOrderSubTaskInfo item : taskOrderSubTaskInfos){
			 item.setTaskOrderId(order.getId());
			 log.info(JsonOutput.toJson(item));
			taskOrderSubTaskInfoDao.insert(item);
		}
		order.setTasks(subTaskInfos);
		order.setTaskOrderSubTaskInfos(taskOrderSubTaskInfos);
		MoneyCalculateUtil.caculateOrderAccount(order);
		
		return order;
	}



	/**
	 * 后台分页查看订单 
	 */
	public ListPager doPageForAdmin(int pageno, int pagesize, String product_title,
			TaskOrderStatus status, Integer userId, Date beginTime, Date endTime,String taobao,String qq)
			throws SQLException {
	 
		ListPager pager = taskOrderDao.doPageForAdmin( pageno,  pagesize,  product_title,
				 status,  userId,  beginTime,  endTime,taobao,qq);
		return pager;
	}



 
	public TaskOrder getTaskOrderByOrderId(int id) throws SQLException, BusinessException {
		TaskOrder taskOrder =  (TaskOrder) taskOrderDao.selectById(id, TaskOrder.class);
		if(taskOrder == null ){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"orderId");
		}
		 
		//查询订单关联的增值任务和基本任务
		List<TaskOrderSubTaskInfo>  taskOrderSubTaskInfos = taskOrderSubTaskInfoDao.getSubTaskInfoByOrderId(id);
		taskOrder.setTaskOrderSubTaskInfos(taskOrderSubTaskInfos);
		return taskOrder;
	}
	
	

}
