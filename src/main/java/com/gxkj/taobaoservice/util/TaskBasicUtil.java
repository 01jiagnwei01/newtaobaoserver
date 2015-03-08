package com.gxkj.taobaoservice.util;

import java.math.BigDecimal;

import com.gxkj.taobaoservice.entitys.TaskBasic;

public class TaskBasicUtil {

	/**
	 * 获得接手方如果接这个任务可以获得的资金奖励是多少
	 * @param taskBasic
	 * @return
	 */
	public static BigDecimal getReceiveCanGetMoneyByTask(TaskBasic taskBasic){
		/**
		 *   获利=基本任务获得的奖金（佣金）  + 额外补助+增值任务获利金额
		 */
		return  taskBasic.getBasicReceiverGainMoney().add(taskBasic.getEncourage());
		
	} 
	
	/**
	 * 获得接手方如果接这个任务可以获得的点数奖励是多少
	 * @param taskBasic
	 * @return
	 */
	public static BigDecimal getReceiveCanGetPointByTask(TaskBasic taskBasic){
		/**
		 *   获利点数=完成增值任务获利点数
		 */
		return  taskBasic.getZengzhiReceiverGainPoints();
		
	} 
}
