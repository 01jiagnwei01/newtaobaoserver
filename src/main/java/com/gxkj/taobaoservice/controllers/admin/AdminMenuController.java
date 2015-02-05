package com.gxkj.taobaoservice.controllers.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.taobaoservice.entitys.AdminMenu;
import com.gxkj.taobaoservice.services.MenuService;

 

@Controller
@RequestMapping("/admin/menu")
public class AdminMenuController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "admin/menuindex";
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public List<AdminMenu> getList( HttpServletRequest request,
			HttpServletResponse response,
    		ModelMap modelMap)  {
		try {
			return	menuService.getAllMenu();
			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping("/doadd")
	public AdminMenu doAdd( HttpServletRequest request,
			HttpServletResponse response,
			AdminMenu entity, 
    		ModelMap modelMap)  {
		try {
			 
			 return menuService.addMenu(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@RequestMapping("/doupdate")
	public AdminMenu doUpdate( HttpServletRequest request,
			HttpServletResponse response,
			AdminMenu entity, 
    		ModelMap modelMap)  {
		try {
			 return menuService.updateMenu(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@RequestMapping("/dodel")
	public Map<String,Object> dodel( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("id") int id,
    		ModelMap modelMap)  {
		try {
			Map<String,Object> json = new HashMap<String,Object>();
			json.put("success", false);
			  menuService.deleteMenuById(id);
			 json.put("success", true);
			 return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
