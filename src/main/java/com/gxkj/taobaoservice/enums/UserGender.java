package com.gxkj.taobaoservice.enums;

public enum UserGender {
	male("男"),female("女"),unknow("未知");
	
	private String name; 
	
	private UserGender(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
