package com.gxkj.taobaoservice.enums;

public enum TaskStatus {
	Wait_For_Receive("等待接手"),Have_Bean_Received("已被接单"),Receive_Complete("接单人完成任务"),Creater_Sure("任务结束")
	,RECEIVEER_GIVEUP("接单人放弃任务");
	private String name; 
	private TaskStatus(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
