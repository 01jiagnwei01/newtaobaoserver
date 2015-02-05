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

import org.hibernate.annotations.GenericGenerator;

import com.gxkj.taobaoservice.enums.ValidType;
@Entity
@Table(name = "valid_info")
public class ValidInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2096609795382339414L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer  id;
	
	/**
	 * 验证类型
	 */
	@Column(name = "type", nullable = false) 
	@Enumerated(EnumType.STRING)
	private ValidType type;
	
	/**
	 * 邮箱、
	 */
	@Column(name = "type_value",nullable = false) 
	private String  typeValue;
	
	/**
	 * 验证码
	 */
	@Column(name = "code",nullable = false) 
	private String  code;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time",nullable = false) 
	private Date  createTime;
	
	/**
	 * 有效截至时间
	 */
	@Column(name = "invalid_time",nullable = false) 
	private Date  invalidTime;
	
	/**
	 * 激活时间
	 */
	@Column(name = "active_time",nullable = false) 
	private Date  activeTime;
	
	/**
	 * 是否可用
	 */
	@Column(name = "enabled",nullable = false) 
	private boolean  enabled;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ValidType getType() {
		return type;
	}

	public void setType(ValidType type) {
		this.type = type;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}
	  

}
