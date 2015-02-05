package com.gxkj.taobaoservice.dto;

import java.io.Serializable;

public class EntityReturnData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3691383085130219034L;
	/**
	 * 成功与否
	 */
	private boolean result = false;
	/**
	 * 操作结果 
	 */
	private String msg;
	/**
	 * 返回对象
	 */
	private Object entity;

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
	 * @return the entity
	 */
	public Object getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(Object entity) {
		this.entity = entity;
	}
	
	

}
