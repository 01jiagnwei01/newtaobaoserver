package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
 

@Entity
@Table(name = "admin_menu")
public class AdminMenu  implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3545715545966442853L;

	// 主键 ：@Id    主键生成方式：strategy = "increment"
	//映射表中id这个字段，不能为空，并且是唯一的
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false) 
	private Integer  id;
	 
	@Column(name = "name", length = 500)
	private String name;
	
	@Column(name = "path", length = 500) 
	private String path;
	
	 
	@Column(name = "isbutton" )  
	private Integer isbutton;
	 
	@Column(name = "btnflag", length = 50)
	private String btnflag;
	
	@Column(name = "pid" )
	private Integer pid;

	
	
	public Integer getId() {
		return id;
	}

	 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	 
	public int getIsbutton() {
		return isbutton;
	}

	public void setIsbutton(Integer isbutton) {
		this.isbutton = isbutton;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBtnflag() {
		return btnflag;
	}

	 
	public void setBtnflag(String btnflag) {
		this.btnflag = btnflag;
	}
	public int getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	 
	

}
