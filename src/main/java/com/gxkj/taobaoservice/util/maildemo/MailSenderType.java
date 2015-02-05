package com.gxkj.taobaoservice.util.maildemo;

public enum MailSenderType {

	SERVICE("服务器") ;
	
	 private final String string;
	 
	 private MailSenderType(String string) {
	        this.string = string;
	  }
	 public String toString() {
	        return this.string;
	  }
}
