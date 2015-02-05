package com.gxkj.taobaoservice.enums;

public enum LoginProcessResults {
	USER_NAME_BLANK_FAILURE("用户名为空")
	,PASSWORD_BLANK_FAILURE("密码为空")
	,YANGZHENGMA_BLANK_FAILURE("验证码为空")
	,USER_NAME_OR_PASSWORD_ERROR_FAILURE("用户名或密码错误")
	,YANGZHENGMA_ERROR("验证码错误")
	,SUCCESS("登陆成功");
	
	private String name; 
	
	private LoginProcessResults(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
