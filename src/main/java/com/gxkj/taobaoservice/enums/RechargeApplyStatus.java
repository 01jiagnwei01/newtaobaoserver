package com.gxkj.taobaoservice.enums;

public enum RechargeApplyStatus {
	WAIT_FOR_AUDIT("等待审核"),REFUSE("拒绝"),APPROVE("通过") ;
	
	private String name; 
	
	private RechargeApplyStatus(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
