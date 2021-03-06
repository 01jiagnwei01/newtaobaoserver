package com.gxkj.taobaoservice.services.impl;

import groovy.json.JsonOutput;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	 * @param commission_			佣金
	 * @param basicPingtaiGainPoint  平台基本佣金点数
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
			String shouHuoDiZhi, Integer piLiangFabuCount, BigDecimal commission_, BigDecimal basicPingtaiGainPoint) throws SQLException,
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
		if( BigDecimal.ZERO.compareTo(guaranteePrice) >=0){
			throw new BusinessException(BusinessExceptionInfos.guaranteePrice__SHOULD_BE_POSITIVE,"guaranteePrice");
		}
		if(commission_.compareTo(BigDecimal.ZERO)<0){
			throw new BusinessException(BusinessExceptionInfos.basicReceiverGainMoney_CANNOT_BE_NEGATIVE,"commission");
		}
		if(basicPingtaiGainPoint.compareTo(BigDecimal.ZERO)<0){
			log.error("付给平台的佣金不能为负数");
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"basicPingtaiGainPoint");
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
			if(userId == userBase.getId().intValue()){
				throw new BusinessException(BusinessExceptionInfos.NO_ALLOW_SPECIAL_RECEIVER_IS_SELF,"jieShouRenId");
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
		 
		TaskOrder order = new TaskOrder();
		 
		/**
		 * 基本任务和增值任务的集合
		 */
		 List<TaskOrderSubTaskInfo> taskOrderSubTaskInfos = new ArrayList<TaskOrderSubTaskInfo>();
		 List<SubTaskInfo> subTaskInfos = new ArrayList<SubTaskInfo>();
		 BigDecimal payPingTaiPoints = BigDecimal.ZERO;
		
		/**
		 * 创建时间
		 */
		Date now = new Date();
		order.setCreateTime(now);
		 
		/**
		 *  每单担保金
		 */
		order.setGuaranteePrice(guaranteePrice);
		
		/**
		 * 佣金
		 */
		order.setCommission(commission_);
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
		 * 该订单支付平台的佣金点数
		 */
		order.setBasicPingtaiGainPoint(basicPingtaiGainPoint);
		payPingTaiPoints = payPingTaiPoints.add(basicPingtaiGainPoint);
		
		
		Map<String,SubTaskInfo> subTaskInfoMap =  SystemDbData.subTaskInfoMap ;
		
		Iterator<Map.Entry<String, SubTaskInfo>> it = subTaskInfoMap.entrySet().iterator();
		  while (it.hasNext()) {
			  Map.Entry<String, SubTaskInfo> entry = it.next();
			  SubTaskInfo item =  entry.getValue();
		   		String taskKey = item.getTaskKey();
		   		if(item.getAmount().compareTo(BigDecimal.ZERO)<0){
		   			continue;
		   		}
		   		switch(taskKey){
		   			case "GOOD_COMMENT_TIME_LIMIT":
		   				if(StringUtils.isNotBlank(goodCommentTimeLimit)){
							TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item);
							taskOrderSubTaskInfo.setInputValue(goodCommentTimeLimit);
							taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
							subTaskInfos.add(item);
							
							this.__calculateSureOrder(item, order);
						}
		   				
		   				break;
		   			case "GOOD_COMMENT_CONTENT":
		   				if(StringUtils.isNotBlank(goodCommentContent)){
							TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item);
							taskOrderSubTaskInfo.setInputValue(goodCommentContent);
							taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
							subTaskInfos.add(item);
							
							this.__calculateSureOrder(item, order);
						}
		   				
		   				break;
		   			case "NEED_WANGWANG_TALK":
		   				if(needWangWangTalk){
							TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item);
							taskOrderSubTaskInfo.setInputValue("1");
							taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
							subTaskInfos.add(item);
							
							this.__calculateSureOrder(item, order);
						}
		   				break;
		   			case "ZHI_DING_SHOU_HUO_DI_ZHI":
		   				if(needZhiDingSouHuoDiZhi){
							TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item);
							taskOrderSubTaskInfo.setInputValue(shouHuoDiZhi);
							taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
							subTaskInfos.add(item);
							
							this.__calculateSureOrder(item, order);
						}
		   				break;
		   			case "ZHI_DING_JIE_SHOU_REN":
		   				if(needZhiDingJieShouRen){
							TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item);
							taskOrderSubTaskInfo.setInputValue(""+jieShouRenId);
							taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
							subTaskInfos.add(item);
							
							this.__calculateSureOrder(item, order);
						}
		   				break;
		   			case "PI_LIANG_FA_BU":
		   				if(piLiangTimes>=2){
		   					TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item);
							taskOrderSubTaskInfo.setInputValue(""+piLiangTimes);
							taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
							subTaskInfos.add(item);
							
							//批量发布，支付点数 
							payPingTaiPoints = payPingTaiPoints.add(item.getAmount());
		   				}
		   				
		   				break;
		   			case "NO_REPEAT_TASK":
		   				if(noRepeatTalk){
		   					TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item);
							taskOrderSubTaskInfo.setInputValue("1");
							taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
							subTaskInfos.add(item);
							
							this.__calculateSureOrder(item, order);
		   				}
		   		}
		  }
		  /**
		   * 每个任务支付接手人金额=所有增值任务支付金额+佣金+担保金
		   */
		  order.setEveryTaskPayReceiverMoney(order.getEveryTaskPayReceiverMoney().add(order.getCommission()).add(order.getGuaranteePrice()));
		  //
		  order.setPayPingTaiPoints(payPingTaiPoints);
		  order.setPayPingTaiMoney(BigDecimal.ZERO);
		  
		 
		
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
		if(!taskOrder.getUserId().equals( userId) ){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userId");
		}
		if(taskOrder.getStatus() != TaskOrderStatus.WAIT_FOR_SURE){
			if(taskOrder.getStatus() == TaskOrderStatus.CANCEL){
				throw new BusinessException(BusinessExceptionInfos.STATUS_IS_CANCEL,"status");
			}
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
		 * 接手（佣金）金额
		 */
		taskBasic.setCommission(taskOrder.getCommission());
		/**
		 * 支付平台金额
		 */
		taskBasic.setPayPingTaiMoney(taskOrder.getEveryTaskPayPingtaiMoney());
		/**
		 * 支付平台点数
		 */
		taskBasic.setPayPingTaiPoints(taskOrder.getEveryTaskPayPingtaiPoints());
		/**
		 * 接手人受益点数
		 */
		taskBasic.setPayReceiverPoints(taskOrder.getEveryTaskPayReceiverPoints());
		/**
		 * 接手人受益金额
		 */
		taskBasic.setPayReceiverMoney(taskOrder.getEveryTaskPayReceiverMoney());
		
		 
		
		Date now = new Date();
		taskBasic.setCreateTime(now);
		 
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
		 
		/**
		 * 判断是否重复，重复次数
		 */
		Integer repeatTime =  taskOrder.getRepeateTimes();
		
		/**
		 * 确认订单
		 */
		taskOrder.setStatus(TaskOrderStatus.SURE);
		taskOrderDao.update(taskOrder);
		
 
		
		if(repeatTime!=null && repeatTime.intValue()>1){
			/**
			 * 重复任务需要多创建任务
			 */
			
			for(int i=0;i<repeatTime.intValue();i++){
				TaskBasic newtaskBasic = new TaskBasic();
				BeanUtils.copyProperties(newtaskBasic, taskBasic);
				taskBasicDao.insert(newtaskBasic);
				
				TaskBasicLog taskBasicLog = new TaskBasicLog(newtaskBasic.getId(),userBase.getId(),TaskBasicLogUserType.CREATER,TaskStatus.Wait_For_Receive,now,userBase.getId());
				taskBasicLogDao.insert(taskBasicLog);
			}
			
		}else {
			taskBasicDao.insert(taskBasic);
			TaskBasicLog taskBasicLog = new TaskBasicLog(taskBasic.getId(),userBase.getId(),TaskBasicLogUserType.CREATER,TaskStatus.Wait_For_Receive,now,userBase.getId());
			taskBasicLogDao.insert(taskBasicLog);
		} 
		
		/**
		 * 修改用户金额和支付平台金额
		 */
		userAccountService.updateUserAccount(userBase, BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,
				UserAccountTypes.Task_Order_SURE, taskOrder.getId(), null);
		
		
		 
	}


	public TaskOrder doUpdateTaskOrder(Integer dbOrderId, UserBase userBase,
			String taobaoXiaohao, String userQq, String productTitle,
			String productLink, BigDecimal guaranteePrice,
			BigDecimal encourage, String goodCommentTimeLimit,
			String goodCommentContent, boolean needWangWangTalk,
			boolean noRepeatTalk, boolean needZhiDingJieShouRen,
			Integer jieShouRenId, boolean needZhiDingSouHuoDiZhi,
			String shouHuoDiZhi, Integer piLiangFabuCount,
			BigDecimal commission_, BigDecimal basicPingtaiGainPoint)
			throws BusinessException, SQLException {
 
		 
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
		if( BigDecimal.ZERO.compareTo(guaranteePrice) >=0){
			throw new BusinessException(BusinessExceptionInfos.guaranteePrice__SHOULD_BE_POSITIVE,"guaranteePrice");
		}
		if(commission_.compareTo(BigDecimal.ZERO)<0){
			throw new BusinessException(BusinessExceptionInfos.basicReceiverGainMoney_CANNOT_BE_NEGATIVE,"commission_");
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
		 * 佣金
		 */
		order.setCommission(commission_);
	 
		
		/**
		 * 创建时间
		 */
		Date now = new Date();
		order.setCreateTime(now);
		 
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
		 * 基本受益点数
		 */
		 BigDecimal payPingTaiPoints = order.getBasicPingtaiGainPoint();
		 
		Map<String,SubTaskInfo> subTaskInfoMap =  SystemDbData.subTaskInfoMap ;
		TaskOrder calCulateTaskOrder = new TaskOrder();
		Iterator<Map.Entry<String, SubTaskInfo>> it = subTaskInfoMap.entrySet().iterator();
		  while (it.hasNext()) {
			  Map.Entry<String, SubTaskInfo> entry = it.next();
			  SubTaskInfo item =  entry.getValue();
		   		String taskKey = item.getTaskKey();
		   		if(item.getAmount().compareTo(BigDecimal.ZERO)<0){
		   			continue;
		   		}
		   		switch(taskKey){
		   			case "GOOD_COMMENT_TIME_LIMIT":
		   				if(StringUtils.isNotBlank(goodCommentTimeLimit)){
							TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item);
							taskOrderSubTaskInfo.setInputValue(goodCommentTimeLimit);
							taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
							subTaskInfos.add(item);
							
							this.__calculateSureOrder(item, calCulateTaskOrder);
						}
		   				
		   				break;
		   			case "GOOD_COMMENT_CONTENT":
		   				if(StringUtils.isNotBlank(goodCommentContent)){
							TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item);
							taskOrderSubTaskInfo.setInputValue(goodCommentContent);
							taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
							subTaskInfos.add(item);
							
							this.__calculateSureOrder(item, calCulateTaskOrder);
						}
		   				
		   				break;
		   			case "NEED_WANGWANG_TALK":
		   				if(needWangWangTalk){
							TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item);
							taskOrderSubTaskInfo.setInputValue("1");
							taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
							subTaskInfos.add(item);
							
							this.__calculateSureOrder(item, calCulateTaskOrder);
						}
		   				break;
		   			case "ZHI_DING_SHOU_HUO_DI_ZHI":
		   				if(needZhiDingSouHuoDiZhi){
							TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item);
							taskOrderSubTaskInfo.setInputValue(shouHuoDiZhi);
							taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
							subTaskInfos.add(item);
							
							this.__calculateSureOrder(item, calCulateTaskOrder);
						}
		   				break;
		   			case "ZHI_DING_JIE_SHOU_REN":
		   				if(needZhiDingJieShouRen){
							TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item);
							taskOrderSubTaskInfo.setInputValue(""+jieShouRenId);
							taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
							subTaskInfos.add(item);
							
							this.__calculateSureOrder(item, calCulateTaskOrder);
						}
		   				break;
		   			case "PI_LIANG_FA_BU":
		   				if(piLiangTimes>=2){
		   					TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item);
							taskOrderSubTaskInfo.setInputValue(""+piLiangTimes);
							taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
							subTaskInfos.add(item);
							 
							payPingTaiPoints = item.getAmount();
		   				}
		   				
		   				break;
		   			case "NO_REPEAT_TASK":
		   				if(noRepeatTalk){
		   					TaskOrderSubTaskInfo taskOrderSubTaskInfo = EntityTransFormUtil.subTaskInfo2TaskOrderSubTaskInfo(item);
							taskOrderSubTaskInfo.setInputValue("1");
							taskOrderSubTaskInfos.add(taskOrderSubTaskInfo);
							subTaskInfos.add(item);
							
							this.__calculateSureOrder(item, calCulateTaskOrder);
		   				}
		   				;
		   		}
		  }
		  /**
		   * 该订单支付平台点数
		   */
		  order.setPayPingTaiPoints(payPingTaiPoints);
		   
		
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
			 log.info("创建增值任务："+JsonOutput.toJson(item));
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
	
	/**
	 * 工具方法，根据增值认为设置增量
	 * @param subTaskInfo  增值任务
	 * @param calCulateTaskOrder	计算用任务
	 */
	private void __calculateSureOrder(SubTaskInfo subTaskInfo,TaskOrder calCulateTaskOrder){
		
		if(subTaskInfo.getAmount().compareTo(BigDecimal.ZERO)<0){
   			return;
   		}else if(subTaskInfo.getAmount().compareTo(BigDecimal.ZERO)==0){
   			return;
   		} 
		/**
			 * 平台获利点增加
			 */
			if(subTaskInfo.getBenefitPersion() == SubTaskInfoBenefitPerson.PLATFORM && subTaskInfo.getBenefitType() == SubTaskInfoBenefitTypes.POINT){
			 
				calCulateTaskOrder.setEveryTaskPayPingtaiPoints(calCulateTaskOrder.getEveryTaskPayPingtaiPoints()  .add(subTaskInfo.getAmount()));
			}else if(subTaskInfo.getBenefitPersion() == SubTaskInfoBenefitPerson.PLATFORM && subTaskInfo.getBenefitType() == SubTaskInfoBenefitTypes.Money){
			  /**
			   * 平台获利金额 平台获利不设置获利金额
			   */
				calCulateTaskOrder.setEveryTaskPayPingtaiMoney(calCulateTaskOrder.getEveryTaskPayPingtaiMoney().add( subTaskInfo.getAmount()));
				 
			}else if(subTaskInfo.getBenefitPersion() == SubTaskInfoBenefitPerson.RECEIVER && subTaskInfo.getBenefitType() == SubTaskInfoBenefitTypes.POINT){
   			  /**
   			   * 接手获利点数
   			   */
				calCulateTaskOrder.setEveryTaskPayReceiverPoints(calCulateTaskOrder.getEveryTaskPayReceiverPoints().add(subTaskInfo.getAmount()));
			 
   		}else if(subTaskInfo.getBenefitPersion() == SubTaskInfoBenefitPerson.RECEIVER && subTaskInfo.getBenefitType() == SubTaskInfoBenefitTypes.Money){
   			  /**
   			   * 接手获利金额
   			   */
   			calCulateTaskOrder.setEveryTaskPayReceiverMoney(calCulateTaskOrder.getEveryTaskPayReceiverMoney().add(subTaskInfo.getAmount()));
   		}
	}


 
	
	

}
