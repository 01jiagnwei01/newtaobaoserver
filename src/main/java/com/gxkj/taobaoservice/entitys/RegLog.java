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

import com.gxkj.taobaoservice.enums.YanZhengMaTypes;
@Entity
@Table(name = "reg_log")
public class RegLog implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7217428708667598876L;

	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer  id;
	
	/**
	 * 验证码
	 */
	@NotNull(message = "验证码不能为空")
	@Column(name = "code", length = 10)
	private String code;
	
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

	
	@Column(name = "enabled", length = 1)
	private boolean enabled;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
