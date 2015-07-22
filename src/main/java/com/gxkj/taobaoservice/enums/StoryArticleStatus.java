package com.gxkj.taobaoservice.enums;

public enum StoryArticleStatus {
	WAIT4REVIEW("待审核"),NORMAL("正常"),NOPASS("审核未通过"),DEL("删除");
	
	private String name; 
	
	private StoryArticleStatus(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
