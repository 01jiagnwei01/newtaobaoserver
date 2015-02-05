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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import com.gxkj.taobaoservice.enums.MailContentStatus;
@Entity
@Table(name = "mail_content")
public class MailContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3783367733494228252L;
	
	// 主键 ：@Id    主键生成方式：strategy = "increment"
		//映射表中id这个字段，不能为空，并且是唯一的
		@GenericGenerator(name = "generator", strategy = "increment")
		@GeneratedValue(generator = "generator")
		@Id
		@Column(name = "id", unique = true, nullable = false) 
		private Integer  id;
		
		
		/**
		 * 模板ID
		 */
		@NotNull(message="模板ID[templete_id]不允许为空")
		@Column(name = "templete_id",  nullable = false,length=10) 
		private Integer templeteId;
		
		/**
		 * 邮件内容
		 */
		@Column(name = "content") 
		@Lob
		@NotEmpty(message="邮件内容不允许为空")
		private String content;
		/**
		 * 邮件标题
		 */
		@Column(name = "title",length=100) 
		@NotEmpty(message="邮件标题不允许为空")
		private String title ;
		
		@Column(name = "update_time",   nullable = false ) 
		private Date updateTime;
		
		/**
		 * 状态
		 */
		@Column(name = "status",   nullable = false ) 
		@Enumerated(EnumType.STRING)
		private MailContentStatus status;
		
		@Column(name = "updateUserId",   nullable = false )
		@NotNull(message="管理员[updateUserId]不允许为空")
		private Integer updateUserId;
		
		@Transient
		private String updateUserName;
		
		@Transient
		private String templeteName;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getTempleteId() {
			return templeteId;
		}

		public void setTempleteId(Integer templeteId) {
			this.templeteId = templeteId;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

		public MailContentStatus getStatus() {
			return status;
		}

		public void setStatus(MailContentStatus status) {
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

		public String getTempleteName() {
			return templeteName;
		}

		public void setTempleteName(String templeteName) {
			this.templeteName = templeteName;
		}
		
		
		

}
