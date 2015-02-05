package com.gxkj.taobaoservice.controllers.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.taobaoservice.dto.SessionConstant;
import com.gxkj.taobaoservice.entitys.AdminMenu;
import com.gxkj.taobaoservice.entitys.AdminUser;

 


@Controller
@RequestMapping("/admin")
public class AdminIndexController {
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "admin/index";
		modelMap.put(SessionConstant._adminUserFalg, request.getSession().getAttribute(SessionConstant._adminUserFalg));
		return mv;
	}
	
	@RequestMapping(value="/mymenu")
	@ResponseBody
	public List<AdminMenu>  mymenu(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		AdminUser user = SessionConstant.getAdminUserInSession(request);
		return user.getMenus();
	}
	
	
	
	

}
