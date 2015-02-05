package com.gxkj.taobaoservice.enums;

public enum MailSenderStatus {
	NOSEND("未发送"),ISEXECUTED("已执行"),SEND("已发送"),SUCCESS("成功"),FAILURE ("失败"),DEL("已删除");
	
	private String name; 
	
	private MailSenderStatus(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
