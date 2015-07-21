package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "admin_user")
public class AdminUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6649680034799009138L;

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name="name" )
	private String name;
	
	
	@Column(name="password" )
	private String password;
	
	@Column(name="real_name" )
	private String realName;
	
	@Column(name="status" )
	private Integer status;
	
	@Transient
	private AdminRole role;
	
	@Transient
	private String menupaths = "";
	
	@Transient
	private List<AdminMenu> menus = new ArrayList<AdminMenu>();
	
	@Transient
	private Map<String ,Boolean> btnMap = new HashMap<String ,Boolean>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public AdminRole getRole() {
		return role;
	}

	public void setRole(AdminRole role) {
		this.role = role;
	}

	public String getMenupaths() {
		return menupaths;
	}

	public void setMenupaths(String menupaths) {
		this.menupaths = menupaths;
	}

	public List<AdminMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<AdminMenu> menus) {
		this.menus = menus;
	}

	public Map<String, Boolean> getBtnMap() {
		return btnMap;
	}

	public void setBtnMap(Map<String, Boolean> btnMap) {
		this.btnMap = btnMap;
	}
	
	
}
