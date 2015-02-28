package com.gxkj.taobaoservice.util;

import java.math.BigDecimal;

import com.gxkj.taobaoservice.entitys.TaskOrder;

public class MoneyCalculateUtil {
	
	/**
	 * 根据担保价格计算接手人受益金额
	 * @param amount
	 * @return
	 */
	public static BigDecimal caculateOrderReceiverGainMoney(double amount){
		if(amount < 0){
			return BigDecimal.ZERO;
		}
		if(amount <=100){
			return new BigDecimal(5);
		}
		if(amount <=200){
			return new BigDecimal(8);
		}
		if(amount <=500){
			return new BigDecimal(15);
		}
		return new BigDecimal(20);
	}
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
		BigDecimal basicReceiverGainMoney = caculateOrderReceiverGainMoney(guaranteePrice.doubleValue());
		/**
		 * 奖励金额
		 */
		BigDecimal encourage = order.getEncourage();
		
		/**
		 * 每单支付平台点数
		 */
		BigDecimal basicPingtaiGainPoint = order.getBasicPingtaiGainPoint();
		
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
		 BigDecimal zengzhiPingtaiGainPoints = order.getZengzhiReceiverGainPoints();
		 
		 /**
		  * (担保金 + 佣金 + 奖励金额 + 增值任务完成，支付接手人金额) * repeatTimes
		  */
		 BigDecimal countPayMoney = (guaranteePrice.add(basicReceiverGainMoney).add(encourage).add(zengzhiReceiverGainMoney)).multiply(new BigDecimal(repeatTimes));
		
		 /**
		  * (每单支付平台点数 + 完成基本任务支付接手人点数 +  增值任务完成，支付接手人金额 + 增值任务完成，支付平台点数) * repeatTimes
		  */
		 BigDecimal countPayPoints = (basicPingtaiGainPoint.add(basicReceiverGainPoint ).add(zengzhiReceiverGainPoints).add(zengzhiPingtaiGainPoints) ).multiply(new BigDecimal(repeatTimes));
		
		 order.setCountPayMoney(countPayMoney);
		 order.setCountPayPoints(countPayPoints);
	}

}
