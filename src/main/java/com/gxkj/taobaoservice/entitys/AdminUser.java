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
@Table(name="admin_user")
public class AdminUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6403480209042700148L;
	
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

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the role
	 */
	public AdminRole getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(AdminRole role) {
		this.role = role;
	}

	/**
	 * @return the menupaths
	 */
	public String getMenupaths() {
		return menupaths;
	}

	/**
	 * @param menupaths the menupaths to set
	 */
	public void setMenupaths(String menupaths) {
		this.menupaths = menupaths;
	}

	/**
	 * @return the btnMap
	 */
	public Map<String, Boolean> getBtnMap() {
		return btnMap;
	}

	/**
	 * @param btnMap the btnMap to set
	 */
	public void setBtnMap(Map<String, Boolean> btnMap) {
		this.btnMap = btnMap;
	}

	/**
	 * @return the menus
	 */
	public List<AdminMenu> getMenus() {
		return menus;
	}

	/**
	 * @param menus the menus to set
	 */
	public void setMenus(List<AdminMenu> menus) {
		this.menus = menus;
	}
	
}
