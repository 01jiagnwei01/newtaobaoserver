package com.gxkj.taobaoservice.enums;

public enum MenuType {
	isButton("按钮"),
	isMenu("菜单");
	
	 private final String string;
	 
	 private MenuType(String string) {
	        this.string = string;
	  }
	 public String toString() {
	        return this.string;
	  }

}
