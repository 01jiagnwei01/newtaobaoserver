package com.gxkj.taobaoservice.controllers.admin;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class APIController {
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	  binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {  
	    final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");  
	    public void setAsText(String text) throws IllegalArgumentException {  
	      try {  
	        Date date = sf.parse(text);  
	        setValue(date);  
	      } catch (ParseException e) {  
	        //Date data = sf.parse(text);  
	        throw new IllegalArgumentException(e);  
	      }  
	    }  
	  }); 
	}

	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "apiindex";
		return mv;	
	}
	@RequestMapping(value="/test1",method={RequestMethod.GET,RequestMethod.POST})  
	public String test1(@RequestParam("userId") int id, int age){
		return "";
	}
	@RequestMapping("/test2")  
	public String test2(@RequestParam("userId") int id, int age){
		return "";
	}
	@RequestMapping("/user/{nickname}/{age}")
	public String path(@PathVariable("nickname") String name, @PathVariable int age){
			return "path";
	}
	
	@RequestMapping("/redirect")  
	public String redirect(@RequestParam("userId") int id, int age){
		return "redirect:/api/index";
	}
	
	@RequestMapping("/forward")  
	public String forward(@RequestParam("userId") int id, int age){
		return "forward:/api/index";
	}
//	@RequestMapping("/updateUser")  
//	  public String updateUser(@ModelAttribute("the-attribute") User user,   
//	  BindingResult result, SessionStatus status) {  
//	  
//	  if (result.hasErrors) {  
//	     return "error";  
//	     }  
//	       
//	     userService.updateUser(user);  
//	     // 我们通过调用 status.setComplete() 方法，该 Controller 所有放在 session 级别的模型属性数据  
//	     // 将从 session 中清空  
//	    status.setComplete();  
//	     return "redirect:getUser?userId=" + user.getId();  
//	   }  


}
