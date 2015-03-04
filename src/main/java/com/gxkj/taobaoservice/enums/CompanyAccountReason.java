package com.gxkj.taobaoservice.enums;

public enum CompanyAccountReason {
	DRAW("取款"),DEPOSIT("充值"),ORDERSURE("订单确认"),SellPoint("卖点卡");
	
	private String name; 
	private CompanyAccountReason(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
