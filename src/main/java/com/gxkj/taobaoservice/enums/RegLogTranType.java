package com.gxkj.taobaoservice.enums;

public enum RegLogTranType {

	REG("注册"),UPDATE_BIND_EMAIL("修改绑定邮箱");
	
	private String name; 
	private RegLogTranType(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
