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
	 * 商品标题
	 */
	@Column(name="product_title" )
	@NotEmpty(message="商品标题不能为空")
	@Length(min=5,max=100,  message="商品标题长度需要为5-100")
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
	@Min(value=5,message="担保金最低为5元")
	private BigDecimal guaranteePrice ;
	
	
	/**
	 *  每单完成基本任务，接手方受益金额
	 */
	@Column(name="basic_receiver_gain_money" )
	@NotNull(message="佣金不能为空")
	private BigDecimal basicReceiverGainMoney; 
	
	
	/**
	 *  每单奖励金额
	 */
	@Column(name="encourage" )
	private BigDecimal encourage ;

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
	@NotNull(message="订单状态不能为空")
	private TaskOrderStatus status = TaskOrderStatus.WAIT_FOR_SURE;
	
	 
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
	 * 重复次数
	 */
	@Column(name="repeate_times" )
	@NotNull
	@Min(value=1)
	private Integer repeateTimes = new Integer("1");
	
	
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

	

	public BigDecimal getEncourage() {
		return encourage;
	}

	public void setEncourage(BigDecimal encourage) {
		this.encourage = encourage;
	}

	public BigDecimal getBasicReceiverGainMoney() {
		return basicReceiverGainMoney;
	}

	public void setBasicReceiverGainMoney(BigDecimal basicReceiverGainMoney) {
		this.basicReceiverGainMoney = basicReceiverGainMoney;
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
	
	
	

}
