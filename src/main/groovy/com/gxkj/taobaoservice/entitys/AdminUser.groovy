package com.gxkj.taobaoservice.entitys

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

class AdminUser implements Serializable{
	
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	 Integer id;
	
	@Column(name="name" )
	 String name;
	
	
	@Column(name="password" )
	 String password;
	
	@Column(name="real_name" )
	 String realName;
	
	@Column(name="status" )
	 Integer status;
	
	@Transient
	 AdminRole role;
	
	@Transient
	 String menupaths = "";
	
	@Transient
	 List<AdminMenu> menus = new ArrayList<AdminMenu>();
	
	@Transient
	 Map<String ,Boolean> btnMap = new HashMap<String ,Boolean>();

}
