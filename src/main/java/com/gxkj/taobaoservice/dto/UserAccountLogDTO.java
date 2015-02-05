package com.gxkj.taobaoservice.dto;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.gxkj.taobaoservice.entitys.UserAccountLog;

public class UserAccountLogDTO extends UserAccountLog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7021272263083310863L;

	/**
	 * 前台用户用户名
	 */
	@Transient
	@Column(name="userName")
	private String userName;

	/**
	 * 管理员名称
	 */
	@Transient
	@Column(name="adminName")
	private String adminName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
}
