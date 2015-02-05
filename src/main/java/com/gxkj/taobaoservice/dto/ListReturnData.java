package com.gxkj.taobaoservice.dto;

import java.io.Serializable;
import java.util.List;

public class ListReturnData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4419677389438361665L;

	/**
	 * 成功与否
	 */
	private boolean result;
	
	/**
	 * 操作结果 
	 */
	private String msg;
	
	/**
	 * 返回对象列表
	 */
	private List<Object> entitys;

	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the entitys
	 */
	public List<Object> getEntitys() {
		return entitys;
	}

	/**
	 * @param entitys the entitys to set
	 */
	public void setEntitys(List<Object> entitys) {
		this.entitys = entitys;
	}
	
	
}
