package com.gxkj.common.security.entitys;

import java.io.Serializable;

public class RelAdminRoleMenu implements Serializable {

	 

	/**
	 * 
	 */
	private static final long serialVersionUID = 547169299679751505L;

	public int id ;
	
	private int roleId;
	
	private int menuid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getMenuid() {
		return menuid;
	}

	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}
	
	
}
