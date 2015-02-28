package com.gxkj.taobaoservice.enums;

public enum SubTaskInfoBenefitPerson {
	Receiver("接手人"),Platform("平台");
	
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

