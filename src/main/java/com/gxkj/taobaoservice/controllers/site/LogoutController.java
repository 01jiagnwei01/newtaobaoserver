package com.gxkj.taobaoservice.controllers.site;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	@RequestMapping(value="",method=RequestMethod.GET)
	public String login(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		request.getSession().invalidate();
		String mv = "index";
		return mv;	
	}
}
