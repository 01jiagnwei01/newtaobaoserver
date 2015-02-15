package com.gxkj.taobaoservice.controllers.site;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/useraccount")
public class UserAccountController {

	@RequestMapping(value="",method=RequestMethod.GET)
	public String order_create_get(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "forward:/caozuoma";
		return mv;	
	}
}
