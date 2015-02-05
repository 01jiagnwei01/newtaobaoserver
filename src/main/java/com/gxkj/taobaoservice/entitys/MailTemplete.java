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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.gxkj.taobaoservice.enums.MailTempleteStatus;
import com.gxkj.taobaoservice.enums.MailTempleteTypes;

/**
 * 邮件模板
 */
@Entity
@Table(name = "mail_templete")
public class MailTemplete implements Serializable {
	
		 
	private static final long serialVersionUID = -7535189939594291593L;
	
	// 主键 ：@Id    主键生成方式：strategy = "increment"
	//映射表中id这个字段，不能为空，并且是唯一的
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer  id;
	
	
	/**
	 * 模板名称
	 */
	@Column(name = "templete_name", unique = true, nullable = false,length=100) 
	private String templeteName;
	
	
	/**
	 * 模板路径
	 */
	@Column(name = "templete_path", unique = true, nullable = false,length=100) 
	private String templetePath;
	
	@Column(name = "update_time",   nullable = false ) 
	private Date updateTime;
	
	/**
	 * 状态
	 */
	@Column(name = "status",   nullable = false ) 
	@Enumerated(EnumType.STRING)
	private MailTempleteStatus status;
	
	/**
	 * 模板类型
	 */
	@Column(name = "templete_type",   nullable = false ) 
	@Enumerated(EnumType.STRING)
	private MailTempleteTypes type;
	
	/**
	 * 模板描述
	 */
	@Column(name = "templete_desc",   nullable = false ) 
	private String templeteDesc;
	
	/**
	 * 修改人
	 */
	@Column(name = "updateUserId",   nullable = false )
	private Integer updateUserId;
	
	@Transient
	private String updateUserName;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTempleteName() {
		return templeteName;
	}


	public void setTempleteName(String templeteName) {
		this.templeteName = templeteName;
	}


	public String getTempletePath() {
		return templetePath;
	}


	public void setTempletePath(String templetePath) {
		this.templetePath = templetePath;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public MailTempleteStatus getStatus() {
		return status;
	}


	public void setStatus(MailTempleteStatus status) {
		this.status = status;
	}


	public Integer getUpdateUserId() {
		return updateUserId;
	}


	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}


	public String getUpdateUserName() {
		return updateUserName;
	}


	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}


	public MailTempleteTypes getType() {
		return type;
	}


	public void setType(MailTempleteTypes type) {
		this.type = type;
	}


	public String getTempleteDesc() {
		return templeteDesc;
	}


	public void setTempleteDesc(String templeteDesc) {
		this.templeteDesc = templeteDesc;
	}
	
	
	
	

}
