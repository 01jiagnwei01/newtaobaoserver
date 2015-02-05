package com.gxkj.taobaoservice.dto;

import java.util.List;

import com.gxkj.taobaoservice.entitys.MailContent;
import com.gxkj.taobaoservice.entitys.MailSenderTask;
import com.gxkj.taobaoservice.entitys.MailTemplete;

public class MailSenderTaskDTO extends MailSenderTask {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5381460597597202134L;
	
	private List<ShouJianRen> shouJianRen = null;
	
	private MailTemplete mailTemplete;
	
	private MailContent mailContent;

	public List<ShouJianRen> getShouJianRen() {
		return shouJianRen;
	}

	public void setShouJianRen(List<ShouJianRen> shouJianRen) {
		this.shouJianRen = shouJianRen;
	}

	public MailTemplete getMailTemplete() {
		return mailTemplete;
	}

	public void setMailTemplete(MailTemplete mailTemplete) {
		this.mailTemplete = mailTemplete;
	}

	public MailContent getMailContent() {
		return mailContent;
	}

	public void setMailContent(MailContent mailContent) {
		this.mailContent = mailContent;
	}
}
