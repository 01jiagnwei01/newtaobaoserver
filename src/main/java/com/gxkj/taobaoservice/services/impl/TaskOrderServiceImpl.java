package com.gxkj.taobaoservice.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.taobaoservice.daos.TaskOrderDao;
import com.gxkj.taobaoservice.daos.UserAccountDao;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.TaskOrder;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.TaskOrderStatus;
import com.gxkj.taobaoservice.services.TaskOrderService;
@Service
public class TaskOrderServiceImpl implements TaskOrderService {

	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private Validator validator; 
	
	private TaskOrderDao taskOrderDao;
	
	/**
	 * 创建任务订单
	 * @throws SQLException 
	 */
	public EntityReturnData addTaskOrder(TaskOrder taskOrder, UserBase userBase) throws SQLException {
		Assert.assertNotNull(taskOrder);
		Assert.assertNotNull(userBase);
		
		
		taskOrder.setUserId(userBase.getId());
		taskOrder.setStatus(TaskOrderStatus.WAIT_FOR_SURE);
		Date now = new Date();
		taskOrder.setCreateTime(now);
		/**
		 * 默认每个订单支付平台0.5个平台币
		 */
		taskOrder.setPayedDot(new BigDecimal("0.5"));
		Set<ConstraintViolation<TaskOrder>> constraintViolations = validator.validate(taskOrder);
		EntityReturnData result = new EntityReturnData();
		if(constraintViolations.size() != 0){
			Iterator<ConstraintViolation<TaskOrder>> it =  constraintViolations.iterator();
			while(it.hasNext()){
				result.setMsg(it.next().getMessage());
				return result;
			}
		}
		UserAccount userAccount = userAccountDao.getUserAccountByUserId(userBase.getId());
		/**
		 * 判断各项费用够不够
		 */
		
		//没有错误则计算各项费用再入库
		taskOrderDao.insert(taskOrder);
		result.setResult(true);
		result.setEntity(taskOrder);
		
		return result;
	}
	public static void main(String[] args) {
		
	}
	
	

}
