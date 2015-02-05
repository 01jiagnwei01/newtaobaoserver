package com.gxkj.taobaoservice.enums;

public enum UserLinkTypes {
	EMAIL("邮箱"),QQ("QQ"),TELPHONE("电话"),TAOBAO("淘宝号");
	
	private String name; 
	
	private UserLinkTypes(String name ) {  
	       this.name = name; 
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
