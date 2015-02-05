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

import com.gxkj.taobaoservice.enums.MailSenderRefAddressListStatus;
import com.gxkj.taobaoservice.enums.UserGender;
@Entity
@Table(name = "mail_sender_ref_address_list")
public class MailSenderRefAddressList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7609111954124271896L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer  id;
	
	/**
	 * 关联 mail_sender表的ID 
	 */
	@Column(name = "mail_sender_task_id" ,   nullable = false) 
	private Integer  mailSenderTaskId;
	
	/**
	 * 邮箱
	 */
	@Column(name = "email",length=100) 
	private String  email;
	
	/**
	 * 通讯录ID 
	 */
	@Column(name = "mail_address_list_id",length=11) 
	private Integer  mailAddressListId;
	
	/**
	 * 邮箱用户名
	 */
	@Column(name = "emai_user_name",length=50) 
	private String  emailUserName;
	
	/**
	 * 性别
	 */
	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserGender gender;
	
	/**
	 * 状态
	 */
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private MailSenderRefAddressListStatus status;
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
 

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getMailAddressListId() {
		return mailAddressListId;
	}

	public void setMailAddressListId(Integer mailAddressListId) {
		this.mailAddressListId = mailAddressListId;
	}

	public String getEmailUserName() {
		return emailUserName;
	}

	public void setEmailUserName(String emailUserName) {
		this.emailUserName = emailUserName;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public MailSenderRefAddressListStatus getStatus() {
		return status;
	}

	public void setStatus(MailSenderRefAddressListStatus status) {
		this.status = status;
	}

	public Integer getMailSenderTaskId() {
		return mailSenderTaskId;
	}

	public void setMailSenderTaskId(Integer mailSenderTaskId) {
		this.mailSenderTaskId = mailSenderTaskId;
	}
	
	 
}
