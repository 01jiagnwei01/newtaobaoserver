package com.gxkj.taobaoservice.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.CompanyAccountDao;
import com.gxkj.taobaoservice.daos.TaskBasicDao;
import com.gxkj.taobaoservice.daos.TaskOrderDao;
import com.gxkj.taobaoservice.daos.TaskOrderSubTaskInfoDao;
import com.gxkj.taobaoservice.daos.UserAccountDao;
import com.gxkj.taobaoservice.daos.UserAccountLogDao;
import com.gxkj.taobaoservice.entitys.TaskBasic;
import com.gxkj.taobaoservice.entitys.TaskOrder;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserAccountLog;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.CompanyAccountReason;
import com.gxkj.taobaoservice.enums.UserAccountTypes;
import com.gxkj.taobaoservice.services.UserAccountService;
@Service
public class UserAccountServiceImpl implements UserAccountService {

	private static final Log log = 
			LogFactory.getLog(UserAccountServiceImpl.class);
	
	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private UserAccountLogDao userAccountLogDao;
	
	@Autowired
	private CompanyAccountDao companyAccountDao;
	
	@Autowired
	private TaskBasicDao taskBasicDao;
	
	@Autowired
	private TaskOrderDao taskOrderDao;
	
	@Autowired
	private TaskOrderSubTaskInfoDao taskOrderSubTaskInfoDao;
	
	/**
	 * 关联修改用户账户信息，并完成log日志
	 * 
	 * 订单确认时refTableId必填写，值为任务ID
	 * @param userBase
	 * @param payamount  付款金额
	 * @param lockAmount  锁定金额
	 * @param payPoints	 付款点数
	 * @param lockPoints	锁定点数
	 * @param operateType
	 * @param refTableId
	 * @return
	 * @throws BusinessException 
	 * @throws SQLException 
	 */
	public boolean updateUserAccount(UserBase userBase, BigDecimal payamount,BigDecimal lockAmount,
			BigDecimal payPoints,BigDecimal lockPoints, UserAccountTypes operateType, Integer refTableId,Integer adminUserId) throws BusinessException, SQLException {
		 TaskBasic taskBasic =  null;
		 TaskOrder taskOrder =  null;
		if(operateType == null){
			log.info(String.format("操作方式为空,operateType = null"));
			throw new BusinessException(BusinessExceptionInfos.UserAccountTypes_IS_NULL,"operateType");
		}
		if(operateType == UserAccountTypes.Task_SURE){
			 if( refTableId == null || refTableId == 0 ){
				 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"refTableId");
			 }
			 taskBasic = (TaskBasic) taskBasicDao.selectById(refTableId, TaskBasic.class);
			 userBase = new UserBase();
			 /**
			  * userBase为创建任务的人
			  */
			 userBase.setId(taskBasic.getUserId());
			 /**
			  * 支付金额 =  	付给平台金额 + 付给接单人金额
			  * 支付点数 =     付给平台金额 + 付给接单人金额
			  */
			 payamount = taskBasic.getPayPingTaiMoney().add(taskBasic.getPayReceiverMoney());
			 lockAmount = BigDecimal.ZERO;
			 payPoints = taskBasic.getPayPingTaiPoints().add(taskBasic.getPayReceiverPoints());
			 lockPoints = BigDecimal.ZERO;
		}else if(UserAccountTypes.Task_Order_SURE == operateType){
			 if( refTableId == null || refTableId == 0 ){
				 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"refTableId");
			 }
			  taskOrder =  (TaskOrder) taskOrderDao.selectById(refTableId, TaskOrder.class);
			 /**
			  * 支付金额为支付平台金额
			  * 绑定金额 = （ 每个任务支付平台金额 + 每个任务支付接手金额 ）* 重复次数
			  * 支付点数 = 支付平台点数
			  * 绑定点数 = （ 每个任务支付平台点数 + 每个任务支付接手人点数 ）* 重复次数
			  */
			 payamount = taskOrder.getPayPingTaiMoney();
			 lockAmount = (
					 taskOrder.getEveryTaskPayPingtaiMoney().add(taskOrder.getEveryTaskPayReceiverMoney())
					 	).multiply(new BigDecimal(taskOrder.getRepeateTimes()));
			 payPoints = taskOrder.getPayPingTaiPoints();
			lockPoints = (taskOrder.getEveryTaskPayPingtaiPoints()
					.add(taskOrder.getEveryTaskPayReceiverPoints()))
					.multiply(new BigDecimal(taskOrder.getRepeateTimes()));

		}
		if(userBase == null){
			log.info(String.format("参数错误,userBase=null"));
			 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userBase");
		}
		if(userBase.getId() == null || userBase.getId().intValue() <=0){
			log.info(String.format("参数错误,userBase.getId()=%d",userBase.getId()));
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userBase");
		}
		if(payamount.compareTo(BigDecimal.ZERO)<0){
			log.info(String.format("参数错误,payamount需要是正数,payamount=%d",payamount));
			 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"payamount");
		}
		if(lockAmount.compareTo(BigDecimal.ZERO)<0){
			log.info(String.format("参数错误,lockAmount需要是正数,lockAmount=%d",payamount));
			 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"lockAmount");
		}
		
		if(payPoints.compareTo(BigDecimal.ZERO)<0){
			log.info(String.format("参数错误,payPoints需要是正数,payPoints=%d",payPoints));
			 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"payamount");
		}
		if(lockPoints.compareTo(BigDecimal.ZERO)<0){
			log.info(String.format("参数错误,lockPoints需要是正数,lockPoints=%d",payamount));
			 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"lockPoints");
		} 
		UserAccount uerAccount = userAccountDao.getUserAccountByUserId(userBase.getId());
		
		//当前可用余额
		BigDecimal currentBalance = uerAccount.getCurrentBalance();
		//当前锁定金额
		BigDecimal currentLockedBalance = uerAccount.getLockedBalance();
		//当前可用点数
		BigDecimal currentPoints = uerAccount.getCurrentRestPoints();
		//当前锁定点数
		BigDecimal currentLockedPoints = uerAccount.getLockedPoints();
		
		//记录用户账户变化
		UserAccountLog userAccountLog = new UserAccountLog();
		userAccountLog.setType(operateType);
		//操作前锁定金额
		userAccountLog.setBeforeLockedAmount(currentLockedBalance);
		//操作前锁定点数
		userAccountLog.setBeforeLockedPoints(currentLockedPoints);
		//操作前可用金额
		userAccountLog.setBeforeRestAmount(currentBalance);
		//操作前可用点数
		userAccountLog.setBeforeRestPoints(currentPoints);
		
		userAccountLog.setAdminUserId(adminUserId);
		userAccountLog.setUserId(userBase.getId());
		Date now = new Date();
		userAccountLog.setCreateTime(now);
		
		/**
		 * 操作后可用金额，可用点数,锁定金额，锁定点数
		 */
		BigDecimal afterAmount = currentBalance;
		BigDecimal afterPoints = currentPoints;
		BigDecimal afterLockedAmount = currentLockedBalance;
		BigDecimal afterLockedPoints = currentLockedPoints;
		
		
		switch(operateType){
			case DEPOSIT:
				/**
				 * 充值成功,
				 */
				if(payamount.compareTo(BigDecimal.ZERO)<=0){
					log.info(String.format("参数错误,amount需要是正数,amount=%d",payamount));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"amount");
				}
				if(refTableId == null || refTableId.intValue()<=0){
					log.info(String.format("参数错误,关联充值记录表ID需要是正数,refTableId=%d",refTableId));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"refTableId");
				}
				/**
				 * 账户金额增加，其他不变
				 */
				afterAmount = currentBalance.add(payamount);
				
				//关联充值申请表ID
				userAccountLog.setDepositApplyLogId(refTableId);
				userAccountLog.setReason("充值成功");
				
				/**
				 * 公司账户增加
				 */
				companyAccountDao.executeUpdateCompanyAccount(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, payamount, BigDecimal.ZERO
						,CompanyAccountReason.DEPOSIT,refTableId, BigDecimal.ZERO,"用户【"+userBase.getId()+"】充值【"+payamount+"】元");
				
				 
				break;
			case WITHDRAW_APPLY:
				/**
				 * 取款申请,
				 */
				if(payamount.compareTo(BigDecimal.ZERO)<=0){
					log.info(String.format("参数错误,amount需要是正数,amount=%d",payamount));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"amount");
				}
				if(refTableId == null || refTableId.intValue()<=0){
					log.info(String.format("参数错误,关联取款申请记录表ID需要是正数,refTableId=%d",refTableId));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"refTableId");
				}
				if(currentBalance.compareTo(payamount)<0){
					log.info(String.format("余额不足，取款金额是：%d,当前余额是:%d",payamount.doubleValue(),currentBalance.doubleValue()));
					 throw new BusinessException(BusinessExceptionInfos.AMOUNT_MONEY_NOT_ENOUGH,"amount");
				}
				/**
				 * 锁定金额增加，可用余额减少
				 */
				afterAmount = currentBalance.subtract(payamount);
				afterLockedAmount = currentLockedBalance.add(payamount);
				// 关联取款申请表
				userAccountLog.setDrawLogId(refTableId);
				userAccountLog.setReason("取款申请，锁定资金【"+payamount+"】元");
				
				break;
			case WITHDRAW_FAILURE:
				//取款申请失败
				if(payamount.compareTo(BigDecimal.ZERO)<=0){
					log.info(String.format("参数错误,amount需要是正数,amount=%d",payamount));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"amount");
				}
				if(refTableId == null || refTableId.intValue()<=0){
					log.info(String.format("参数错误,关联取款申请记录表ID需要是正数,refTableId=%d",refTableId));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"refTableId");
				}
				
				/**
				 * 取款申请失败,
				 * 可用余额增加,锁定金额减少
				 *
				 */
				afterAmount = currentBalance.add(payamount);
				afterLockedAmount = currentLockedBalance.subtract(payamount);
				// 关联取款申请表
				userAccountLog.setDrawLogId(refTableId);
				userAccountLog.setReason("取款失败，资金回归可用资金【"+payamount+"】元");
				
				break;
			case WITHDRAW_SUCCESS:
				//取款成功
				if(payamount.compareTo(BigDecimal.ZERO)==0){
					log.info(String.format("参数错误,amount需要是正数,amount=%d",payamount));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"amount");
				}
				if(refTableId == null || refTableId.intValue()==0){
					log.info(String.format("参数错误,关联取款申请记录表ID需要是正数,refTableId=%d",refTableId));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"refTableId");
				}
				
				/**
				 * 取款申请成功,
				 * 锁定金额减少，其他不变
				 *
				 */
				afterLockedAmount = currentLockedBalance.subtract(payamount);
				// 关联取款申请表
				userAccountLog.setDrawLogId(refTableId);
				userAccountLog.setReason("取款成功，取出资金【"+payamount+"】元");
				
				/**
				 * 公司账户增加
				 */
				companyAccountDao.executeUpdateCompanyAccount(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, payamount,CompanyAccountReason.DRAW,refTableId, BigDecimal.ZERO
						, "用户【"+userBase.getId()+"】取款【"+payamount+"】元");
				
				break;
			case BUY_POINTS:
				/**
				 * 买点卡
				 */
				if(payamount.compareTo(BigDecimal.ZERO)<=0){
					log.info(String.format("参数错误,amount需要是正数,amount=%d",payamount));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"amount");
				}
				if(payPoints.compareTo(BigDecimal.ZERO)<=0){
					log.info(String.format("参数错误,points需要是正数,points=%d",payPoints));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"points");
				}
				if(currentBalance.compareTo(payamount)<0){
					log.info(String.format("余额不足，取款金额是：%d,当前余额是:%d",payamount.doubleValue(),currentBalance.doubleValue()));
					 throw new BusinessException(BusinessExceptionInfos.AMOUNT_MONEY_NOT_ENOUGH,"amount");
				}
				/**
				 * 可用金额减少,可用点数增加
				 */
				afterAmount = currentBalance.subtract(payamount);
				afterPoints = currentPoints.add(payPoints);
				userAccountLog.setReason("购买点卡");
				
				/**
				 * 公司账户增加
				 */
				companyAccountDao.executeUpdateCompanyAccount(payPoints, payamount,  BigDecimal.ZERO,  BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO ,CompanyAccountReason.SellPoint,refTableId
						,BigDecimal.ZERO,"卖出【"+payPoints+"】点,获得收入【"+payamount+"】元");
				
				 
				break;
			case Task_Order_SURE:
				/**
				 * 订单确认 
				 * 锁定账户，绑定资金
				 */
				 
				if(lockAmount.compareTo(BigDecimal.ZERO)<0 ){
					log.info(String.format("参数错误,lockAmount需要是正数,lockAmount=%d",lockAmount));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"lockAmount");
				}
				 
				if(payPoints!=null && payPoints.compareTo(BigDecimal.ZERO)<0){
					log.info(String.format("参数错误,points需要是正数,payPoints=%.2f",payPoints));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"points");
				}
				if( currentPoints.compareTo(lockPoints)<0){
					log.info(String.format("可用点数不足，当前可用点数是：%.2f,需要绑定点数:%.2f",currentPoints.doubleValue(),lockPoints.doubleValue()));
					 throw new BusinessException(BusinessExceptionInfos.POINT_NOT_ENOUGH,"points");
				}
				if(refTableId == null || refTableId.intValue()==0){
					log.info(String.format("参数错误,关联取款申请记录表ID需要是正数,refTableId=%.2f",refTableId));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"refTableId");
				}
				
				// 任务创建者的账户变化关联订单表Id
				userAccountLog.setTaskOrderId(refTableId);
				userAccountLog.setReason("订单【"+refTableId+"】完成，支付资金，担保金绑定");
				/**
				 * 任务创建者
				 * 锁定资金减少，所用点数减少 可用资金、点数不变
				 */
				/**
				 * 可用金额 = 可用金额 - 付款金额 - 锁定金额
				 * 可用点数 = 可用点数 - 付款点数 -锁定点数
				 * 锁定金额 = 当前锁定金额 + 锁定金额
				 * 锁定点数 = 锁定点数 + 锁定点数
				 * 支付公司点数
				 */
				afterAmount = currentBalance.subtract(payamount).subtract(lockAmount);
				afterPoints = currentPoints.subtract(payPoints).subtract(lockPoints);;
				afterLockedAmount = currentLockedBalance.add(lockAmount);
				afterLockedPoints = currentLockedPoints.add(lockPoints);
				/**
				 * 公司账户增加
				 * 获利金额 = 支付平台金额
				 * 	获得点数 = 支付平台点数
				 */
				
				companyAccountDao.executeUpdateCompanyAccount(BigDecimal.ZERO, BigDecimal.ZERO, 
						taskOrder.getPayPingTaiPoints(),  BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO ,
						CompanyAccountReason.ORDERSURE,refTableId,
						taskOrder.getPayPingTaiMoney(),
						"订单确定，公司收入【"+taskOrder.getPayPingTaiPoints()+"】点"
								+""
								+((taskOrder.getPayPingTaiMoney().compareTo(BigDecimal.ZERO) == 0) ? "":",收入【"+taskOrder.getPayPingTaiMoney()+"】元")
						);
				
				 
				
				break;
			case Task_SURE:
				/**
				 * 任务正常结束，接单人完成任务，创建者确定
				 */
				
				if(lockAmount.compareTo(BigDecimal.ZERO)<0 ){
					log.info(String.format("参数错误,lockAmount需要是正数,lockAmount=%d",lockAmount));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"lockAmount");
				}
				 
				if(payPoints!=null && payPoints.compareTo(BigDecimal.ZERO)<0){
					log.info(String.format("参数错误,points需要是正数,payPoints=%.2f",payPoints));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"points");
				}
				if( currentPoints.compareTo(lockPoints)<0){
					log.info(String.format("可用点数不足，当前可用点数是：%.2f,需要绑定点数:%.2f",currentPoints.doubleValue(),lockPoints.doubleValue()));
					 throw new BusinessException(BusinessExceptionInfos.POINT_NOT_ENOUGH,"points");
				}
				if(refTableId == null || refTableId.intValue()==0){
					log.info(String.format("参数错误,关联取款申请记录表ID需要是正数,refTableId=%.2f",refTableId));
					 throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"refTableId");
				}
				// 任务创建者的账户变化关联任务表Id
				userAccountLog.setTaskBasicId(refTableId);
				userAccountLog.setReason("任务【"+refTableId+"】完成，支付资金");
				/**
				 * 任务创建者
				 * 锁定资金减少，锁定点数减少 可用资金、点数不变
				 * 锁定资金 = 锁定资金 - 支付资金
				 * 锁定点数 = 锁定点数 - 支付点数
				 */
				afterLockedAmount = afterLockedAmount.subtract(payamount);
				afterLockedPoints = afterLockedPoints.subtract(payPoints);
				
				
				/**
				 * 修改公司账户
				 */
				if(taskBasic.getPayPingTaiMoney().compareTo(BigDecimal.ZERO) >0 || taskBasic.getPayPingTaiPoints().compareTo(BigDecimal.ZERO) >0){
					companyAccountDao.executeUpdateCompanyAccount( BigDecimal.ZERO,  BigDecimal.ZERO,  
							taskBasic.getPayPingTaiPoints(),  BigDecimal.ZERO, 
							BigDecimal.ZERO, BigDecimal.ZERO ,CompanyAccountReason.ORDERSURE,refTableId,taskBasic.getPayPingTaiMoney(),"任务【"+refTableId+"】完成");
				}
				
				/**
				 * 接单人员账户变化
				 */
				_receiverUserCountChangeForTaskOrderSURE(now ,taskBasic);
				break;
		default:
			break;
				
			
		
		}
		if( operateType != UserAccountTypes.DEPOSIT 
				&&  operateType != UserAccountTypes.WITHDRAW_APPLY 
				&&  operateType != UserAccountTypes.WITHDRAW_FAILURE 
				&&  operateType != UserAccountTypes.WITHDRAW_SUCCESS
				&&  operateType != UserAccountTypes.BUY_POINTS
				&&  operateType != UserAccountTypes.Task_Order_SURE
				&&  operateType != UserAccountTypes.Task_SURE){
			/**
			 * 标识出支持的
			 */
			 throw new BusinessException(BusinessExceptionInfos.undo,"operateType");
		}
		/**
		 * 操作后验证
		 */
		if(afterAmount.compareTo(BigDecimal.ZERO)<0){
			log.error(String.format("参数错误,可用金额不能为负数,操作支付金额为payamount=%.2f,锁定金额是lockAmount=%.2f，操作前金额为：%.2f",payamount.doubleValue(),
					lockAmount.doubleValue(),currentBalance.doubleValue()));
			 throw new BusinessException(BusinessExceptionInfos.AMOUNT_MONEY_NOT_ENOUGH,"amount");
		}
		if(afterPoints.compareTo(BigDecimal.ZERO)<0){
			log.error(String.format("参数错误,可用点数不能为负数,操作点数为payPoints=%.2f，锁定点数为lockPoints=%.2f，操作前点数为：%.2f",payPoints.doubleValue(),lockPoints.doubleValue(),
					currentPoints.doubleValue()));
			 throw new BusinessException(BusinessExceptionInfos.POINT_NOT_ENOUGH,"points");
		}
		
		if(afterLockedAmount.compareTo(BigDecimal.ZERO)<0){
			log.error(String.format("参数错误,锁定金额不能为负数,操作支付金额为payamount=%.2f,锁定金额是lockAmount=%.2f，操作前锁定金额为：%.2f",payamount.doubleValue(),
					lockAmount.doubleValue(),currentLockedBalance.doubleValue()));
			 throw new BusinessException(BusinessExceptionInfos.AMOUNT_MONEY_NOT_ENOUGH,"amount");
		}
		
		if(afterLockedPoints.compareTo(BigDecimal.ZERO)<0){
			log.error(String.format("参数错误,可用点数不能为负数,操作点数为payPoints=%.2f，锁定点数为lockPoints=%.2f，操作前锁定点数为：%.2f",payPoints.doubleValue(),lockPoints.doubleValue(),currentLockedPoints.doubleValue()));
			throw new BusinessException(BusinessExceptionInfos.POINT_NOT_ENOUGH,"points");
		}
		uerAccount.setCurrentBalance(afterAmount);
		uerAccount.setCurrentRestPoints(afterPoints);
		uerAccount.setLockedBalance(afterLockedAmount);
		uerAccount.setLockedPoints(afterLockedPoints);
		userAccountDao.update(uerAccount);
		 
		//操作金额
		 
		userAccountLog.setPayAmount(payamount);
		userAccountLog.setLockAmount(lockAmount);
		userAccountLog.setPayPoints(payPoints);
		userAccountLog.setLockPoint(lockPoints);
		
		//操作后可用金额
		userAccountLog.setAfterRestAmount(afterAmount);
		//操作后锁定金额
		userAccountLog.setAfterLockedAmount(afterLockedAmount);
		//操作后可用点数
		userAccountLog.setAfterRestPoints(afterPoints);
		//操作后锁定点数
		userAccountLog.setAfterLockedPoints(afterLockedPoints);
		
		userAccountLogDao.insert(userAccountLog);
		
		userBase.setUerAccount(uerAccount);
		return true;
	}
	/**
	 * 订单确认时 接单人账户变化 
	 * 接单人  可用金额增加,可用点数增加
	 * @param receiverId
	 * @param changePoints
	 * @param changeAmount
	 * @param refTableId 关联表ID
	 * @throws SQLException 
	 */
	private void _receiverUserCountChangeForTaskOrderSURE(Date now ,TaskBasic taskBasic) throws SQLException{
			
		UserAccount uerAccount = userAccountDao.getUserAccountByUserId(taskBasic.getReceiverId());
		
		//当前可用余额
		BigDecimal currentBalance = uerAccount.getCurrentBalance();
		//当前锁定金额
		BigDecimal currentLockedBalance = uerAccount.getLockedBalance();
		//当前可用点数
		BigDecimal currentPoints = uerAccount.getCurrentRestPoints();
		//当前锁定点数
		BigDecimal currentLockedPoints = uerAccount.getLockedPoints();
		
		/**
		 * 订单确认接单人账户变化
		 * 变化金额 =  担保金 + 佣金 + 增值任务获利金
		 * 变化点数 =  接单人增值任务获利点数
		 * 
		 */
		BigDecimal changeAmount = taskBasic.getPayReceiverMoney();
		BigDecimal changePoints = taskBasic.getPayReceiverPoints();
		if (changeAmount.compareTo(BigDecimal.ZERO) == 0 && changePoints.compareTo(BigDecimal.ZERO) == 0){
			return;
		}
		
		BigDecimal afterAmount = currentBalance.add(changeAmount);
		BigDecimal afterPoints = currentPoints.add(changePoints);
		BigDecimal afterLockedAmount = currentLockedBalance;
		BigDecimal afterLockedPoints = currentLockedPoints;
		
		
		uerAccount.setCurrentBalance(afterAmount);
		uerAccount.setCurrentRestPoints(afterPoints);
		uerAccount.setLockedBalance(afterLockedAmount);
		uerAccount.setLockedPoints(afterLockedPoints);
		userAccountDao.update(uerAccount);
		
		
		//记录用户账户变化
		UserAccountLog userAccountLog = new UserAccountLog();
		userAccountLog.setType(UserAccountTypes.Task_SURE);
		//操作前锁定金额
		userAccountLog.setBeforeLockedAmount(currentLockedBalance);
		//操作前锁定点数
		userAccountLog.setBeforeLockedPoints(currentLockedPoints);
		//操作前可用金额
		userAccountLog.setBeforeRestAmount(currentBalance);
		//操作前可用点数
		userAccountLog.setBeforeRestPoints(currentPoints);
		
		userAccountLog.setAdminUserId(null);
		userAccountLog.setUserId(taskBasic.getReceiverId());
		userAccountLog.setCreateTime(now);
		userAccountLog.setTaskBasicId(taskBasic.getId()); 
		
		userAccountLog.setPayAmount(changeAmount);
		userAccountLog.setPayPoints(changePoints);
		
		//操作后可用金额
		userAccountLog.setAfterRestAmount(afterAmount);
		//操作后锁定金额
		userAccountLog.setAfterLockedAmount(afterLockedAmount);
		//操作后可用点数
		userAccountLog.setAfterRestPoints(afterPoints);
		//操作后锁定点数
		userAccountLog.setAfterLockedPoints(afterLockedPoints);

		userAccountLog
				.setReason("完成任务【"
						+ taskBasic.getId()
						+ "】，获得担保金【"
						+ taskBasic.getGuaranteePrice()
						+ "】"
						+ (taskBasic.getCommission().compareTo(BigDecimal.ZERO) == 0 ? ""
								: "获得佣金【" + taskBasic.getCommission() + "】"));

		userAccountLogDao.insert(userAccountLog);
		
	}
	 
	public ListPager doPage(UserBase userBase, int pageno, int pagesize,
			Date startTime, Date endTime) throws BusinessException,
			SQLException {
		 
		return userAccountLogDao.doPageForSite( userBase,  pageno,  pagesize,
				 startTime,  endTime);
	}
	public UserAccount getUserAccountByUserBaseId(Integer userBaseId)
			throws   SQLException {
		 
		return userAccountDao.getUserAccountByUserId(userBaseId);
	}

}
