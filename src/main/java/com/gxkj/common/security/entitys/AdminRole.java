package com.gxkj.common.security.entitys;

import java.io.Serializable;

public class AdminRole implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1091298384475185941L;
	private int id;
	private String roleName;
	
	
	public AdminRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminRole(int roleId, String roleName, String roleDesc) {
		super();
		this.id = roleId;
		this.roleName = roleName;
		 
	}
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
