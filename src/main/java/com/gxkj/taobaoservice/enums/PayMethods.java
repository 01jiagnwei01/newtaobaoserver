package com.gxkj.taobaoservice.enums;

public enum PayMethods {
	DoTaskPay("接手方付款"),CreateTaskPay("发布方付款"),RedPaper ("红包支付");
	private String name; 
	private PayMethods(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 

}
