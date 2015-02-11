package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.gxkj.taobaoservice.enums.UserLinkStatus;
import com.gxkj.taobaoservice.enums.UserLinkTypes;
@Entity
@Table(name = "user_link")
public class UserLink implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2891322948100848983L;

	
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer id;
	
	@Column(name = "user_id",  nullable = false) 
	private Integer userId;
	
	@Column(name = "link_type",  nullable = false) 
	@Enumerated(EnumType.STRING)
	private UserLinkTypes linkType;
	
	@Column(name = "link_value",  nullable = false,length=50) 
	private String linkValue;
	
	@Column(name = "status",  nullable = false)
	@Enumerated(EnumType.STRING)
	private UserLinkStatus status;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public UserLinkTypes getLinkType() {
		return linkType;
	}

	public void setLinkType(UserLinkTypes linkType) {
		this.linkType = linkType;
	}

	public String getLinkValue() {
		return linkValue;
	}

	public void setLinkValue(String linkValue) {
		this.linkValue = linkValue;
	}

	 

	public UserLinkStatus getStatus() {
		return status;
	}

	public void setStatus(UserLinkStatus status) {
		this.status = status;
	}


	
	
}
