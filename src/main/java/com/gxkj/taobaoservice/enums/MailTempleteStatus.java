package com.gxkj.taobaoservice.enums;

public enum MailTempleteStatus {

	NORMAL ("正常"),DELETE("已删除");
	private String name; 
	private MailTempleteStatus(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
