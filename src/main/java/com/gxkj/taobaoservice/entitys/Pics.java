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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.gxkj.taobaoservice.enums.PicStatus;
@Entity
@Table(name = "pics")
public class Pics implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8559290287128814889L;

	// 主键 ：@Id    主键生成方式：strategy = "increment"
		//映射表中id这个字段，不能为空，并且是唯一的
		@GenericGenerator(name = "generator", strategy = "increment")
		@GeneratedValue(generator = "generator")
		@Id
		@Column(name = "id", unique = true, nullable = false) 
		private Integer  id;
		 
		/**
		 * 图片路径
		 */
		@Column(name = "pic_path", length = 200,nullable=false)
		private String picPath;
		
		/**
		 * 图片名称
		 */
		@Column(name = "pic_name", length = 100,nullable=false)
		private String picName;
		 
		/**
		 * 图片描述
		 */
		@Column(name = "pic_desc", length = 100,nullable=true)
		private String picDesc;
		
		/**
		 * 图片状态
		 */
		@Column(name = "status", length = 100,   nullable = false)
		@Enumerated(EnumType.STRING)
		private PicStatus status;
		
		/**
		 * 创建时间
		 */
		@Column(name = "create_time" ,   nullable = false)
		@Temporal(TemporalType.TIMESTAMP )
		private Date createTime;
		
		/**
		 * 创建人
		 */
		@Column(name = "create_user" )
		private Integer createUser;
		
		/**
		 * 创建人姓名
		 */
		@Transient
		private String createUserName;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getPicPath() {
			return picPath;
		}

		public void setPicPath(String picPath) {
			this.picPath = picPath;
		}

		public String getPicName() {
			return picName;
		}

		public void setPicName(String picName) {
			this.picName = picName;
		}

		public String getPicDesc() {
			return picDesc;
		}

		public void setPicDesc(String picDesc) {
			this.picDesc = picDesc;
		}

		public PicStatus getStatus() {
			return status;
		}

		public void setStatus(PicStatus status) {
			this.status = status;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Integer getCreateUser() {
			return createUser;
		}

		public void setCreateUser(Integer createUser) {
			this.createUser = createUser;
		}

		public String getCreateUserName() {
			return createUserName;
		}

		public void setCreateUserName(String createUserName) {
			this.createUserName = createUserName;
		}
 

}
