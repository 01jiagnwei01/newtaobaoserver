package com.gxkj.taobaoservice.enums;

public enum RegLogTypes {
	
	phone("手机号码"),email("邮箱");
	
	private String name;
	private RegLogTypes(String name ) {
			   this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
