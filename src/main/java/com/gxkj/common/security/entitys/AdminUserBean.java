package com.gxkj.common.security.entitys;

import java.io.Serializable;
import java.util.List;

public class AdminUserBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1117945372243415377L;

	private int id ;
	
	private String userName;
	
	private String password;
	
	
	private List<AdminRole> roles;
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<AdminRole> getRoles() {
		return roles;
	}
	public void setRoles(List<AdminRole> roles) {
		this.roles = roles;
	}
	 
	 
}
