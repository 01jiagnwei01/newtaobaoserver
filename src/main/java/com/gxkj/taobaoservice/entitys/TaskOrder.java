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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.gxkj.taobaoservice.enums.GoodCommentTimeLimits;
import com.gxkj.taobaoservice.enums.PayMethods;
import com.gxkj.taobaoservice.enums.TaskOrderStatus;
import com.gxkj.taobaoservice.enums.TaskTypes;

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
	@DecimalMin(value="100",message="userId最小值是1")
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
	 * 商品地址
	 */
	@Column(name="product_link" )
	@NotEmpty(message="商品地址不能为空")
	@Length(min=10,message="请填入正确的商品地址")
	private String productLink;
	
	/**
	 * 商品标题
	 */
	@Column(name="product_title" )
	@NotEmpty(message="商品标题不能为空")
	@Length(min=5,message="请填入正确的商品标题")
	private String productTitle ;
	
	/**
	 * 付款方式
	 */
	@Column(name="pay_method" )
	@Enumerated(EnumType.STRING)
	@NotNull
	private PayMethods payMethod ;
	
	/**
	 * 担保金
	 */
	@Column(name="guarantee_price" )
	@NotNull(message="担保金不能为空")
	@Min(value=5,message="担保金最低为5元")
	private BigDecimal guaranteePrice ;
	
	
	/**
	 * 任务类型
	 */
	@Column(name="task_type" )
	@Enumerated(EnumType.STRING)
	@NotNull(message="任务类型不能为空")
	private  TaskTypes taskType;
	
	/**
	 * 产品好评内容
	 */
	@Column(name="good_comment" )
	private  String goodComment;
	
	/**
	 * 好评时限
	 */
	@Column(name="good_comment_time_limit" )
	@Enumerated(EnumType.STRING)
	@NotNull(message="好评时限不能为空")
	private  GoodCommentTimeLimits goodCommentTimeLimit;
	
	/**
	 * 付给平台金额
	 */
	@Column(name="payed_dot" )
	@NotNull(message="付给平台金额不能为空")
	private  BigDecimal payedDot;
	
	/**
	 * 接手人获利金额
	 */
	@Column(name="remuneration" )
	@NotNull(message="接手人获利金额不能为空")
	private BigDecimal remuneration; 

	
	/**
	 * 订单状态
	 */
	@Column(name="status" )
	@Enumerated(EnumType.STRING)
	@NotNull(message="订单状态不能为空")
	private TaskOrderStatus status = TaskOrderStatus.WAIT_FOR_SURE;
	
	
	/**
	 * 好评需要发布点数
	 */
	@Column(name="good_comment_time_dot" )
	@NotNull
	private  BigDecimal goodCommentTimeDot;
	
	/**
	 * 完成增值任务，接收方受益点数
	 */
	@Column(name="zengzhi_receiver_gain_points" )
	@NotNull
	@Min(value=0)
	private  BigDecimal zengzhiReceiverGain_points = BigDecimal.ZERO;
	/**
	 * 完成增值任务，平台受益点数
	 */
	@Column(name="zengzhi_pingtai_gain_points" )
	@NotNull
	@Min(value=0)
	private  BigDecimal zengzhiPingtaiGain_points = BigDecimal.ZERO;
	
	/**
	 * 完成基本任务，接收方受益点数
	 */
	@Column(name="basic_receiver_gain_point" )
	private  BigDecimal basicReceiverGainPoint = BigDecimal.ZERO;
	/**
	 * 完成基本任务，平台受益点数
	 */
	@Column(name="basic_pingtai_gain_point" )
	@NotNull
	@Min(value=0)
	private  BigDecimal basicPingtaiGainPoint = BigDecimal.ZERO;
	
	/**
	 * 重复次数
	 */
	@Column(name="repeate_times" )
	@NotNull
	@Min(value=1)
	private Integer repeateTimes = new Integer("1");
	
	
	/**
	 * 增值任务
	 */
	@Transient
	private List<TaskAppreciation> taskAppreciations;
	
	/**
	 * 基本发布点
	 */
	@Column(name="basic_publish_dot" )
	@NotNull(message="基本发布点不能为空")
	private BigDecimal basicPublishDot;
	
	

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

	public PayMethods getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(PayMethods payMethod) {
		this.payMethod = payMethod;
	}

	public BigDecimal getGuaranteePrice() {
		return guaranteePrice;
	}

	public void setGuaranteePrice(BigDecimal guaranteePrice) {
		this.guaranteePrice = guaranteePrice;
	}

	public BigDecimal getBasicPublishDot() {
		return basicPublishDot;
	}

	public void setBasicPublishDot(BigDecimal basicPublishDot) {
		this.basicPublishDot = basicPublishDot;
	}

	public TaskTypes getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskTypes taskType) {
		this.taskType = taskType;
	}

	public String getGoodComment() {
		return goodComment;
	}

	public void setGoodComment(String goodComment) {
		this.goodComment = goodComment;
	}

	public GoodCommentTimeLimits getGoodCommentTimeLimit() {
		return goodCommentTimeLimit;
	}

	public void setGoodCommentTimeLimit(GoodCommentTimeLimits goodCommentTimeLimit) {
		this.goodCommentTimeLimit = goodCommentTimeLimit;
	}

	public BigDecimal getGoodCommentTimeDot() {
		return goodCommentTimeDot;
	}

	public void setGoodCommentTimeDot(BigDecimal goodCommentTimeDot) {
		this.goodCommentTimeDot = goodCommentTimeDot;
	}

	public List<TaskAppreciation> getTaskAppreciations() {
		return taskAppreciations;
	}

	public void setTaskAppreciations(List<TaskAppreciation> taskAppreciations) {
		this.taskAppreciations = taskAppreciations;
	}

	public TaskOrderStatus getStatus() {
		return status;
	}

	public void setStatus(TaskOrderStatus status) {
		this.status = status;
	}

	public BigDecimal getZengzhiReceiverGain_points() {
		return zengzhiReceiverGain_points;
	}

	public void setZengzhiReceiverGain_points(BigDecimal zengzhiReceiverGain_points) {
		this.zengzhiReceiverGain_points = zengzhiReceiverGain_points;
	}

	public BigDecimal getZengzhiPingtaiGain_points() {
		return zengzhiPingtaiGain_points;
	}

	public void setZengzhiPingtaiGain_points(BigDecimal zengzhiPingtaiGain_points) {
		this.zengzhiPingtaiGain_points = zengzhiPingtaiGain_points;
	}

	public BigDecimal getBasicReceiverGainPoint() {
		return basicReceiverGainPoint;
	}

	public void setBasicReceiverGainPoint(BigDecimal basicReceiverGainPoint) {
		this.basicReceiverGainPoint = basicReceiverGainPoint;
	}

	public BigDecimal getBasicPingtaiGainPoint() {
		return basicPingtaiGainPoint;
	}

	public void setBasicPingtaiGainPoint(BigDecimal basicPingtaiGainPoint) {
		this.basicPingtaiGainPoint = basicPingtaiGainPoint;
	}

	public Integer getRepeateTimes() {
		return repeateTimes;
	}

	public void setRepeateTimes(Integer repeateTimes) {
		this.repeateTimes = repeateTimes;
	}

	public BigDecimal getPayedDot() {
		return payedDot;
	}

	public void setPayedDot(BigDecimal payedDot) {
		this.payedDot = payedDot;
	}
	
	
	

}
