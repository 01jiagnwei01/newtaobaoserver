package com.gxkj.taobaoservice.util;

import com.gxkj.taobaoservice.entitys.SubTaskInfo;
import com.gxkj.taobaoservice.entitys.TaskOrderSubTaskInfo;

/**
 *
 * 实体对象装换
 */
public class EntityTransFormUtil {
	
	/**
	 * SubTaskInfo 类对象转化为TaskOrderSubTaskInfo类对象
	 * @param source
	 */
	public static TaskOrderSubTaskInfo subTaskInfo2TaskOrderSubTaskInfo(SubTaskInfo source){
		
		TaskOrderSubTaskInfo entity = new TaskOrderSubTaskInfo();
		entity.setAmount(source.getAmount());
		entity.setBenefitPersion(source.getBenefitPersion());
		entity.setBenefitType(source.getBenefitType());
		entity.setTaskKey(source.getTaskKey());
		entity.setType(source.getType());
		
		 
		return entity;
	}

}
