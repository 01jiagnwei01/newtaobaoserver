package com.gxkj.taobaoservice.dto;

import java.io.Serializable;

import com.gxkj.taobaoservice.enums.UserGender;

public class ShouJianRen implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6154009419323987037L;
	
	private int id ;//通讯录ID
	
	private String email;
	
	private UserGender gender;
	
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
