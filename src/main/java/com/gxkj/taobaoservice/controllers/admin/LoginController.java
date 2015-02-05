package com.gxkj.taobaoservice.controllers.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.constants.StatusConstant;
import com.gxkj.common.util.PWDGenter;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.dto.SessionConstant;
import com.gxkj.taobaoservice.entitys.AdminMenu;
import com.gxkj.taobaoservice.entitys.AdminRole;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.services.AdminUserService;
import com.gxkj.taobaoservice.services.RelAdminUserRoleService;
import com.gxkj.taobaoservice.services.RoleService;

@Controller
@RequestMapping("/admin")
public class LoginController {
	 
	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private RelAdminUserRoleService relAdminUserRoleService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="")
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		 
		return "forward:/admin/login";
	}
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		if(request.getSession().getAttribute(SessionConstant._adminUserFalg)!=null){
			return "redirect:/admin/index";
		}
		String mv = "admin/login";
		return mv;
	}
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		 request.getSession().invalidate();
		 
		return "forward:/admin/login";
	}
	@RequestMapping(value="/dologin",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData dologin( @RequestParam(value="flag",defaultValue="0")int flag, 
			@RequestParam(value="name",defaultValue="")String username,
			@RequestParam(value="pass",defaultValue="")String password, 
			HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		String pass = PWDGenter.generateKen(password);
		EntityReturnData data = new EntityReturnData();
		data.setResult(false);
		data.setMsg("登陆失败");
 
		 List<AdminUser> users = adminUserService.getAdminUserByName(username);
		 AdminUser sessionUser = null;
		 if(CollectionUtils.isNotEmpty( users)){
			 for(AdminUser _temp : users){
				 if(_temp.getPassword().equals(pass)){
					 sessionUser=_temp;
					 break;
				 }
			 }
		 }
		 if(sessionUser != null && sessionUser.getStatus() == StatusConstant.isvalid){
			 /***
			  * 查看系统管理员的权限菜单和权限按钮
			  */
			 AdminRole role = relAdminUserRoleService.getRoleByUserId(sessionUser.getId());
			 if(role!=null){
				 role = roleService.getRoleById(role.getId());
				 sessionUser.setRole(role);
				 
				 //菜单列表
				 List<AdminMenu> menus = role.getRelMenus();
				 
				 Map<String ,Boolean> btnMap = new HashMap<String ,Boolean>();
				 StringBuffer menuPath = new StringBuffer();
				 if(menus != null){
					 sessionUser.setMenus( menus);
				 }
				 
				 for(AdminMenu menu :menus){
					 if(StringUtils.isNotBlank(menu.getPath() )){
						 menuPath.append(";").append(menu.getPath());
					 }
					 //按钮
					 if(menu.getIsbutton() == 1){
						 if(StringUtils.isNotBlank(menu.getBtnflag() )){
							 btnMap.put(menu.getBtnflag() , true);
						 }
					 }
				 }
				 sessionUser.setBtnMap(btnMap);
				 if(menuPath.length()>0){
					 sessionUser.setMenupaths(menuPath.append(";").toString());
				 }
			 }
			 SessionConstant.setAdminUserToSession(request, sessionUser);
			 data.setResult(true);
			 data.setMsg("登陆成功");
			 data.setEntity( sessionUser);
		 }else{
			 data.setEntity( sessionUser);
		 }
		return data;
	}
}
