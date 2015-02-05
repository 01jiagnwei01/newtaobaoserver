package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

public class Notification  implements Serializable {
	
		 
	private static final long serialVersionUID = -7535189939594291593L;
	
	// 主键 ：@Id    主键生成方式：strategy = "increment"
	//映射表中id这个字段，不能为空，并且是唯一的
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer  id;
	
	
	@Column(name = "message")
	private String message;


	public Notification(  String message) {
		 this.message = message;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
