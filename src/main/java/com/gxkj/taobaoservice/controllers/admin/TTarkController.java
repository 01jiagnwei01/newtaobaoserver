package com.gxkj.taobaoservice.controllers.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/task")
public class TTarkController {

	@RequestMapping(value="/haveclose",method={RequestMethod.GET})
	public String haveclose(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "admin/task/haveclose";
		return mv;
	}
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String create(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "admin/task/create";
		return mv;
	}
	
	
}
