package com.gxkj.taobaoservice.enums;

public enum YanZhengMaLogTranTypes {
	Reg("注册"),Update_bind("修改绑定");
	
	private String name; 
	private YanZhengMaLogTranTypes(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
