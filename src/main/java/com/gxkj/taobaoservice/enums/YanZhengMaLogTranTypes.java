package com.gxkj.taobaoservice.enums;

public enum YanZhengMaLogTranTypes {
	Reg("注册"),Update_bind("修改绑定"),FIND_BACK_PASSWORD("找回密码"),Update_CaoZuoMa("修改操作码");
	
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
