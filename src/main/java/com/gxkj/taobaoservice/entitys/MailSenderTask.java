package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import com.gxkj.taobaoservice.enums.MailSenderStatus;
/**
 * 邮件发送任务
 * @author Administrator
 *
 */
@Entity
@Table(name = "mail_sender_task")
public class MailSenderTask implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5706118697478529778L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer  id;
	
	/**
	 * 创建人ID 
	 */
	@Column(name = "create_user" ,   nullable = false) 
	private Integer  createUser;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time" ,   nullable = false)
	@Temporal(TemporalType.TIMESTAMP )
	private Date createTime;
	
	
	/**
	 * 邮件标题
	 */
	@Column(name = "title",length=100) 
	@NotEmpty(message="邮件标题不允许为空")
	private String  title;
	
	/**
	 * 邮件内容
	 */
	@Column(name = "content") 
	@Lob
	@NotEmpty(message="邮件内容不允许为空")
	private String content;
	
	/**
	 * 状态
	 */
	@Column(name = "status" ,   nullable = false)
	@Enumerated(EnumType.STRING)
	private MailSenderStatus status;
	
	/**
	 * 创建用户用户名
	 */
	@Transient
	private String createUserName;
	
	@Column(name = "content_id" ,   nullable = false)
	private Integer   contentId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MailSenderStatus getStatus() {
		return status;
	}

	public void setStatus(MailSenderStatus status) {
		this.status = status;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	

}
