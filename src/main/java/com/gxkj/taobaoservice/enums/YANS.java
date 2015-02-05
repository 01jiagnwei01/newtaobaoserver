package com.gxkj.taobaoservice.enums;

public enum YANS {

	YES("是"),NO("否");
	
	private String name; 
	
	private YANS(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
