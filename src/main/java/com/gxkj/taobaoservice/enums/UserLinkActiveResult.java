package com.gxkj.taobaoservice.enums;

public enum UserLinkActiveResult {
	
	ID_OR_EMAIL_ERRORE_FAILER("ID或者EMAIL错误"),HAVE_PASS_ACTIVE_DATE_FAILER("已经过期失效错误"),SUCCESS("操作成功") ;
	
	private String name; 
	
	private UserLinkActiveResult(String name ) {  
	       this.name = name; 
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
