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
	 * 平台服务获取点数
	 */
	@Column(name="get_points")
	private BigDecimal getPoints;
	
	@Column(name="supply_points")
	private BigDecimal supplyPoints;

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

}
