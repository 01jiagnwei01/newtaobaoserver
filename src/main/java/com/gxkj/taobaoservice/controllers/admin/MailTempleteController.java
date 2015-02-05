package com.gxkj.taobaoservice.controllers.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.dto.SessionConstant;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.MailTemplete;
import com.gxkj.taobaoservice.services.MailTempleteService;

@Controller
@RequestMapping("/admin/mail/templete")
public class MailTempleteController {
	
	@Autowired
	private MailTempleteService mailTempleteService;
	
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "admin/mail/mailtemplete";
		return mv;
	}
	
	@RequestMapping(value="/dopage",method={RequestMethod.POST})
	 @ResponseBody
	 public EntityReturnData doPage( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="pageno",defaultValue="0") int pageno,
			@RequestParam(value="limit",defaultValue="20") int pagesize,
			@RequestParam(value="templeteName",defaultValue="") String templeteName ,
			ModelMap modelMap) throws SQLException  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
			 
			ListPager paper = mailTempleteService.doPage(pageno, pagesize, templeteName);
			ret.setEntity(paper);
			 
			return ret;
	}
	
	@RequestMapping(value="/doadd",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doAdd( HttpServletRequest request,
			HttpServletResponse response,
			MailTemplete entity,
			ModelMap modelMap) throws SQLException, BindException  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			entity = mailTempleteService.doAddMailTemplete(entity, adminUser);
			ret.setEntity(entity);
			 
			return ret;
	}
	@RequestMapping(value="/doupdate",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doUpdate( HttpServletRequest request,
			HttpServletResponse response,
			MailTemplete entity,
			ModelMap modelMap) throws SQLException, BindException  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			entity = mailTempleteService.doUpdateMailTemplete(entity, adminUser);
			ret.setEntity(entity);
			
			return ret;
	}	
	
	@RequestMapping(value="/dodel",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData dodel( HttpServletRequest request,
			HttpServletResponse response,
			int id,
			ModelMap modelMap) throws SQLException  {
			EntityReturnData ret = new EntityReturnData();
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			boolean result =   mailTempleteService.doDelMailTemplete(id, adminUser);
			ret.setResult(result);
			
			return ret;
	}

}
