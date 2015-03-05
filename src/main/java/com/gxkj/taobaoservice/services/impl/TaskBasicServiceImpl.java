package com.gxkj.taobaoservice.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
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
		taskBasic.setStatus(TaskStatus.Have_Bean_Received);
		taskBasic.setReceiverId(userBase.getId());
		Date now = new Date();
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
			throw new BusinessException(BusinessExceptionInfos.NOT_SELF_ORDER,"userBase");
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
			throw new BusinessException(BusinessExceptionInfos.NOT_SELF_ORDER,"userBase");
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
		BigDecimal payamount = taskBasic.getEncourage().add(taskBasic.getGuaranteePrice() ).add(taskBasic.getBasicReceiverGainMoney())  ;
		BigDecimal lockAmount  = BigDecimal.ZERO;
		BigDecimal payPoints  = taskBasic.getZengzhiPingtaiGainPoints().add(taskBasic.getZengzhiReceiverGainPoints());
		BigDecimal lockPoints  = BigDecimal.ZERO;
		UserAccountTypes operateType =UserAccountTypes.Task_SURE;
		Integer refTableId = taskBasic.getId();
		UserBase  receiverUserBase= new UserBase();
		receiverUserBase.setId(taskBasic.getReceiverId());
		userAccountService.updateUserAccount(receiverUserBase, payamount, lockAmount, payPoints, lockPoints, operateType, refTableId, null);
		return taskBasic;
	}

}
