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
import org.hibernate.validator.constraints.Length;
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
	@Length(min=10,max=100,message="商品网址长度需要为5-100")
	private String productLink;
	
	/**
	 *  每单担保金
	 */
	@Column(name="guarantee_price" )
	@NotNull(message="担保金不能为空") 
	private BigDecimal guaranteePrice ;
	/**
	 *  每单完成基本任务，接手方受益金额
	 */
	@Column(name="basic_receiver_gain_money" )
	@NotNull(message="佣金不能为空")
	private BigDecimal basicReceiverGainMoney = BigDecimal.ZERO; 
	
	/**
	 *  每单奖励金额
	 */
	@Column(name="encourage" )
	private BigDecimal encourage = BigDecimal.ZERO ;
	
	/**
	 *  每单完成基本任务，平台受益点数
	 */
	@Column(name="basic_pingtai_gain_point" )
	@NotNull
	@Min(value=0)
	private  BigDecimal basicPingtaiGainPoint = BigDecimal.ZERO;
	
	/**
	 * 订单状态
	 */
	@Column(name="status" )
	@Enumerated(EnumType.STRING)
	@NotNull(message="任务状态不能为空")
	private TaskStatus status = TaskStatus.Wait_For_Receive;
	
	/**
	 *  每单完成基本任务，接手方受益点数
	 */
	@Column(name="basic_receiver_gain_point" )
	private  BigDecimal basicReceiverGainPoint = BigDecimal.ZERO;
	
	/**
	 *  每单完成增值任务，接手方受益点数
	 */
	@Column(name="zengzhi_receiver_gain_points" )
	@NotNull
	@Min(value=0)
	private  BigDecimal zengzhiReceiverGainPoints = BigDecimal.ZERO;
	
	/**
	 *  每单完成增值任务，接手方受益金额
	 */
	@Column(name="zengzhi_receiver_gain_money" )
	@NotNull
	@Min(value=0)
	private  BigDecimal zengzhiReceiverGainMoney = BigDecimal.ZERO;
	
	/**
	 * 每单完成增值任务，平台受益点数
	 */
	@Column(name="zengzhi_pingtai_gain_points" )
	@NotNull
	@Min(value=0)
	private  BigDecimal zengzhiPingtaiGainPoints = BigDecimal.ZERO;
	
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

	public BigDecimal getBasicReceiverGainMoney() {
		return basicReceiverGainMoney;
	}

	public void setBasicReceiverGainMoney(BigDecimal basicReceiverGainMoney) {
		this.basicReceiverGainMoney = basicReceiverGainMoney;
	}

	public BigDecimal getEncourage() {
		return encourage;
	}

	public void setEncourage(BigDecimal encourage) {
		this.encourage = encourage;
	}

	public BigDecimal getBasicPingtaiGainPoint() {
		return basicPingtaiGainPoint;
	}

	public void setBasicPingtaiGainPoint(BigDecimal basicPingtaiGainPoint) {
		this.basicPingtaiGainPoint = basicPingtaiGainPoint;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public BigDecimal getBasicReceiverGainPoint() {
		return basicReceiverGainPoint;
	}

	public void setBasicReceiverGainPoint(BigDecimal basicReceiverGainPoint) {
		this.basicReceiverGainPoint = basicReceiverGainPoint;
	}

	public BigDecimal getZengzhiReceiverGainPoints() {
		return zengzhiReceiverGainPoints;
	}

	public void setZengzhiReceiverGainPoints(BigDecimal zengzhiReceiverGainPoints) {
		this.zengzhiReceiverGainPoints = zengzhiReceiverGainPoints;
	}

	public BigDecimal getZengzhiReceiverGainMoney() {
		return zengzhiReceiverGainMoney;
	}

	public void setZengzhiReceiverGainMoney(BigDecimal zengzhiReceiverGainMoney) {
		this.zengzhiReceiverGainMoney = zengzhiReceiverGainMoney;
	}

	public BigDecimal getZengzhiPingtaiGainPoints() {
		return zengzhiPingtaiGainPoints;
	}

	public void setZengzhiPingtaiGainPoints(BigDecimal zengzhiPingtaiGainPoints) {
		this.zengzhiPingtaiGainPoints = zengzhiPingtaiGainPoints;
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
	
	

}
