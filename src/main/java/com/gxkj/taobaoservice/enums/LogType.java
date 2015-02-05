package com.gxkj.taobaoservice.enums;

public enum LogType {
 
	LOGIN("登陆"),LOGOUT ("退出"),FIND_PASSWORD("找回密码");
	
	private String name; 
	
	private LogType(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
