package com.gxkj.taobaoservice.enums;

public enum YanZhengMaTypes {
	
	phone("手机号码"),email("邮箱");
	
	private String name;
	private YanZhengMaTypes(String name ) {
			   this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
