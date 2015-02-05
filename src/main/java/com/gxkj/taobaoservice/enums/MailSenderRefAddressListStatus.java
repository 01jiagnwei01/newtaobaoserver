package com.gxkj.taobaoservice.enums;

public enum MailSenderRefAddressListStatus {
	NOSEND("未发送"),SUCCESS("成功"),FAILURE("失败") ;
	 
	private String name; 
	
	private MailSenderRefAddressListStatus(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
