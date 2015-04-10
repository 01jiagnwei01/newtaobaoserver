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
		 * 每个任务支付平台点数
		 */
		BigDecimal everyTaskPayPingtaiPoints = order.getEveryTaskPayPingtaiPoints();
		
		/**
		 * 每个任务支付平台金额
		 */
		BigDecimal everyTaskPayPingtaiMoney =  order.getEveryTaskPayPingtaiMoney();
		/**
		 * 每个任务支付接手人点数
		 */
		BigDecimal everyTaskPayReceiverPoints = order.getEveryTaskPayReceiverPoints();
		/**
		 * 每个任务支付支付接手人金额
		 */
		 BigDecimal everyTaskPayReceiverMoney =order.getEveryTaskPayReceiverMoney();
		 
		 /**
			 * 该订单支付平台点数
			 */
		 BigDecimal payPingTaiPoints = order.getPayPingTaiPoints();
		 /**
		 * 该订单支付平台金额
		 */
		 BigDecimal payPingTaiMoney = order.getPayPingTaiMoney();
		 
		 
		 /**
		  *  总支付金额 = 订单支付平台金额 + （(每单支付平台金额 + 每单支付接手人金额) * repeatTimes）
		  */
		 BigDecimal countPayMoney = payPingTaiMoney.add( (everyTaskPayPingtaiMoney.add(everyTaskPayReceiverMoney)).multiply(new BigDecimal(repeatTimes)));
		
		 /**
		  *  总支付点数 = 订单支付平台点数 + （(每单支付平台点数 + 每单支付接手人点数) * repeatTimes）
		  */
		 BigDecimal countPayPoints = payPingTaiPoints.add( (everyTaskPayPingtaiPoints.add(everyTaskPayReceiverPoints)).multiply(new BigDecimal(repeatTimes)));
		
		 order.setCountPayMoney(countPayMoney);
		 order.setCountPayPoints(countPayPoints);
	}

}
