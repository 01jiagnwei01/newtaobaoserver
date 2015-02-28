package com.gxkj.taobaoservice.enums;

public enum SubTaskInfoBenefitPerson {
	RECEIVER("接手人"),PLATFORM("平台"),FREE("免费");
	
	private String name; 
	
	private SubTaskInfoBenefitPerson(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

