package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="rel_admin_user_role")
public class RelAdminUserRole  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7778442849502427658L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private int id;
	
	@Column(name="admin_user_id" )
	private int adminUserId;
	
	@Column(name="admin_role_id" )
	private int adminRoleId;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the adminUserId
	 */
	public int getAdminUserId() {
		return adminUserId;
	}

	/**
	 * @param adminUserId the adminUserId to set
	 */
	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	/**
	 * @return the adminRoleId
	 */
	public int getAdminRoleId() {
		return adminRoleId;
	}

	/**
	 * @param adminRoleId the adminRoleId to set
	 */
	public void setAdminRoleId(int adminRoleId) {
		this.adminRoleId = adminRoleId;
	}
	
	
	

}
