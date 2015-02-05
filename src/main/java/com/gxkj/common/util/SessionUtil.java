package com.gxkj.common.util;

import javax.servlet.http.HttpServletRequest;

import com.gxkj.taobaoservice.entitys.UserBase;

public class SessionUtil {
	
	private final static String siteUserFlag = "site_user_flag";
	
	public static void setSiteUser2Session(HttpServletRequest request, UserBase userBase){
		request.getSession().setAttribute(siteUserFlag, userBase);
	}
	public static UserBase getSiteUserInSession(HttpServletRequest request){
		return (UserBase) request.getSession().getAttribute(siteUserFlag);
	}

}
