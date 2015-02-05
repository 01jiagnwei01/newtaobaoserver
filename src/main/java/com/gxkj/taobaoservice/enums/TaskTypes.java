package com.gxkj.taobaoservice.enums;

public enum TaskTypes {
	VIRTUAL("虚拟"), PRACTICALITY ("实物");

	private String name; 
	
	private TaskTypes(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
