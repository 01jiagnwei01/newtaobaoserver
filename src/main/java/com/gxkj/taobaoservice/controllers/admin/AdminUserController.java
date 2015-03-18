package com.gxkj.taobaoservice.controllers.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.common.util.PWDGenter;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.dto.SessionConstant;
import com.gxkj.taobaoservice.entitys.AdminRole;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.services.AdminUserService;
 
 

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "admin/adminuser";
		return mv;
	}
	
	 @RequestMapping(value="/dopage",method={RequestMethod.GET})
	 @ResponseBody
    public EntityReturnData doPage( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="realname",defaultValue="") String realname,
			@RequestParam(value="status",defaultValue="0")  int status,
			@RequestParam(value="pageno",defaultValue="0") int pageno,
    		@RequestParam(value="limit",defaultValue="20") int pagesize
    		,ModelMap modelMap)  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
			try {
				ListPager paper = adminUserService.doPage(pageno, pagesize, realname, status);
				ret.setEntity(paper);
			} catch (Exception e) {
				e.printStackTrace();
				ret.setMsg("失败");
				ret.setResult(false);
			}
			return ret;
	}
	@RequestMapping(value="/doadd",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doAdd( HttpServletRequest request, HttpServletResponse response,
			AdminUser entity, 
			@RequestParam(value="roleName",defaultValue="") String roleName,
			@RequestParam(value="roleid",defaultValue="0") int roleid,
			ModelMap modelMap) throws Exception  {
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setResult(true);
		
			if(roleid!=0){
				
				AdminRole role = new AdminRole();
				role.setId(roleid);
				role.setName(roleName);
				entity.setRole(role);
			}
			adminUserService.addAdminUser(entity);
			ret.setEntity(entity);
		
		return ret;
	}
	
	@RequestMapping(value="/doupdate",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doUpdate( @RequestParam(value="roleName",defaultValue="") String roleName,
			@RequestParam(value="roleid",defaultValue="0") int roleid,
			HttpServletRequest request, HttpServletResponse response,
			AdminUser entity,  ModelMap modelMap) throws SQLException, BusinessException  {
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setResult(true);
		if(roleid!=0){
			AdminRole role = new AdminRole();
			role.setId(roleid);
			role.setName(roleName);
			entity.setRole(role);
		}
		adminUserService.updateAdminUser(entity);
		return ret;
	}
	@RequestMapping(value="/dodel ",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData updateStatus( 
			@RequestParam(value="status",defaultValue="0") int status,
			@RequestParam(value="id",defaultValue="0") int  id,
			HttpServletRequest request, HttpServletResponse response,  ModelMap modelMap)  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
			try {
				adminUserService.updateStatus(status, id);
			} catch (Exception e) {
				e.printStackTrace();
				ret.setMsg("失败");
				ret.setResult(false);
			}
			return ret;
	}
	@ResponseBody
	@RequestMapping(value="/setpassword ",method={RequestMethod.GET})
	public EntityReturnData updatePasswordById( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="id",defaultValue="0") int id,
			@RequestParam(value="password",defaultValue="") String password,
    		ModelMap modelMap)  {
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setResult(true);
		try {
			 adminUserService.updatePasswordById(id,PWDGenter.generateKen(password));
		} catch (Exception e) {
			e.printStackTrace();
			ret.setMsg("失败");
			ret.setResult(false);
			
		}
		return ret;
	}
	@ResponseBody
	@RequestMapping("/setmypassword")
	public EntityReturnData setmypassword( HttpServletRequest request,
			HttpServletResponse response,
			String password,
			String confirmpassword,
    		ModelMap modelMap)  {
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setResult(false);
		try {
			if(StringUtils.isEmpty(password )){
				ret.setMsg("新密码不能为空");
			 }else if(StringUtils.isEmpty(confirmpassword )){
				 ret.setMsg("确认密码不能为空");
			 }else if(!confirmpassword.equals(password)){
				 ret.setMsg("确认密码与新密码不一致");
			 }else{
				 AdminUser user = SessionConstant.getAdminUserInSession(request);
				 adminUserService.updatePasswordById(user.getId(),PWDGenter.generateKen(password));
				 ret.setResult(true);
			 }
				
		} catch (Exception e) {
			e.printStackTrace();
			ret.setMsg("失败");
			ret.setResult(false);
			
		}
		return ret;
	}
	

}
