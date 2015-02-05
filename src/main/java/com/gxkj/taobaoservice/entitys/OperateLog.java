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

import org.hibernate.annotations.GenericGenerator;

import com.gxkj.taobaoservice.enums.OperateTypes;

@Entity
@Table(name="operate_log")
public class OperateLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5564763427344541389L;
	
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false) 
	private Integer id;
	
	/**
	 * 被操作人ID
	 */
	@Column(name="user_id" )
	private Integer user_id;
	
	/**
	 * 操作时间
	 */
	@Column(name="operate_time" )
	@Temporal(TemporalType.TIMESTAMP )
	private Date operateTime;
	
	/**
	 * 操作类型
	 */
	@Column(name="operate_type" )
	@Enumerated(EnumType.STRING)
	private OperateTypes operateType;
	
	/**
	 * 修改前的值
	 */
	@Column(name="before_value" )
	private String beforeValue;
	
	/**
	 * 修改后的值
	 */
	@Column(name="after_value" )
	private String afterValue;
	
	/**
	 * IP
	 */
	@Column(name="ip" )
	private String ip;
	
	/**
	 * 被用过了 
	 */
	@Column(name="isused" )
	private int isUsed;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public OperateTypes getOperateType() {
		return operateType;
	}

	public void setOperateType(OperateTypes operateType) {
		this.operateType = operateType;
	}

	public String getBeforeValue() {
		return beforeValue;
	}

	public void setBeforeValue(String beforeValue) {
		this.beforeValue = beforeValue;
	}

	public String getAfterValue() {
		return afterValue;
	}

	public void setAfterValue(String afterValue) {
		this.afterValue = afterValue;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	
	

}
