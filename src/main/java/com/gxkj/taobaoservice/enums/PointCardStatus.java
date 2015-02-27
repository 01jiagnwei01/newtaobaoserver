package com.gxkj.taobaoservice.enums;

public enum PointCardStatus {
	NORMAL("正常"),PAUSE("停售"),DELERATE("删除");
	
	private String name; 
	
	private PointCardStatus(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
