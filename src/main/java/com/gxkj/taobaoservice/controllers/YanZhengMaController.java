package com.gxkj.taobaoservice.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.util.RandomValidateCode;
import com.gxkj.taobaoservice.dto.EntityReturnData;

@Controller
@RequestMapping("/yanzhengma")
public class YanZhengMaController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public void path(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		 response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
	        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expire", 0);
	        RandomValidateCode randomValidateCode = new RandomValidateCode();
	        try {
	            randomValidateCode.drawRandcode(request, response);//输出图片方法
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public EntityReturnData getYanZhengma(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		EntityReturnData ret = new EntityReturnData();
		String yanzhengma = (String) request.getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
		ret.setEntity(yanzhengma);
		ret.setResult(true);
		 return ret;
	}
}
