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

import com.gxkj.taobaoservice.enums.SubTaskInfoBenefitPerson;
import com.gxkj.taobaoservice.enums.SubTaskInfoBenefitTypes;
import com.gxkj.taobaoservice.enums.SubTaskInfoTypes;
/**
 * 
 *  订单关联的增值业务
 *  
 *
 */
@Entity
@Table(name = "task_order_sub_task_info")
public class TaskOrderSubTaskInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4377462593543172415L;
	
	// 主键 ：@Id    主键生成方式：strategy = "increment"
		//映射表中id这个字段，不能为空，并且是唯一的
		@GenericGenerator(name = "generator", strategy = "increment")
		@GeneratedValue(generator = "generator")
		@Id
		@Column(name = "id", unique = true, nullable = false) 
		private Integer  id;
		
		
		@Column(name = "task_order_id",  nullable = false) 
		private Integer  taskOrderId;
		
		
		
		/**
		 * 关键字标识
		 */
		@Column(name = "task_key", unique = true, nullable = false,length=30) 
		private String taskKey;
		
		/**
		 * 子任务类型：基本 or 增值
		 */
		@Column(name = "type", nullable = false,length=10) 
		@Enumerated(EnumType.STRING)
		private SubTaskInfoTypes type;
		
		
		/**
		 * 需要支付数量
		 */
		@Column(name="amount" )
		private BigDecimal amount ;
		
		/**
		 * 现金 or 平台点
		 */
		@Column(name="benefit_type" )
		@Enumerated(EnumType.STRING)
		private SubTaskInfoBenefitTypes benefitType;
		

		/**
		 * 受益人  平台 or 接手人
		 */
		@Column(name="benefit_persion" )
		@Enumerated(EnumType.STRING)
		private SubTaskInfoBenefitPerson benefitPersion;
		
		
		/**
		 * 收货地址，接手人ID等
		 */
		@Column(name = "input_value",  nullable = true) 
		private String  inputValue;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		 

		public SubTaskInfoTypes getType() {
			return type;
		}

		public void setType(SubTaskInfoTypes type) {
			this.type = type;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public SubTaskInfoBenefitTypes getBenefitType() {
			return benefitType;
		}

		public void setBenefitType(SubTaskInfoBenefitTypes benefitType) {
			this.benefitType = benefitType;
		}

		public SubTaskInfoBenefitPerson getBenefitPersion() {
			return benefitPersion;
		}

		public void setBenefitPersion(SubTaskInfoBenefitPerson benefitPersion) {
			this.benefitPersion = benefitPersion;
		}

		public Integer getTaskOrderId() {
			return taskOrderId;
		}

		public void setTaskOrderId(Integer taskOrderId) {
			this.taskOrderId = taskOrderId;
		}

		public String getInputValue() {
			return inputValue;
		}

		public void setInputValue(String inputValue) {
			this.inputValue = inputValue;
		}

		public String getTaskKey() {
			return taskKey;
		}

		public void setTaskKey(String taskKey) {
			this.taskKey = taskKey;
		}
 
	
}
