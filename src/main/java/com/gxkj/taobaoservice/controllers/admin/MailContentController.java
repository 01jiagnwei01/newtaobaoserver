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
import com.gxkj.taobaoservice.entitys.MailContent;
import com.gxkj.taobaoservice.services.MailContentService;

@Controller
@RequestMapping("/admin/mail/content")
public class MailContentController {
	
	@Autowired
	private MailContentService mailContentService;
	
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "admin/mail/mailcontent";
		return mv;
	}
	
	@RequestMapping(value="/dopage",method={RequestMethod.POST})
	 @ResponseBody
  public EntityReturnData doPage( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="pageno",defaultValue="0") int pageno,
			@RequestParam(value="limit",defaultValue="20") int pagesize,
			@RequestParam(value="title",defaultValue="") String title ,
			ModelMap modelMap) throws SQLException  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
		
			ListPager paper = mailContentService.doPage(pageno, pagesize, title);
			ret.setEntity(paper);
			
			return ret;
	}
	@RequestMapping(value="/doadd",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doAdd( HttpServletRequest request,
			HttpServletResponse response,
			MailContent mailContent,
			ModelMap modelMap) throws SQLException, BindException  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			mailContent = mailContentService.doAddMailContent(mailContent, adminUser);
			ret.setEntity(mailContent);
			 
			return ret;
	}
	@RequestMapping(value="/doupdate",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doUpdate( HttpServletRequest request,
			HttpServletResponse response,
			MailContent mailContent,
			ModelMap modelMap) throws SQLException, BindException  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
			
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			mailContent = mailContentService.doUpdateMailContent(mailContent, adminUser);
			ret.setEntity(mailContent);
			
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
			boolean result =   mailContentService.doDelMailContent(id, adminUser);
			ret.setResult(result);
			
			return ret;
	}
	

}
