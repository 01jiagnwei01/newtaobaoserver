package com.gxkj.taobaoservice.enums;

public enum SubTaskInfoStatus {
	
 	NORMAL("正常"),DEL("删除"),PAUSE("暂停使用");
	
	private String name; 
	
	private SubTaskInfoStatus(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}