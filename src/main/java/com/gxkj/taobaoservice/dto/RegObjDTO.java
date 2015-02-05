package com.gxkj.taobaoservice.dto;

import java.io.Serializable;
/**
 * 注册时提交的对象
 *
 */
public class RegObjDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8056923538829354524L;
	
	private String userName;
	
	private String email;
	
	private String password;
	
	private String rePassword;
	
	private String yanzhengma;
	
	private String ip;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getYanzhengma() {
		return yanzhengma;
	}

	public void setYanzhengma(String yanzhengma) {
		this.yanzhengma = yanzhengma;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	

}
