package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import com.gxkj.taobaoservice.enums.TaskOrderStatus;

/**
 * 创建任务订单 
 */
@Entity
@Table(name="task_order")
public class TaskOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3560729988994035043L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer id;
	
	/**
	 * 用户ID
	 */
	@Column(name="user_id" ) 
	private Integer userId;
	
	/**
	 * 创建时间
	 */
	@NotNull(message="创建时间不能为空")
	@Column(name="create_time" )
	private  Date createTime;
	
	/**
	 * 任务发布人淘宝号
	 */
	@Column(name="taobao_xiaohao" )
	@NotEmpty(message="淘宝号不能为空")
	private String taobaoXiaohao;
	
	/**
	 * 任务发布人QQ
	 */
	@Column(name="user_qq" )
	@NotEmpty(message="QQ不能为空")
	private String userQq;
	
	/**
	 * 商品标题
	 */
	@Column(name="product_title" )
	@NotEmpty(message="商品标题不能为空")
	private String productTitle ;
	
	/**
	 * 商品地址
	 */
	@Column(name="product_link" )
	@NotEmpty(message="商品网址不能为空")
	private String productLink;
	
	/**
	 *  每单担保金
	 */
	@Column(name="guarantee_price" )
	@NotNull(message="担保金不能为空")
	private BigDecimal guaranteePrice ;
	
	
	/**
	 *   接手方佣金金额
	 */
	@Column(name="commission" )
	@NotNull(message="佣金不能为空")
	private BigDecimal commission = BigDecimal.ZERO; 
	
	/**
	 * 订单状态
	 */
	@Column(name="status" )
	@Enumerated(EnumType.STRING)
	@NotNull(message="订单状态不能为空")
	private TaskOrderStatus status = TaskOrderStatus.WAIT_FOR_SURE;
	 
	 
	/**
	 * 重复次数
	 */
	@Column(name="repeate_times" )
	@NotNull
	@Min(value=1)
	private Integer repeateTimes = new Integer("1");
	
	/**
	 * 订单确认付给平台点数
	 */
	@Column(name="pay_pingtai_points" )
	@NotNull
	@Min(value=0)
	private  BigDecimal payPingTaiPoints = BigDecimal.ZERO;
	
	/**
	 * 订单确认付给平台金额
	 */
	@Column(name="pay_pingtai_money" )
	@NotNull
	@Min(value=0)
	private  BigDecimal payPingTaiMoney = BigDecimal.ZERO;
	
	/**
	 * 每个任务付给平台点数
	 */
	@Column(name="every_task_pay_pingtai_points" )
	@NotNull
	@Min(value=0)
	private  BigDecimal everyTaskPayPingtaiPoints = BigDecimal.ZERO;
	
	/**
	 * 每个任务付给平台金额
	 */
	@Column(name="every_task_pay_pingtai_money" )
	@NotNull
	@Min(value=0)
	private  BigDecimal everyTaskPayPingtaiMoney = BigDecimal.ZERO;
	
	/**
	 * 每个任务付给接手点数
	 */
	@Column(name="every_task_pay_receiver_points" )
	@NotNull
	@Min(value=0)
	private  BigDecimal everyTaskPayReceiverPoints = BigDecimal.ZERO;
	
	/**
	 * 每个任务付给接手金额
	 */
	@Column(name="every_task_pay_receiver_money" )
	@NotNull
	@Min(value=0)
	private  BigDecimal everyTaskPayReceiverMoney = BigDecimal.ZERO;
	
	
	/**
	 * 该订单平台基本受益点数
	 */
	@Column(name="basicPingtaiGainPoint" )
	@NotNull
	@Min(value=0)
	private  BigDecimal basicPingtaiGainPoint = BigDecimal.ZERO;
	
	/**
	 *任务
	 */
	@Transient
	private List<SubTaskInfo> tasks;
	
	/**
	 * 实际选中的任务
	 */
	@Transient
	private List<TaskOrderSubTaskInfo> taskOrderSubTaskInfos;
	
	/**
	 * 合计支付金额
	 */
	@Transient
	private BigDecimal countPayMoney;

	/**
	 * 合计支付点数
	 */
	@Transient
	private BigDecimal countPayPoints;
	

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

	public String getTaobaoXiaohao() {
		return taobaoXiaohao;
	}

	public void setTaobaoXiaohao(String taobaoXiaohao) {
		this.taobaoXiaohao = taobaoXiaohao;
	}

	public String getUserQq() {
		return userQq;
	}

	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}

	public String getProductLink() {
		return productLink;
	}

	public void setProductLink(String productLink) {
		this.productLink = productLink;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public BigDecimal getGuaranteePrice() {
		return guaranteePrice;
	}

	public void setGuaranteePrice(BigDecimal guaranteePrice) {
		this.guaranteePrice = guaranteePrice;
	}
 

	public TaskOrderStatus getStatus() {
		return status;
	}

	public void setStatus(TaskOrderStatus status) {
		this.status = status;
	}

	public Integer getRepeateTimes() {
		return repeateTimes;
	}

	public void setRepeateTimes(Integer repeateTimes) {
		this.repeateTimes = repeateTimes;
	}

	 
	public List<SubTaskInfo> getTasks() {
		return tasks;
	}

	public void setTasks(List<SubTaskInfo> tasks) {
		this.tasks = tasks;
	}

	public BigDecimal getCountPayMoney() {
		return countPayMoney;
	}

	public void setCountPayMoney(BigDecimal countPayMoney) {
		this.countPayMoney = countPayMoney;
	}

	public BigDecimal getCountPayPoints() {
		return countPayPoints;
	}

	public void setCountPayPoints(BigDecimal countPayPoints) {
		this.countPayPoints = countPayPoints;
	}

	public List<TaskOrderSubTaskInfo> getTaskOrderSubTaskInfos() {
		return taskOrderSubTaskInfos;
	}

	public void setTaskOrderSubTaskInfos(
			List<TaskOrderSubTaskInfo> taskOrderSubTaskInfos) {
		this.taskOrderSubTaskInfos = taskOrderSubTaskInfos;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public BigDecimal getPayPingTaiPoints() {
		return payPingTaiPoints;
	}

	public void setPayPingTaiPoints(BigDecimal payPingTaiPoints) {
		this.payPingTaiPoints = payPingTaiPoints;
	}

	public BigDecimal getPayPingTaiMoney() {
		return payPingTaiMoney;
	}

	public void setPayPingTaiMoney(BigDecimal payPingTaiMoney) {
		this.payPingTaiMoney = payPingTaiMoney;
	}

	public BigDecimal getEveryTaskPayPingtaiPoints() {
		return everyTaskPayPingtaiPoints;
	}

	public void setEveryTaskPayPingtaiPoints(BigDecimal everyTaskPayPingtaiPoints) {
		this.everyTaskPayPingtaiPoints = everyTaskPayPingtaiPoints;
	}

	public BigDecimal getEveryTaskPayPingtaiMoney() {
		return everyTaskPayPingtaiMoney;
	}

	public void setEveryTaskPayPingtaiMoney(BigDecimal everyTaskPayPingtaiMoney) {
		this.everyTaskPayPingtaiMoney = everyTaskPayPingtaiMoney;
	}

	public BigDecimal getEveryTaskPayReceiverPoints() {
		return everyTaskPayReceiverPoints;
	}

	public void setEveryTaskPayReceiverPoints(BigDecimal everyTaskPayReceiverPoints) {
		this.everyTaskPayReceiverPoints = everyTaskPayReceiverPoints;
	}

	public BigDecimal getEveryTaskPayReceiverMoney() {
		return everyTaskPayReceiverMoney;
	}

	public void setEveryTaskPayReceiverMoney(BigDecimal everyTaskPayReceiverMoney) {
		this.everyTaskPayReceiverMoney = everyTaskPayReceiverMoney;
	}

	public BigDecimal getBasicPingtaiGainPoint() {
		return basicPingtaiGainPoint;
	}

	public void setBasicPingtaiGainPoint(BigDecimal basicPingtaiGainPoint) {
		this.basicPingtaiGainPoint = basicPingtaiGainPoint;
	}
	
	
}
