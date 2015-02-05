package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer  id;
	
	/**
	 * 用户ID
	 */
	@Column(name = "user_id", unique = true, nullable = false)
	private int userId;
	
	/**
	 * 当前余额
	 */
	@Column(name = "current_balance")
	private BigDecimal currentBalance = BigDecimal.ZERO;
	
	/**
	 * 剩余点数
	 */
	@Column(name = "current_rest_points")
	private BigDecimal currentRestPoints = BigDecimal.ZERO;
	
	/**
	 * 锁定金额
	 */
	@Column(name = "locked_balance")
	private BigDecimal lockedBalance = BigDecimal.ZERO;
	
	/**
	 * 锁定点数
	 */
	@Column(name = "locked_points")
	private BigDecimal lockedPoints = BigDecimal.ZERO;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public BigDecimal getCurrentRestPoints() {
		return currentRestPoints;
	}

	public void setCurrentRestPoints(BigDecimal currentRestPoints) {
		this.currentRestPoints = currentRestPoints;
	}

	public BigDecimal getLockedBalance() {
		return lockedBalance;
	}

	public void setLockedBalance(BigDecimal lockedBalance) {
		this.lockedBalance = lockedBalance;
	}

	public BigDecimal getLockedPoints() {
		return lockedPoints;
	}

	public void setLockedPoints(BigDecimal lockedPoints) {
		this.lockedPoints = lockedPoints;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
	

}
