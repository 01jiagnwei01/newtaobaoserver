package com.gxkj.taobaoservice.enums;

public enum ValidType {

	EMAIL_REGISTER("注册"),EMAIL_FIND_PASSWORD("找回密码") ;
	
	private String name; 
	
	private ValidType(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
