package com.gxkj.taobaoservice.enums;

public enum FindBackPasswordProcessResult {
	 EMAIL_IS_BLANK("邮箱为空"),
	 YANZHENGMA_IS_BLANK("验证码为空"),
	 YANZHENGMA_IS_ERROR("验证码错误"),
	 EMAIL_NOT_EXIT("邮箱不存在"),
	 USER_NOT_NORMAL("用户没有处于正常状态"),
	 SEND_FAILURE("发送失败请待会重试"),
	 SUCCESS("成功");
	
	private String name; 
	private FindBackPasswordProcessResult(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
