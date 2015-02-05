package com.gxkj.taobaoservice.enums;

public enum UserBaseStatus {
	
	WAIT_FOR_ACTIVE("待激活"),
	NORMAL("正常"),
	LOCKED("锁定");
	
	private String name; 
	private UserBaseStatus(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
