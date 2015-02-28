package com.gxkj.taobaoservice.enums;

public enum SubTaskInfoBenefitTypes {

Money("现金"),POINT("点数");
	
	private String name; 
	
	private SubTaskInfoBenefitTypes(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
