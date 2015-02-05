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

import org.hibernate.annotations.GenericGenerator;

import com.gxkj.common.enums.BusinessExceptionInfos;
@Entity
@Table(name="business_exception")
public class BusinessExceptionEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -554429047596458914L;
	
	
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false) 
	private Integer id;
	
	@Column(name="userId" )
	private Integer userId;
	
	/**
	 * 创建时间 
	 */
	@Column(name="create_time")
	private Date createTime;
	
	/**
	 * 类型
	 */
	@Column(name="type" )
	@Enumerated(EnumType.STRING)
	private BusinessExceptionInfos type;
	
	/**
	 * 类路径
	 */
	@Column(name="class_path")
	private String classPath;
	
	/**
	 * 方法名称
	 */
	@Column(name="method_name")
	private String methodName;
	
	/**
	 * 方法参数的json格式
	 */
	@Column(name="param_string")
	private String paramString;

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

	public BusinessExceptionInfos getType() {
		return type;
	}

	public void setType(BusinessExceptionInfos type) {
		this.type = type;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getParamString() {
		return paramString;
	}

	public void setParamString(String paramString) {
		this.paramString = paramString;
	}
	
	

}
