package com.gxkj.taobaoservice.enums;

public enum MailAddressListStatus {

	NORMAL("正常"),DEL("删除") ;
	
	private String name; 
	
	private MailAddressListStatus(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
