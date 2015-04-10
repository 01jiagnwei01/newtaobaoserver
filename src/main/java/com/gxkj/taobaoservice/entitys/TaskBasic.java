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

import com.gxkj.taobaoservice.enums.TaskStatus;
@Entity
@Table(name = "task_basic")
public class TaskBasic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6074676928376368937L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer  id;
	
	/**
	 * 订单创建用户ID
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
	 * 订单ID
	 */
	@NotNull(message="关联订单不允许为空")
	@Column(name="task_order_id" )
	private  Integer taskOrderId;
	
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
	 *  佣金
	 */
	@Column(name="commission" )
	private BigDecimal commission = BigDecimal.ZERO ;
	
	/**
	 * 该任务确认付给平台点数
	 */
	@Column(name="pay_pingtai_points" )
	@NotNull
	@Min(value=0)
	private  BigDecimal payPingTaiPoints = BigDecimal.ZERO;
	
	/**
	 * 该任务确认付给平台金额
	 */
	@Column(name="pay_pingtai_money" )
	@NotNull
	@Min(value=0)
	private  BigDecimal payPingTaiMoney = BigDecimal.ZERO;
	
	
	/**
	 * 该任务确认付给接手点数
	 */
	@Column(name="pay_receiver_points" )
	@NotNull
	@Min(value=0)
	private  BigDecimal payReceiverPoints = BigDecimal.ZERO;
	
	/**
	 * 该任务确认付给接手金额
	 */
	@Column(name="pay_receiver_money" )
	@NotNull
	@Min(value=0)
	private  BigDecimal payReceiverMoney = BigDecimal.ZERO;
	
	
	/**
	 * 订单状态
	 */
	@Column(name="status" )
	@Enumerated(EnumType.STRING)
	@NotNull(message="任务状态不能为空")
	private TaskStatus status = TaskStatus.Wait_For_Receive;
	 
	
	/**
	 * 接单人ID
	 */
	@Column(name="receiver_id" ) 
	private Integer receiverId;
	
	/**
	 * 接单时间
	 */
	@Column(name="receiver_time" ) 
	private Date receiverTime;
	
	/**
	 * 任务完成时间
	 */
	@Column(name="task_complete_time" ) 
	private Date taskCompleteTime;
	
	/**
	 * 任务结束时间
	 */
	@Column(name="task_end_time" ) 
	private Date taskEndTime;
	/**
	 * 接单人QQ号
	 */
	@Column(name="receiver_qq" ) 
	private String receiverQq;
	
	/**
	 * 接单人支付宝账号
	 */
	@Column(name="receiver_alipay" ) 
	private String receiverAlipay;
	 
	
	/**
	 * 实际选中的任务
	 */
	@Transient
	private List<TaskOrderSubTaskInfo> taskOrderSubTaskInfos;
	
	@Transient
	private List<TaskBasicLog> taskBasicLogs;

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

	public Integer getTaskOrderId() {
		return taskOrderId;
	}

	public void setTaskOrderId(Integer taskOrderId) {
		this.taskOrderId = taskOrderId;
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

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductLink() {
		return productLink;
	}

	public void setProductLink(String productLink) {
		this.productLink = productLink;
	}

	public BigDecimal getGuaranteePrice() {
		return guaranteePrice;
	}

	public void setGuaranteePrice(BigDecimal guaranteePrice) {
		this.guaranteePrice = guaranteePrice;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public Date getReceiverTime() {
		return receiverTime;
	}

	public void setReceiverTime(Date receiverTime) {
		this.receiverTime = receiverTime;
	}

	public List<TaskOrderSubTaskInfo> getTaskOrderSubTaskInfos() {
		return taskOrderSubTaskInfos;
	}

	public void setTaskOrderSubTaskInfos(
			List<TaskOrderSubTaskInfo> taskOrderSubTaskInfos) {
		this.taskOrderSubTaskInfos = taskOrderSubTaskInfos;
	}

	public String getReceiverQq() {
		return receiverQq;
	}

	public void setReceiverQq(String receiverQq) {
		this.receiverQq = receiverQq;
	}

	public String getReceiverAlipay() {
		return receiverAlipay;
	}

	public void setReceiverAlipay(String receiverAlipay) {
		this.receiverAlipay = receiverAlipay;
	}

	public List<TaskBasicLog> getTaskBasicLogs() {
		return taskBasicLogs;
	}

	public void setTaskBasicLogs(List<TaskBasicLog> taskBasicLogs) {
		this.taskBasicLogs = taskBasicLogs;
	}

	public Date getTaskCompleteTime() {
		return taskCompleteTime;
	}

	public void setTaskCompleteTime(Date taskCompleteTime) {
		this.taskCompleteTime = taskCompleteTime;
	}

	public Date getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(Date taskEndTime) {
		this.taskEndTime = taskEndTime;
	}
 

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
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

	public BigDecimal getPayReceiverPoints() {
		return payReceiverPoints;
	}

	public void setPayReceiverPoints(BigDecimal payReceiverPoints) {
		this.payReceiverPoints = payReceiverPoints;
	}

	public BigDecimal getPayReceiverMoney() {
		return payReceiverMoney;
	}

	public void setPayReceiverMoney(BigDecimal payReceiverMoney) {
		this.payReceiverMoney = payReceiverMoney;
	}
	
	

}
