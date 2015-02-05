package com.gxkj.taobaoservice.dto;

import java.util.ArrayList;
import java.util.List;

public class EmailExcuteResult {
	
	public List<String> addressErrorMails = new ArrayList<String> ();
	
	public List<String> otherErrorMails = new ArrayList<String> ();

	public List<String> getAddressErrorMails() {
		return addressErrorMails;
	}

	public void setAddressErrorMails(List<String> addressErrorMails) {
		this.addressErrorMails = addressErrorMails;
	}

	public List<String> getOtherErrorMails() {
		return otherErrorMails;
	}

	public void setOtherErrorMails(List<String> otherErrorMails) {
		this.otherErrorMails = otherErrorMails;
	}
	
	

}
