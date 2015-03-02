package com.gxkj.taobaoservice.enums;

public enum UserAccountTypes {
	DEPOSIT("充值"),WITHDRAW_SUCCESS("取款成功"),WITHDRAW_APPLY("取款申请"),WITHDRAW_FAILURE("取款失败")
	,BUY_POINTS("买点"),Task_Order_SURE("订单确认"),POINTS2MONEY("点兑钱"),
	PUBLISH_TASK ("发布任务"),FINISH_TASK("发布任务"),CANCLE_TASK("任务取消"),
	ADMINISTRATOR_INTERVENTION("管理员干预"),COMPANY_SUPPLY("平台赞助");
	
	private String name; 
	private UserAccountTypes(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
