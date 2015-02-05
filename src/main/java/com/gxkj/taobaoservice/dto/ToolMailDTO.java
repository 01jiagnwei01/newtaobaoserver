package com.gxkj.taobaoservice.dto;

import java.io.Serializable;

public class ToolMailDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6291648071499832670L;
	
	private String email;
	
	private String subject;
	
	private String  content;
	
	private Integer dbTableId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public Integer getDbTableId() {
		return dbTableId;
	}

	public void setDbTableId(Integer dbTableId) {
		this.dbTableId = dbTableId;
	}

	 
	

}
