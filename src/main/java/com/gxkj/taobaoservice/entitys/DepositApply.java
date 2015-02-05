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

import com.gxkj.taobaoservice.enums.RechargeApplyStatus;
/**
 * 充值申请
 *
 */
@Entity
@Table(name = "deposit_apply")
public class DepositApply implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3903904157148096889L;

	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer  id;
	
	/**
	 * 第三方订单号
	 */
	@Column(name = "third_order_no", unique = true) 
	private String  thirdOrderNo;
	
	/**
	 * 充值金额
	 */
	@Column(name = "amount" ,   nullable = true) 
	private BigDecimal  amount;
	
	/**
	 * 申请人ID
	 */
	@Column(name = "user_id" ,   nullable = true) 
	private Integer  userId;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time",   nullable = true )
	@Temporal(TemporalType.TIMESTAMP )
	private Date createTime;
	
	/**
	 * 状态
	 */
	@Column(name = "status" ,   nullable = true)
	@Enumerated(EnumType.STRING)
	private RechargeApplyStatus status;
	
	/**
	 * 审核人ID
	 */
	@Column(name = "auditor_id" )
	private Integer auditorId;
	
	/**
	 * 审核人名字
	 */
	@Column(name = "auditor_name" )
	private String auditorName;
	
	
	/**
	 * 审核时间
	 */
	@Column(name = "review_time" )
	@Temporal(TemporalType.TIMESTAMP )
	private Date reviewTime;
	
	/**
	 * 拒绝理由
	 */
	@Column(name = "refuse_reason" )
	private  String refuseReason;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getThirdOrderNo() {
		return thirdOrderNo;
	}

	public void setThirdOrderNo(String thirdOrderNo) {
		this.thirdOrderNo = thirdOrderNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public RechargeApplyStatus getStatus() {
		return status;
	}

	public void setStatus(RechargeApplyStatus status) {
		this.status = status;
	}

	public Integer getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	 
}
