package com.gxkj.taobaoservice.enums;

public enum PicStatus {

	NORMAL("正常"),DELERATE("删除");
	
	private String name; 
	
	private PicStatus(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
