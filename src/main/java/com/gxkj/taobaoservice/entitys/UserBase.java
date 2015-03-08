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
	 * 状态
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
	 * 绑定邮箱
	 */
	@Column(name="bind_email" )
	private String bindEmail;
	
	/**
	 * 绑定手机号
	 */
	@Column(name="bind_telphone" )
	private String bindTelphone;
	
	@Column(name="cao_zuo_ma" )
	private String caoZuoMa;
	
	/**
	 * QQ号 
	 */
	@Column(name="bind_qq" )
	private String bindQq;
	
	/**
	 * 绑定阿里账号
	 */
	@Column(name="bind_alipay" )
	private String bindAlipay;
	

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

	 

	public UserBaseStatus getStatus() {
		return status;
	}

	public void setStatus(UserBaseStatus status) {
		this.status = status;
	}

	public String getCaoZuoMa() {
		return caoZuoMa;
	}

	public void setCaoZuoMa(String caoZuoMa) {
		this.caoZuoMa = caoZuoMa;
	}

	public String getBindEmail() {
		return bindEmail;
	}

	public void setBindEmail(String bindEmail) {
		this.bindEmail = bindEmail;
	}

	public String getBindTelphone() {
		return bindTelphone;
	}

	public void setBindTelphone(String bindTelphone) {
		this.bindTelphone = bindTelphone;
	}

	public String getBindQq() {
		return bindQq;
	}

	public void setBindQq(String bindQq) {
		this.bindQq = bindQq;
	}

	public String getBindAlipay() {
		return bindAlipay;
	}

	public void setBindAlipay(String bindAlipay) {
		this.bindAlipay = bindAlipay;
	}
	
	

	 
	
}
