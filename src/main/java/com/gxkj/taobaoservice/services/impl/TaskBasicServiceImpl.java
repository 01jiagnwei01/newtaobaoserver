package com.gxkj.taobaoservice.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.gxkj.taobaoservice.daos.TaskOrderSubTaskInfoDao;
import com.gxkj.taobaoservice.entitys.TaskBasic;
import com.gxkj.taobaoservice.entitys.TaskBasicLog;
import com.gxkj.taobaoservice.entitys.TaskOrderSubTaskInfo;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.TaskBasicLogUserType;
import com.gxkj.taobaoservice.enums.TaskStatus;
import com.gxkj.taobaoservice.enums.UserAccountTypes;
import com.gxkj.taobaoservice.services.TaskBasicService;
import com.gxkj.taobaoservice.services.UserAccountService;
@Service
public class TaskBasicServiceImpl implements TaskBasicService {
	
	private static final Log log =  LogFactory.getLog(TaskBasicServiceImpl.class);
	
	@Autowired
	private TaskBasicDao taskBasicDao;
	
	@Autowired
	private TaskBasicLogDao taskBasicLogDao;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private TaskOrderSubTaskInfoDao taskOrderSubTaskInfoDao;
 
	public ListPager doPage(UserBase userBase, Integer orderId, int pageno,
			int pagesize, Date startTime, Date endTime) throws SQLException, BusinessException {
		 
		if(userBase == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userBase");
		}
		ListPager pager = taskBasicDao.doPageForSite( userBase, orderId, pageno,  pagesize,
				 startTime,  endTime);
		return pager;
	}

	 
	public TaskBasic getTaskById( Integer taskId)
			throws SQLException, BusinessException {
		
		TaskBasic taskBasic = (TaskBasic) taskBasicDao.selectById(taskId, TaskBasic.class);
		if(taskBasic == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"taskId");
		} 
		List<TaskOrderSubTaskInfo>  taskOrderSubTaskInfo = taskOrderSubTaskInfoDao.getSubTaskInfoByOrderId(taskBasic.getTaskOrderId());
		taskBasic.setTaskOrderSubTaskInfos(taskOrderSubTaskInfo);
		return taskBasic;
	}


	/**
	 * 任务大厅
	 */
	public ListPager doPageForDaTing(int pageno, int pagesize, Date startTime,
			Date endTime) throws SQLException, BusinessException {
		 
		ListPager pager = taskBasicDao.doPageForSite( null, null, pageno,  pagesize,
				 startTime,  endTime);
		return pager;
	}


	/**
	 * 接单
	 */
	public TaskBasic doReceiveTask(UserBase userBase, int taskid)
			throws SQLException, BusinessException {
		if(userBase == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userBase");
		}
		TaskBasic taskBasic = (TaskBasic) taskBasicDao.selectById(taskid, TaskBasic.class);
		if(taskBasic == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"taskId");
		} 
		if(taskBasic.getStatus() != TaskStatus.Wait_For_Receive){
			throw new BusinessException(BusinessExceptionInfos.TASK_STATUS_NOT_WAIT,"status");
		}
		Integer countAllow = SystemGlobals.getIntPreference("taobao.order.user.can.receive.count",20);
		/**
		 * 查看用户接单是否超过每天允许接单总数的限制
		 */
		Date now = new Date();
		Integer haveReceivedCount = taskBasicLogDao.getOnePersonCountReceivedTaskInOneDay(userBase.getId(), now);
		if(haveReceivedCount!=null && haveReceivedCount>countAllow){
			throw new BusinessException(BusinessExceptionInfos.DAY_RECEIVE_COUNT_LIMIT,"receive_count");
		}
		List<TaskOrderSubTaskInfo>  taskOrderSubTaskInfo = taskOrderSubTaskInfoDao.getSubTaskInfoByOrderId(taskBasic.getTaskOrderId());
		if(taskOrderSubTaskInfo != null && taskOrderSubTaskInfo.size()>0){
			 
			Map<String,TaskOrderSubTaskInfo> map = new HashMap<String,TaskOrderSubTaskInfo>();
			for(TaskOrderSubTaskInfo item :taskOrderSubTaskInfo){
				map.put(item.getTaskKey(), item);
			}
			/**
			 * 如果指定接手人
			 */
			TaskOrderSubTaskInfo jieShouRen = map.get("ZHI_DING_JIE_SHOU_REN");
			if(jieShouRen != null&& StringUtils.isNotBlank(jieShouRen.getInputValue()) &&  !jieShouRen.getInputValue().equals("0")){
				if(!String.valueOf(userBase.getId()).equals( jieShouRen.getInputValue())){
					throw new BusinessException(BusinessExceptionInfos.USER_ID_NOT_ALLOW,"userId");
				}
			}
			/**
			 * 如果限制重复接手人，则不允许重复接手
			 */
			TaskOrderSubTaskInfo noRepeatTask = map.get("NO_REPEAT_TASK");
			if(noRepeatTask != null&& StringUtils.isNotBlank(noRepeatTask.getInputValue()) &&  !noRepeatTask.getInputValue().equals("0")){
				//查看该用户当天有没有接过指定用户的单，这里简单点，直接查任务表了，虽然不太精准，但是能满足大多情况，效率稍高点
				TaskBasic haveReceiveTaskBasic = taskBasicDao.getTaskBasicCountReceivedByReceiverId(now,userBase.getId(),taskBasic.getUserId());
				if(haveReceiveTaskBasic != null){
					throw new BusinessException(BusinessExceptionInfos.TASK_LIMIT_REPEAT_RECEIVE,"taks_special");
				}
			}
		}
		
		
		taskBasic.setStatus(TaskStatus.Have_Bean_Received);
		taskBasic.setReceiverId(userBase.getId());
		
		taskBasic.setReceiverTime(now); 
		taskBasicDao.update(taskBasic);
		
		TaskBasicLog taskBasicLog = new TaskBasicLog();
		taskBasicLog.setCreateTime(now);
		taskBasicLog.setStatus(TaskStatus.Have_Bean_Received);
		taskBasicLog.setTaskBasicId(taskBasic.getId());
		taskBasicLog.setUserId(userBase.getId());
		taskBasicLog.setUserType(TaskBasicLogUserType.RECEIVER);
		taskBasicLogDao.insert(taskBasicLog);
		return taskBasic;
	}


	/**
	 * 接单人确定订单完成
	 * @param userBase
	 * @param taskid
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public TaskBasic doRecierCompleteTask(UserBase userBase, int taskid)
			throws SQLException, BusinessException {
		if(userBase == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userBase");
		}
		TaskBasic taskBasic = (TaskBasic) taskBasicDao.selectById(taskid, TaskBasic.class);
		if(taskBasic == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"taskId");
		} 
		if(taskBasic.getStatus() != TaskStatus.Have_Bean_Received){
			throw new BusinessException(BusinessExceptionInfos.TASK_STATUS_NOT_HAVE_RECEIVED,"status");
		}
		if(taskBasic.getReceiverId() != userBase.getId()){
			throw new BusinessException(BusinessExceptionInfos.NOT_SELF_TASK,"userBase");
		}
		
		taskBasic.setStatus(TaskStatus.Receive_Complete);
		taskBasicDao.update(taskBasic);
		
		Date now = new Date();
		TaskBasicLog taskBasicLog = new TaskBasicLog();
		taskBasicLog.setCreateTime(now);
		taskBasicLog.setStatus(TaskStatus.Receive_Complete);
		taskBasicLog.setTaskBasicId(taskBasic.getId());
		taskBasicLog.setUserId(userBase.getId());
		taskBasicLog.setUserType(TaskBasicLogUserType.RECEIVER);
		taskBasicLogDao.insert(taskBasicLog);
		return taskBasic;
	}


	/**
	 * 任务创建人确定任务完成
	 * @param userBase
	 * @param taskid
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public TaskBasic doSureRecierCompleteTask(UserBase userBase, int taskid)
			throws SQLException, BusinessException {
		 
		if(userBase == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userBase");
		}
		TaskBasic taskBasic = (TaskBasic) taskBasicDao.selectById(taskid, TaskBasic.class);
		if(taskBasic == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"taskId");
		} 
		if(taskBasic.getStatus() != TaskStatus.Receive_Complete){
			throw new BusinessException(BusinessExceptionInfos.TASK_STATUS_NOT_COMPLETED,"status");
		}
		if(taskBasic.getUserId() != userBase.getId()){
			throw new BusinessException(BusinessExceptionInfos.NOT_SELF_TASK,"userBase");
		}
		
		taskBasic.setStatus(TaskStatus.Creater_Sure);
		taskBasicDao.update(taskBasic);
		
		Date now = new Date();
		TaskBasicLog taskBasicLog = new TaskBasicLog();
		taskBasicLog.setCreateTime(now);
		taskBasicLog.setStatus(TaskStatus.Creater_Sure);
		taskBasicLog.setTaskBasicId(taskBasic.getId());
		taskBasicLog.setUserId(userBase.getId());
		taskBasicLog.setUserType(TaskBasicLogUserType.CREATER);
		taskBasicLogDao.insert(taskBasicLog);
		
		/**
		 *	接单用户资金变化
		 */
 
		UserAccountTypes operateType =UserAccountTypes.Task_SURE;
		Integer refTableId = taskBasic.getId(); 
		userAccountService.updateUserAccount(null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, operateType, refTableId, null);
		return taskBasic;
	}


	/**
	 * 接单人放弃任务
	 */
	public TaskBasic doRergiveupTask(UserBase userBase, int taskid)
			throws SQLException, BusinessException {
		if(userBase == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userBase");
		}
		TaskBasic taskBasic = (TaskBasic) taskBasicDao.selectById(taskid, TaskBasic.class);
		if(taskBasic == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"taskId");
		} 
		if(taskBasic.getStatus() != TaskStatus.Have_Bean_Received){
			throw new BusinessException(BusinessExceptionInfos.TASK_STATUS_NOT_HaveRecived,"status");
		}
		if(taskBasic.getReceiverId() != userBase.getId()){
			throw new BusinessException(BusinessExceptionInfos.NOT_SELF_TASK,"userBase");
		}
		/**
		 * 回复到待接单状态
		 */
		taskBasic.setStatus(TaskStatus.Wait_For_Receive);
		taskBasic.setReceiverId(null);
		taskBasic.setReceiverTime(null);
		taskBasicDao.update(taskBasic);
		
		Date now = new Date();
		TaskBasicLog taskBasicLog = new TaskBasicLog();
		taskBasicLog.setCreateTime(now);
		taskBasicLog.setStatus(TaskStatus.RECEIVEER_GIVEUP);
		taskBasicLog.setTaskBasicId(taskBasic.getId());
		taskBasicLog.setUserId(userBase.getId());
		taskBasicLog.setUserType(TaskBasicLogUserType.RECEIVER);
		taskBasicLogDao.insert(taskBasicLog);
		return taskBasic;
	}


	public ListPager doMyReceiveTaskPage(UserBase userBase, Integer orderId,
			int pageno, int pagesize, Date startTime, Date endTime)
			throws SQLException, BusinessException {
		if(userBase == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userBase");
		}
		ListPager pager = taskBasicDao.doPageForSiteAndReceive( userBase, orderId, pageno,  pagesize,
				 startTime,  endTime);
		return pager;
	}

}
