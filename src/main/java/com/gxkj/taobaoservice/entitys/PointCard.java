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

import org.hibernate.annotations.GenericGenerator;

import com.gxkj.taobaoservice.enums.PointCardStatus;

/**
 * 点卡
 * @author admin
 *
 */
@Entity
@Table(name = "point_card")
public class PointCard   implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3545715545966442853L;

	// 主键 ：@Id    主键生成方式：strategy = "increment"
	//映射表中id这个字段，不能为空，并且是唯一的
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer  id;
	 
	/**
	 * 点卡对应的点数
	 */
	@Column(name = "points", length = 10, nullable = false)
	private BigDecimal points;
	
	/**
	 * 购买该点卡需要支付的金额
	 */
	@Column(name = "money", length = 10, nullable = false) 
	private BigDecimal money;
	
	/**
	 * 标题
	 */
	@Column(name = "title", length = 50, nullable = false) 
	private String title;
	
	/**
	 * 图片路径
	 */
	@Column(name = "url", length = 100) 
	private String url;
	
	/**
	 * 点卡排序优先级
	 */
	@Column(name = "orders", length = 10) 
	private BigDecimal orders;
	
	@Column(name = "status", length = 10, nullable = false) 
	@Enumerated(EnumType.STRING)
	private PointCardStatus status;
	
	/**
	 * 管理员ID
	 */
	@Column(name = "admin_user_id", length = 10) 
	private Integer adminUserId;
	
	@Column(name="update_time" )
	@Temporal(TemporalType.TIMESTAMP )
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BigDecimal getOrders() {
		return orders;
	}

	public void setOrders(BigDecimal orders) {
		this.orders = orders;
	}

	public PointCardStatus getStatus() {
		return status;
	}

	public void setStatus(PointCardStatus status) {
		this.status = status;
	}

	public Integer getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
}
