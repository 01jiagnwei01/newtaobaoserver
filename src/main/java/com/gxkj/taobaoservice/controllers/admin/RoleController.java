package com.gxkj.taobaoservice.controllers.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.AdminRole;
import com.gxkj.taobaoservice.services.RoleService;

@Controller
@RequestMapping("/admin/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "admin/easyui_roleindex";
		return mv;
	}
	
	@ResponseBody @RequestMapping("/dopage")
    public ListPager doPage( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="name",defaultValue="")String name,
			@RequestParam(value="status",defaultValue="0") int status,
			@RequestParam(value="pageno",defaultValue="0") int pageno,
    		@RequestParam(value="limit",defaultValue="20") int pagesize
    		,ModelMap modelMap)  {
		try {
			ListPager pager =  roleService.doPage(pageno,pagesize,name,status);
			return pager;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@RequestMapping(value="/doadd",method = RequestMethod.POST)
	public AdminRole doAdd( HttpServletRequest request,
			HttpServletResponse response,
			AdminRole role,String menuIds,
    		ModelMap modelMap)  {
		try {
			roleService.addRole(role,menuIds.split(","));
			return role;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/doupdate",method = RequestMethod.POST)
	public AdminRole doupdate( HttpServletRequest request,
			HttpServletResponse response,
			AdminRole role,String menuIds,
    		ModelMap modelMap)  {
		try {
			roleService.updateRole(role,menuIds.split(","));
			return role;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@RequestMapping(value="/setstatus",method = RequestMethod.POST)
	public Map<String,Object> setstatus( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="ids",defaultValue="0") String ids, int  status,
    		ModelMap modelMap)  {
		Map<String,Object> json = new HashMap<String,Object>();
		json.put("success", false);
		try {
			
			roleService.updateStatus(ids.split(","),status);
			json.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	@ResponseBody
	@RequestMapping("/get")
	public AdminRole get( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="id",defaultValue="0")  int id,
    		ModelMap modelMap)  {
		 
		try {
			 AdminRole role =roleService.getRoleById(id);
			 return role;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
