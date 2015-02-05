package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.TaskOrder;
import com.gxkj.taobaoservice.entitys.UserBase;

public interface TaskOrderService {
	
	 
	/**
	 * 创建任务订单
	 * @param taskOrder
	 * @param userBase
	 * @return
	 * @throws SQLException 
	 */
	public EntityReturnData addTaskOrder(TaskOrder taskOrder, UserBase userBase) throws SQLException;

}
