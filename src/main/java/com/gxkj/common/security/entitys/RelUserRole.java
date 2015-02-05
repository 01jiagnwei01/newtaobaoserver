package com.gxkj.common.security.entitys;

import java.io.Serializable;

public class RelUserRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6777885158918003923L;

	public int id ;
	
	private int userId;
	
	private int roleId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	 

}
