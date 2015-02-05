package com.gxkj.taobaoservice.controllers.site;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *订单管理
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String order_create_get(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "site/order/order_create_page";
		return mv;	
	}
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String order_create_post(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "site/order/order_create_sure";
		return mv;	
	}
}
