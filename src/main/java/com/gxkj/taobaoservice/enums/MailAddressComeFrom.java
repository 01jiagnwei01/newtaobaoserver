package com.gxkj.taobaoservice.enums;

public enum MailAddressComeFrom {

	QQ("qq"),BLOB("博客"),OTHER("其他") ;
	 
	private String name; 
	
	private MailAddressComeFrom(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
