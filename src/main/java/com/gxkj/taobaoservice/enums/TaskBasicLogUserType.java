package com.gxkj.taobaoservice.enums;

public enum TaskBasicLogUserType {

	
	RECEIVER("接单人"),CREATER("创建人"),ADMINER("管理员");
	
	private String name; 
	
	private TaskBasicLogUserType(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static void main(String[] args) {
		System.out.println(TaskBasicLogUserType.RECEIVER);
	}
}
