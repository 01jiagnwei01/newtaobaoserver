package com.gxkj.taobaoservice.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gxkj.common.util.SystemGlobals;
import com.gxkj.taobaoservice.util.SubHeaderTag;

@Controller
@RequestMapping("/red5")
public class Red5Controller {

	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			@RequestParam(value="dev",defaultValue="") String dev){
		String mv = "site/red5/index";
		if(StringUtils.isBlank(dev)){
			modelMap.put("www", SystemGlobals.getPreference("taobaserver.video.online.url"));
		}else{
			modelMap.put("www", SystemGlobals.getPreference("taobaserver.video.dev.url"));
		}
		 
		modelMap.put(SubHeaderTag.tagName, "aboutus");
		return mv;	
	}
}
