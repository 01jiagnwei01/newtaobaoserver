package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="company_account")
public class CompanyAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2407939676528569988L;
	
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false) 
	private Integer id;
	
	/**
	 * 已售出点数
	 */
	@Column(name="sell_points")
	private BigDecimal sellPoint;
	
	/**
	 * 售点输入金额
	 */
	@Column(name="sell_points_money")
	private BigDecimal sellPointsMoney;
	
	/**
	 * 平台服务获取点数
	 */
	@Column(name="get_points")
	private BigDecimal getPoints;
	
	/**
	 * 赠送点数
	 */
	@Column(name="supply_points")
	private BigDecimal supplyPoints;
	
	/**
	 * 充值金额
	 */
	@Column(name="deposit_money")
	private BigDecimal depositMoney;
	
	/**
	 * 
	 * 取款金额
	 */
	@Column(name="draw_money")
	private BigDecimal drawMoney;
	
	@Column(name="createtime")
	private Date createTime;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(BigDecimal sellPoint) {
		this.sellPoint = sellPoint;
	}

	public BigDecimal getGetPoints() {
		return getPoints;
	}

	public void setGetPoints(BigDecimal getPoints) {
		this.getPoints = getPoints;
	}

	public BigDecimal getSupplyPoints() {
		return supplyPoints;
	}

	public void setSupplyPoints(BigDecimal supplyPoints) {
		this.supplyPoints = supplyPoints;
	}

	public BigDecimal getSellPointsMoney() {
		return sellPointsMoney;
	}

	public void setSellPointsMoney(BigDecimal sellPointsMoney) {
		this.sellPointsMoney = sellPointsMoney;
	}

	public BigDecimal getDepositMoney() {
		return depositMoney;
	}

	public void setDepositMoney(BigDecimal depositMoney) {
		this.depositMoney = depositMoney;
	}

	public BigDecimal getDrawMoney() {
		return drawMoney;
	}

	public void setDrawMoney(BigDecimal drawMoney) {
		this.drawMoney = drawMoney;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
