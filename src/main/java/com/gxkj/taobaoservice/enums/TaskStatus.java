package com.gxkj.taobaoservice.enums;

public enum TaskStatus {
	Wait_For_Receive("等待接手");
	private String name; 
	private TaskStatus(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
