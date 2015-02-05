package com.gxkj.taobaoservice.dto;

import java.io.Serializable;
/**
 * 
 * 发短信的响应信息
 *
 */
public class SmsResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7717377038978976087L;
	/**
	 * 响应结果 0:true;!0:false
	 */
	private boolean result;
	/**
	 * sendid
	 */
	private String sendId;
	
	/**
	 * 无效号码数
	 */
	private  int invalidNum;
	
	/**
	 * 成功提交数
	 */
	private int successNum;
	
	/**
	 * 黑名单数
	 */
	private int inBlackNum;
	/**
	 * 消息
	 */
	private String msg;
	
	/**
	 * 响应编码
	 */
	private String code;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public int getInvalidNum() {
		return invalidNum;
	}

	public void setInvalidNum(int invalidNum) {
		this.invalidNum = invalidNum;
	}

	public int getSuccessNum() {
		return successNum;
	}

	public void setSuccessNum(int successNum) {
		this.successNum = successNum;
	}

	public int getInBlackNum() {
		return inBlackNum;
	}

	public void setInBlackNum(int inBlackNum) {
		this.inBlackNum = inBlackNum;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
