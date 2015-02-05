package com.gxkj.taobaoservice.enums;

public enum TaskOrderStatus {

	WAIT_FOR_SURE("等待确认"),SURE("已确认");
	private String name; 
	private TaskOrderStatus(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
