package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
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

import com.gxkj.taobaoservice.enums.UserAccountTypes;

@Entity
@Table(name="user_account_log")
public class UserAccountLog  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6521304516499883312L;
	
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false) 
	private Integer id;
	
	/**
	 * 创建时间
	 */
	@Column(name="createTime" )
	@Temporal(TemporalType.TIMESTAMP )
	private Date createTime;

	/**
	 * 用户ID
	 */
	@Column(name="user_id")
	private Integer userId;
	
	/**
	 * 操作类型
	 */
	@Column(name="type" )
	@Enumerated(EnumType.STRING)
	private UserAccountTypes type; 
	
	/**
	 * 操作金额
	 */
	@Column(name="amount")
	private BigDecimal amount;
	
	/**
	 * 操作点数
	 */
	@Column(name="points")
	private BigDecimal  points;
	
	/**
	 * 操作前可用余额
	 */
	@Column(name="before_rest_amount")
	private BigDecimal beforeRestAmount;
	
	/**
	 * 操作前可用点数
	 */
	@Column(name="before_rest_points")
	private BigDecimal beforeRestPoints;
	
	/**
	 * 操作前锁定金额
	 */
	@Column(name="before_locked_amount")
	private BigDecimal beforeLockedAmount;
	
	/**
	 * 操作前锁定点数
	 */
	@Column(name="before_locked_points")
	private BigDecimal beforeLockedPoints;
	
	/**
	 * 操作后可用金额
	 */
	@Column(name="after_rest_amount")
	private BigDecimal afterRestAmount;
	
	/**
	 * 操作后可用点数
	 */
	@Column(name="after_rest_points")
	private BigDecimal afterRestPoints;
	
	/**
	 * 操作后锁定金额
	 */
	@Column(name="after_locked_amount")
	private BigDecimal afterLockedAmount;
	
	/**
	 * 操作后锁定点数
	 */
	@Column(name="after_locked_points")
	private BigDecimal afterLockedPoints;
	
	/**
	 * 介入的管理员ID
	 */
	@Column(name="admin_user_id")
	private Integer adminUserId;
	
	/**
	 * 任务ID
	 */
	@Column(name="task_id")
	private Integer taskId;
	
	/**
	 * 取款申请表ID
	 */
	@Column(name="draw_log_id")
	private Integer drawLogId;
	
	
	@Transient
	private String adminName;
	
	@Transient
	private String userName;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public UserAccountTypes getType() {
		return type;
	}

	public void setType(UserAccountTypes type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	public BigDecimal getBeforeRestAmount() {
		return beforeRestAmount;
	}

	public void setBeforeRestAmount(BigDecimal beforeRestAmount) {
		this.beforeRestAmount = beforeRestAmount;
	}

	public BigDecimal getBeforeRestPoints() {
		return beforeRestPoints;
	}

	public void setBeforeRestPoints(BigDecimal beforeRestPoints) {
		this.beforeRestPoints = beforeRestPoints;
	}

	public BigDecimal getBeforeLockedAmount() {
		return beforeLockedAmount;
	}

	public void setBeforeLockedAmount(BigDecimal beforeLockedAmount) {
		this.beforeLockedAmount = beforeLockedAmount;
	}

	public BigDecimal getBeforeLockedPoints() {
		return beforeLockedPoints;
	}

	public void setBeforeLockedPoints(BigDecimal beforeLockedPoints) {
		this.beforeLockedPoints = beforeLockedPoints;
	}

	public BigDecimal getAfterRestAmount() {
		return afterRestAmount;
	}

	public void setAfterRestAmount(BigDecimal afterRestAmount) {
		this.afterRestAmount = afterRestAmount;
	}

	public BigDecimal getAfterRestPoints() {
		return afterRestPoints;
	}

	public void setAfterRestPoints(BigDecimal afterRestPoints) {
		this.afterRestPoints = afterRestPoints;
	}

	public BigDecimal getAfterLockedAmount() {
		return afterLockedAmount;
	}

	public void setAfterLockedAmount(BigDecimal afterLockedAmount) {
		this.afterLockedAmount = afterLockedAmount;
	}

	public BigDecimal getAfterLockedPoints() {
		return afterLockedPoints;
	}

	public void setAfterLockedPoints(BigDecimal afterLockedPoints) {
		this.afterLockedPoints = afterLockedPoints;
	}

	public Integer getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getDrawLogId() {
		return drawLogId;
	}

	public void setDrawLogId(Integer drawLogId) {
		this.drawLogId = drawLogId;
	}
	
	

	 
}
