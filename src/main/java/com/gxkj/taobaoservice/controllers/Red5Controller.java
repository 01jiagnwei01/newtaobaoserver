package com.gxkj.taobaoservice.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gxkj.taobaoservice.util.SubHeaderTag;

@Controller
@RequestMapping("/red5")
public class Red5Controller {

	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "site/red5/index";
		modelMap.put(SubHeaderTag.tagName, "aboutus");
		return mv;	
	}
}
