package com.gxkj.taobaoservice.enums;

public enum StoryTypes {

	MYTH("神话");
	
	private String name; 
	
	private StoryTypes(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
