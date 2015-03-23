package com.gxkj.taobaoservice.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/xuetang")
public class XueTangController {
	@RequestMapping("/page")
	public String page(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		return "site/xuetang/page";
	}
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		return "site/xuetang/list";
	}
}
