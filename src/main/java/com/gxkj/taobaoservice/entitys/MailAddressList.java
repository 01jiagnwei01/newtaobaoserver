package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;
import java.util.Date;

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
import org.hibernate.validator.constraints.Email;

import com.gxkj.taobaoservice.enums.MailAddressComeFrom;
import com.gxkj.taobaoservice.enums.MailAddressListStatus;
import com.gxkj.taobaoservice.enums.UserGender;

/**
 * 
 * 邮件通讯地址
 *
 */
@Entity
@Table(name = "mail_address_list")
public class MailAddressList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6607059950896411291L;

	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer  id;
	
	/**
	 * 用户名
	 */
	@Column(name = "name", nullable = false) 
	private String   name;
	
	
	/**
	 * 邮箱
	 */
	@Email(message="邮箱地址无效")
	@Column(name = "email", nullable = false) 
	private String   email;
	
	/**
	 * 状态
	 */
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private MailAddressListStatus status;
	
	/**
	 * 创建|修改人
	 */
	@Column(name = "create_user_id",  nullable = false) 
	private Integer  createUserId;
	
	
	/**
	 * 创建|修改人姓名
	 */
	@Transient
	private String  createUserName;
	
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "crete_time",  nullable = false) 
	private Date  creteTime;
	
	/**
	 * 邮箱来源
	 */
	@Column(name = "come_from", nullable = false)
	@Enumerated(EnumType.STRING)
	private MailAddressComeFrom comefrom;
	
	
	/**
	 * 性别
	 */
	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserGender gender;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MailAddressListStatus getStatus() {
		return status;
	}

	public void setStatus(MailAddressListStatus status) {
		this.status = status;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreteTime() {
		return creteTime;
	}

	public void setCreteTime(Date creteTime) {
		this.creteTime = creteTime;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public MailAddressComeFrom getComefrom() {
		return comefrom;
	}

	public void setComefrom(MailAddressComeFrom comefrom) {
		this.comefrom = comefrom;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}
	

}
