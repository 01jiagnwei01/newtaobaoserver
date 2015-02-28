package com.gxkj.taobaoservice.enums;

public enum SubTaskInfoTypes {
	BASIC("基本任务"),APPRECIATION("增值业务");
	
	private String name; 
	
	private SubTaskInfoTypes(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}