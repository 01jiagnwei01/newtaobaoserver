package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.gxkj.taobaoservice.enums.UserBaseStatus;

@Entity
@Table(name = "user_base")
public class UserBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5273424711942109501L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer id;
	
	/**
	 * 用户名
	 */
	@Column(name="user_name" )
	private String userName;
	
	/**
	 * 密码
	 */
	@Column(name="password" )
	private String password;
	
	 

	/**
	 * status
	 */
	@Column(name="status" )
	@Enumerated(EnumType.STRING)
	private UserBaseStatus status;
	
	/**
	 * 注册时间
	 */
	@Column(name="regTime")
	@Temporal(TemporalType.TIMESTAMP )
	private Date regTime;
	
	 
	
	
	
	/**
	 * 账户信息
	 */
	@Transient
	private UserAccount uerAccount;
	
	/**
	 * 联系方式
	 */
	@Transient
	private List<UserLink> userLinks = new ArrayList<UserLink>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	 
	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public UserAccount getUerAccount() {
		return uerAccount;
	}

	public void setUerAccount(UserAccount uerAccount) {
		this.uerAccount = uerAccount;
	}

	public List<UserLink> getUserLinks() {
		return userLinks;
	}

	public void setUserLinks(List<UserLink> userLinks) {
		this.userLinks = userLinks;
	}

	public UserBaseStatus getStatus() {
		return status;
	}

	public void setStatus(UserBaseStatus status) {
		this.status = status;
	}

	 
	
}
