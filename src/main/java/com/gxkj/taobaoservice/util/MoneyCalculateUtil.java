package com.gxkj.taobaoservice.util;

import java.math.BigDecimal;

import com.gxkj.taobaoservice.entitys.TaskOrder;

public class MoneyCalculateUtil {
	 
	/**
	 * 计算一个订单需要支付的合计费用
	 * @param order
	 */
	public static void caculateOrderAccount(TaskOrder order){
		
		int repeatTimes = order.getRepeateTimes();
		/**
		 * 担保金
		 */
		BigDecimal guaranteePrice = order.getGuaranteePrice();
		/**
		 * 接手人佣金
		 */
		BigDecimal basicReceiverGainMoney = order.getBasicReceiverGainMoney();
	 
		
		/**
		 * 每个订单支付平台点数
		 */
		BigDecimal basicPingtaiGainPoint = order.getBasicPingtaiGainPoint();
		
		/**
		 * 批量发布，平台受益点数
		 */
		BigDecimal piliangPingtaiGainPoint =  order.getRepeatPlarformGrainPoint();
		/**
		 * 每单完成基本任务支付接手人点数
		 */
		BigDecimal basicReceiverGainPoint = order.getBasicReceiverGainPoint();
		/**
		 * 增值任务完成，支付接手人点数
		 */
		 BigDecimal zengzhiReceiverGainPoints =order.getZengzhiReceiverGainPoints();
		 /**
			 * 增值任务完成，支付接手人金额
			 */
		 BigDecimal zengzhiReceiverGainMoney = order.getZengzhiReceiverGainMoney();
		 /**
			 * 增值任务完成，支付平台点数
			 */
		 BigDecimal zengzhiPingtaiGainPoints = order.getZengzhiPingtaiGainPoints();
		 
		 /**
		  *  总支付金额 = (担保金 + 佣金  + 增值任务完成，支付接手人金额) * repeatTimes
		  */
		 BigDecimal countPayMoney = (guaranteePrice.add(basicReceiverGainMoney).add(zengzhiReceiverGainMoney)).multiply(new BigDecimal(repeatTimes));
		
		 /**
		  *  总支付点数 = 批量发布获取的点数 +每单支付平台点数 +( 完成基本任务支付接手人点数 +  增值任务完成，支付接手人点数 + 增值任务完成，支付平台点数) * repeatTimes
		  */
		 BigDecimal countPayPoints =piliangPingtaiGainPoint.add(basicPingtaiGainPoint).add(( basicReceiverGainPoint .add(zengzhiReceiverGainPoints).add(zengzhiPingtaiGainPoints) ).multiply(new BigDecimal(repeatTimes)));
		
		 order.setCountPayMoney(countPayMoney);
		 order.setCountPayPoints(countPayPoints);
	}

}
