package com.gxkj.common.security.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/login")
public class LoginController {
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public String adminlogin(HttpServletRequest request,@RequestParam(defaultValue = "" ,value="error") String error,ModelAndView map  ) throws Exception{
		map.addObject("error", error);
		return "admin/login";
	} 
}
