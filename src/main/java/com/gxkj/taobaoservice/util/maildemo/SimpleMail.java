package com.gxkj.taobaoservice.util.maildemo;

import java.io.Serializable;

public class SimpleMail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3161536791127660502L;

	private String subject;
	
	private String content;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
