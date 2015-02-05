package com.gxkj.taobaoservice.enums;

public enum MailTempleteTypes {
	JU_LAI_BAO_TUI_GUANG_V1("聚来宝推广");
 
	 private final String string;
	 
	 private MailTempleteTypes(String string) {
	        this.string = string;
	  }
	 public String toString() {
	        return this.string;
	  }
	 public String getString() {
	        return this.string;
	  }
	 
}
