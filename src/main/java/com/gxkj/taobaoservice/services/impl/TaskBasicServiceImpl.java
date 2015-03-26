package com.gxkj.taobaoservice.services.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
	
	//private static final Log log =  LogFactory.getLog(TaskBasicServiceImpl.class);
	
	@Autowired
	private TaskBasicDao taskBasicDao;
	
	@Autowired
	private TaskBasicLogDao taskBasicLogDao;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private TaskOrderSubTaskInfoDao taskOrderSubTaskInfoDao;
 
	/**
	 * 分页查看用户发布的任务
	 */
	public ListPager doPage(UserBase userBase, Integer orderId, int pageno,
			int pagesize, Date startTime, Date endTime) throws SQLException, BusinessException {
		 
		if(userBase == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userBase");
		}
		ListPager pager = taskBasicDao.doPageForSite( userBase, orderId, pageno,  pagesize,
				 startTime,  endTime,null);
		return pager;
	}

	 
 


	/**
	 * 任务大厅
	 */
	public ListPager doPageForDaTing(int pageno, int pagesize, Date startTime,
			Date endTime) throws SQLException, BusinessException {
		 
		ListPager pager = taskBasicDao.doPageForSite( null, null, pageno,  pagesize,
				 startTime,  endTime,TaskStatus.Wait_For_Receive);
		return pager;
	}


	/**
	 * 接单
	 * @throws ParseException 
	 */
	public TaskBasic doReceiveTask(UserBase userBase, int taskid,String receiverIp)
			throws SQLException, BusinessException, ParseException {
		if(userBase == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userBase");
		}
		String receiverQQ = userBase.getBindQq();
		String receiverAlipay = userBase.getBindAlipay();
		if(StringUtils.isBlank(receiverQQ)){
			throw new BusinessException(BusinessExceptionInfos.QQ_IS_NO_SET,"QQ");
		}
		if(StringUtils.isBlank(receiverAlipay)){
			throw new BusinessException(BusinessExceptionInfos.ALIPAY_IS_NO_SET,"alipay");
		}
		TaskBasic taskBasic = (TaskBasic) taskBasicDao.selectById(taskid, TaskBasic.class);
		if(taskBasic == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"taskId");
		}
		if(taskBasic.getUserId().equals(userBase.getId())){
			throw new BusinessException(BusinessExceptionInfos.NO_ALLOW_SELF_RECEIVE_SELF_TASK,"userid");
		}
		if(taskBasic.getStatus() != TaskStatus.Wait_For_Receive){
			throw new BusinessException(BusinessExceptionInfos.TASK_STATUS_NOT_WAIT,"status");
		}
		Date now = new Date();
		/**
		 * 判断接手一天内是否使用该IP结果该用户的单
		 */
		boolean haveReceivedWithThisIP = taskBasicLogDao.haveReceivedWithThisIP(userBase.getId(),taskBasic.getUserId(),receiverIp,now);
		if(haveReceivedWithThisIP){
			throw new BusinessException(BusinessExceptionInfos.TASK_STATUS_NOT_WAIT,"status");
		}
		
		Integer countAllow = SystemGlobals.getIntPreference("taobao.order.user.can.receive.count",20);
		/**
		 * 查看用户接单是否超过每天允许接单总数的限制
		 */
		
		BigInteger haveReceivedCount = taskBasicLogDao.getOnePersonCountReceivedTaskInOneDay(userBase.getId(), now);
		if(haveReceivedCount!=null && haveReceivedCount.intValue()>countAllow){
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
		taskBasic.setReceiverQq(receiverQQ);
		taskBasic.setReceiverAlipay(receiverAlipay);
		
		taskBasicDao.update(taskBasic);
		
		TaskBasicLog taskBasicLog = new TaskBasicLog();
		taskBasicLog.setCreateTime(now);
		taskBasicLog.setStatus(TaskStatus.Have_Bean_Received);
		taskBasicLog.setTaskBasicId(taskBasic.getId());
		taskBasicLog.setUserId(userBase.getId());
		taskBasicLog.setUserType(TaskBasicLogUserType.RECEIVER);
		taskBasicLog.setReceiverIp(receiverIp);
		taskBasicLog.setTaskBasicCreaterId(taskBasic.getUserId());
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
		if(!taskBasic.getReceiverId().equals( userBase.getId())){
			throw new BusinessException(BusinessExceptionInfos.NOT_SELF_TASK,"userBase");
		}
		Date now = new Date();
		taskBasic.setStatus(TaskStatus.Receive_Complete);
		taskBasic.setTaskCompleteTime(now);
		taskBasicDao.update(taskBasic);
		
		
		TaskBasicLog taskBasicLog = new TaskBasicLog();
		taskBasicLog.setCreateTime(now);
		taskBasicLog.setStatus(TaskStatus.Receive_Complete);
		taskBasicLog.setTaskBasicId(taskBasic.getId());
		taskBasicLog.setUserId(userBase.getId());
		taskBasicLog.setUserType(TaskBasicLogUserType.RECEIVER);
		taskBasicLog.setTaskBasicCreaterId(taskBasic.getUserId());
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
		if(!taskBasic.getUserId().equals(userBase.getId() )){
			throw new BusinessException(BusinessExceptionInfos.NOT_SELF_TASK,"userBase");
		}
		Date now = new Date();
		taskBasic.setStatus(TaskStatus.Creater_Sure);
		taskBasic.setTaskEndTime(now);
		taskBasicDao.update(taskBasic);
		
		
		TaskBasicLog taskBasicLog = new TaskBasicLog();
		taskBasicLog.setCreateTime(now);
		taskBasicLog.setStatus(TaskStatus.Creater_Sure);
		taskBasicLog.setTaskBasicId(taskBasic.getId());
		taskBasicLog.setUserId(userBase.getId());
		taskBasicLog.setUserType(TaskBasicLogUserType.CREATER);
		taskBasicLog.setTaskBasicCreaterId(taskBasic.getUserId());
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
		if(!taskBasic.getReceiverId().equals(userBase.getId())){
			throw new BusinessException(BusinessExceptionInfos.NOT_SELF_TASK,"userBase");
		}
		/**
		 * 回复到待接单状态
		 */
		taskBasic.setStatus(TaskStatus.Wait_For_Receive);
		taskBasic.setReceiverId(null);
		taskBasic.setReceiverTime(null);
		taskBasic.setReceiverQq(null);
		taskBasic.setReceiverAlipay(null);
		
		taskBasicDao.update(taskBasic);
		
		Date now = new Date();
		TaskBasicLog taskBasicLog = new TaskBasicLog();
		taskBasicLog.setCreateTime(now);
		taskBasicLog.setStatus(TaskStatus.RECEIVEER_GIVEUP);
		taskBasicLog.setTaskBasicId(taskBasic.getId());
		taskBasicLog.setUserId(userBase.getId());
		taskBasicLog.setUserType(TaskBasicLogUserType.RECEIVER);
		taskBasicLog.setTaskBasicCreaterId(taskBasic.getUserId());
		taskBasicLogDao.insert(taskBasicLog);
		return taskBasic;
	}

/**
 * 我未完成的任务列表
 */
	public ListPager doMyReceiveTaskPage(UserBase userBase, Integer orderId,
			int pageno, int pagesize, Date startTime, Date endTime)
			throws SQLException, BusinessException {
		if(userBase == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userBase");
		}
		ListPager pager = taskBasicDao.doPageForSiteAndReceive( userBase, orderId, pageno,  pagesize,
				 startTime,  endTime,TaskStatus.Have_Bean_Received);
		return pager;
	}


	/**
	 * 我完成的任务列表
	 */
	public ListPager getMyCompletelistPage(UserBase userBase, int pageno,
			int pagesize, Date startTime, Date endTime) throws SQLException,
			BusinessException {
		 
		if(userBase == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userBase");
		}
		ListPager pager = taskBasicDao.doPageForMyfinishedTask( userBase,  pageno,  pagesize,
				 startTime,  endTime);
		return pager;
	}


 
	public ListPager doPageForAdmin(int pageno, int pagesize,
			String producttittle, TaskStatus status, Integer userId,
			Date beginTime, Date endTime, String taobao, String qq,
			String receivetaobao, String receiveqq, Date receivebeginTime,
			Date receiveendTime) throws SQLException, BusinessException {
		
		ListPager pager = taskBasicDao.doPageForAdmin( pageno, pagesize, producttittle,  status,
				userId,  beginTime,endTime,taobao,qq,receivetaobao,receiveqq,receivebeginTime,receiveendTime);
		return pager;
	}


	public TaskBasic getTaskByIdForSite(Integer taskId) throws SQLException,
			BusinessException {
	 
		TaskBasic taskBasic = (TaskBasic) taskBasicDao.selectById(taskId, TaskBasic.class);
		if(taskBasic == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"taskId");
		} 
		List<TaskOrderSubTaskInfo>  taskOrderSubTaskInfo = taskOrderSubTaskInfoDao.getSubTaskInfoByOrderId(taskBasic.getTaskOrderId());
		taskBasic.setTaskOrderSubTaskInfos(taskOrderSubTaskInfo);
		return taskBasic;
	}


	 
	public TaskBasic getTaskByIdForAdmin(Integer taskId) throws SQLException,
			BusinessException {
		TaskBasic taskBasic = (TaskBasic) taskBasicDao.selectById(taskId, TaskBasic.class);
		if(taskBasic == null){
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"taskId");
		} 
		List<TaskOrderSubTaskInfo>  taskOrderSubTaskInfo = taskOrderSubTaskInfoDao.getSubTaskInfoByOrderId(taskBasic.getTaskOrderId());
		taskBasic.setTaskOrderSubTaskInfos(taskOrderSubTaskInfo);
		//查看订单变更历史
		List<TaskBasicLog> tasklogs = taskBasicLogDao.getTaskBasicLogByTaskId(taskId);
		taskBasic.setTaskBasicLogs(tasklogs);
		return taskBasic;
	}

}
