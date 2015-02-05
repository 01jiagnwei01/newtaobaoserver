package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
 

@Entity
@Table(name = "admin_role")
public class AdminRole implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2609671432337033186L;

	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer id;
	
	@Column(name="name" )
	private String name;
	
	@Column(name="status")
	private Integer status;
	
	@Transient
	List<AdminMenu> relMenus;
	
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

	public int getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<AdminMenu> getRelMenus() {
		return relMenus;
	}
	public void setRelMenus(List<AdminMenu> relMenus) {
		this.relMenus = relMenus;
	}
	
	

}
