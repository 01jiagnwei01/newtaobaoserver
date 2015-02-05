package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.gxkj.taobaoservice.enums.BenefitTypes;
import com.gxkj.taobaoservice.enums.TaskAppreciations;
@Entity
@Table(name="task_appreciation")
public class TaskAppreciation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8887907931222133137L;


	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer id;
	
	
	/**
	 * 订单ID
	 */
	@Column(name = "task_order_id",  nullable = false) 
	private Integer taskOrderId;
	
	/**
	 * 增值任务标题
	 */
	@Column(name = "task_title",  nullable = false) 
	private String taskTitle;
	
	/**
	 * 发布增值任务需要的发布点
	 */
	@Column(name = "need_dot",  nullable = false) 
	private BigDecimal needDot;
	
	/**
	 * 增值任务类型
	 */
	@Column(name = "type",  nullable = false) 
	@Enumerated(EnumType.STRING)
	private TaskAppreciations type;
	
	/**
	 * 增值任务受益的是哪一方
	 */
	@Column(name = "benefit_type",  nullable = false) 
	@Enumerated(EnumType.STRING)
	private BenefitTypes benefitTypes;
	
	/**
	 * 增值任务选择的结果
	 */
	@Column(name = "task_value") 
	private String taskValue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTaskOrderId() {
		return taskOrderId;
	}

	public void setTaskOrderId(Integer taskOrderId) {
		this.taskOrderId = taskOrderId;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public BigDecimal getNeedDot() {
		return needDot;
	}

	public void setNeedDot(BigDecimal needDot) {
		this.needDot = needDot;
	}

	public TaskAppreciations getType() {
		return type;
	}

	public void setType(TaskAppreciations type) {
		this.type = type;
	}

	public BenefitTypes getBenefitTypes() {
		return benefitTypes;
	}

	public void setBenefitTypes(BenefitTypes benefitTypes) {
		this.benefitTypes = benefitTypes;
	}

	public String getTaskValue() {
		return taskValue;
	}

	public void setTaskValue(String taskValue) {
		this.taskValue = taskValue;
	}
	
	
}
