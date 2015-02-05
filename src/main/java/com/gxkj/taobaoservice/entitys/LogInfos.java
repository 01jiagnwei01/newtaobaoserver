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

import com.gxkj.taobaoservice.enums.LogType;

/**
 * 日志 
 *
 */
@Entity
@Table(name = "log_info")
public class LogInfos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3091339344884994835L;

	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer  id;
	
	/**
	 * 用户ID
	 */
	@Column(name = "user_id", nullable = false) 
	private Integer  userId;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", nullable = false) 
	private Date  createTime;
	
	/**
	 * 日志类型
	 */
	@Column(name = "log_type", nullable = false) 
	@Enumerated(EnumType.STRING)
	private LogType  logType;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public LogType getLogType() {
		return logType;
	}

	public void setLogType(LogType logType) {
		this.logType = logType;
	}
	
	
	
}
