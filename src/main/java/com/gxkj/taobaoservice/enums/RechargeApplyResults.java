package com.gxkj.taobaoservice.enums;

public enum RechargeApplyResults {
	THIRD_ORDER_No_IS_BLANK("第三方流水号为空"),
	AMOUNT_IS_BLANK("第三方流水号为空"),
	SUCCESS("成功");
	
	private String name; 
	
	private RechargeApplyResults(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
