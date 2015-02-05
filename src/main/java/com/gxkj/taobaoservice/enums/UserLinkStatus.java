package com.gxkj.taobaoservice.enums;

public enum UserLinkStatus {

	NORMAL("正常"),WAIT_ACTIVE("待激活"),ACTIVED("已激活"),DEL("删除");
	
	private String name; 
	
	private UserLinkStatus(String name ) {  
	       this.name = name; 
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
