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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.gxkj.taobaoservice.enums.YanZhengMaTranType;
import com.gxkj.taobaoservice.enums.YanZhengMaTypes;
@Entity
@Table(name = "yanzhengma_bind_code")
public class YanZhengMaBindCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5407429116075398639L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer  id;
	
	@Column(name = "user_id",   nullable = false) 
	private Integer  userId;
	

	/**
	 * 注册方式类型
	 */
	@NotNull(message = "注册方式类型不能为空")
	@Column(name = "type", length = 10)
	@Enumerated(EnumType.STRING)
	private YanZhengMaTypes type;
	
	/**
	 * 注册的手机号或者是邮箱地址
	 */
	@NotNull(message = "联系方式不能为空")
	@Column(name = "value", length = 50)
	private String value;
	
	/**
	 * 验证码
	 */
	@NotNull(message = "验证码不能为空")
	@Column(name = "code", length = 10)
	private String code;
	
	
	
	
	/**
	 * 创建时间
	 */
	@NotNull(message = "创建时间不能为空")
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP )
	private Date createDime;
	
	/**
	 * 激活时间
	 */
	 
	@Column(name = "active_time")
	@Temporal(TemporalType.TIMESTAMP )
	private Date activeTime;
	
	/**
	 * 过期时间
	 */
	@NotNull(message = "过期时间不能为空")
	@Column(name = "exp_time")
	@Temporal(TemporalType.TIMESTAMP )
	private Date expTime;
	
	@Column(name = "tran_type")
	@Enumerated(EnumType.STRING)
	private YanZhengMaTranType tranType;
	
	@Column(name = "enabled", length = 1)
	private boolean enabled;

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

	public YanZhengMaTypes getType() {
		return type;
	}

	public void setType(YanZhengMaTypes type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateDime() {
		return createDime;
	}

	public void setCreateDime(Date createDime) {
		this.createDime = createDime;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	public Date getExpTime() {
		return expTime;
	}

	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}

	public YanZhengMaTranType getTranType() {
		return tranType;
	}

	public void setTranType(YanZhengMaTranType tranType) {
		this.tranType = tranType;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

}
