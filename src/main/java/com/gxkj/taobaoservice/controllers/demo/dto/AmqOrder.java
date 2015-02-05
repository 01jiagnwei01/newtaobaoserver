package com.gxkj.taobaoservice.controllers.demo.dto;

import java.io.Serializable;

public class AmqOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9198244341714205220L;
	
  private 	String msg = null;

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

 
@Override
public String toString() {
	return "{msg:" + msg + "}";
}


  
  

}
