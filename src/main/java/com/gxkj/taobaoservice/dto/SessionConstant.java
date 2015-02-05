package com.gxkj.taobaoservice.dto;

import javax.servlet.http.HttpServletRequest;

import com.gxkj.taobaoservice.entitys.AdminUser;

 

 

public class SessionConstant {

	public static final String _adminUserFalg = "_adminUser_";
	
	public static AdminUser getAdminUserInSession(HttpServletRequest request){
		return (AdminUser) request.getSession().getAttribute(_adminUserFalg);
	}
	public static void setAdminUserToSession(HttpServletRequest request,AdminUser user){
		  request.getSession().setAttribute(_adminUserFalg,user);
	}
	
	public static void main(String[] args) {
	 
	}
}
