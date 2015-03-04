package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.gxkj.taobaoservice.enums.TaskBasicLogUserType;
import com.gxkj.taobaoservice.enums.TaskStatus;
@Entity
@Table(name = "task_basic_log")
public class TaskBasicLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2294810666949803402L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer  id;
	
	/**
	 * 任务ID
	 */
	@Column(name="task_basic_id" ) 
	private Integer taskBasicId;
	
	
	/**
	 * 订单创建用户ID
	 */
	@Column(name="user_id" ) 
	private Integer userId;
	
	/**
	 * 用户类型
	 */
	@Column(name="user_type" )
	@Enumerated(EnumType.STRING)
	private TaskBasicLogUserType userType;
	
	@Column(name="task_state" )
	@Enumerated(EnumType.STRING)
	@NotNull(message="任务状态不能为空")
	private TaskStatus status = TaskStatus.Wait_For_Receive;
	
	/**
	 * 创建时间
	 */
	@NotNull(message="创建时间不能为空")
	@Column(name="create_time" )
	private  Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTaskBasicId() {
		return taskBasicId;
	}

	public void setTaskBasicId(Integer taskBasicId) {
		this.taskBasicId = taskBasicId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public TaskBasicLogUserType getUserType() {
		return userType;
	}

	public void setUserType(TaskBasicLogUserType userType) {
		this.userType = userType;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public TaskBasicLog(Integer taskBasicId, Integer userId,
			TaskBasicLogUserType userType, TaskStatus status, Date createTime) {
		super();
		this.taskBasicId = taskBasicId;
		this.userId = userId;
		this.userType = userType;
		this.status = status;
		this.createTime = createTime;
	}

	public TaskBasicLog() {
		super();
	}
	
	

}
