package com.gxkj.common.security.entitys;

import java.io.Serializable;

public class AdminMenu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1330484762867735406L;

	private int id;
	
	/**
	 * 菜单名称或按钮名称
	 */
	private String name;
	
	/**
	 * springSecurity资源保护时需要的角色
	 */
	private String springSecurityRole;
	
	/**
	 * 菜单路径或按钮触发路径
	 */
	private String path;
	
	/**
	 * 按钮ID,前台显示使用
	 */
	private String buttonId;
	
	/**
	 * 按钮还是菜单 二选一  0：菜单  1：按钮
	 */
	private int isButton = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpringSecurityRole() {
		return springSecurityRole;
	}

	public void setSpringSecurityRole(String springSecurityRole) {
		this.springSecurityRole = springSecurityRole;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	public int getIsButton() {
		return isButton;
	}

	public void setIsButton(int isButton) {
		this.isButton = isButton;
	}
	
	
	

}
