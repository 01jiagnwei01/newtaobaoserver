package com.gxkj.taobaoservice.controllers.admin;

import java.io.IOException;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.dto.MailSenderTaskDTO;
import com.gxkj.taobaoservice.dto.SessionConstant;
import com.gxkj.taobaoservice.dto.ShouJianRen;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.MailSenderTask;
import com.gxkj.taobaoservice.enums.MailSenderRefAddressListStatus;
import com.gxkj.taobaoservice.enums.MailSenderStatus;
import com.gxkj.taobaoservice.services.MailSenderRefAddressListService;
import com.gxkj.taobaoservice.services.MailSenderTaskService;

@Controller
@RequestMapping("/admin/mail/sendertask")
public class MailSenderTaskController {
	
	@Autowired
	private MailSenderTaskService mailSenderTaskService;
	
	@Autowired
	private  MailSenderRefAddressListService mailSenderRefAddressListService;
	
	
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "admin/mail/mailsendertask";
		return mv;
	}
	
	@RequestMapping(value="/dopage",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doPage( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="pageno",defaultValue="0") int pageno,
			@RequestParam(value="limit",defaultValue="20") int pagesize,
			@RequestParam(value="title",defaultValue="") String title ,
			@RequestParam(value="createUser",defaultValue="0") Integer createUser ,
			@RequestParam(value="status",required=false) MailSenderStatus status ,
			
			ModelMap modelMap) throws SQLException  {
			EntityReturnData ret = new EntityReturnData();
			
		
			ListPager paper = mailSenderTaskService.doPage(pageno, pagesize, title,status,createUser);
			ret.setEntity(paper);
			ret.setMsg("执行成功");
			ret.setResult(true);
			return ret;
	}
	@RequestMapping(value="/doadd",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doAdd( HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam(value="contentId",defaultValue="0") int contentId,
			@RequestParam(value="shoujianren" ,defaultValue="[]" )String shoujianren,
			ModelMap modelMap) throws SQLException, BindException, BusinessException, JsonParseException, JsonMappingException, IOException  {
			EntityReturnData ret = new EntityReturnData();
			
			ObjectMapper mapper = new ObjectMapper();
			ShouJianRen[] shoujianrens = mapper.readValue(shoujianren, ShouJianRen[].class);
			 
			  
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			 MailSenderTask task = mailSenderTaskService.doAddMailSendTask(contentId,adminUser,shoujianrens);
			ret.setEntity(task);
			ret.setResult(true);
			ret.setMsg("执行成功");
			return ret;
	}
	@RequestMapping(value="/doupdate",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doUpdate( HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam(value="taskId",defaultValue="0") int taskId,
			@RequestParam(value="contentId",defaultValue="0") int contentId,
			@RequestParam(value="shoujianren" ,defaultValue="[]" )String shoujianren,
			ModelMap modelMap) throws SQLException, BindException, BusinessException, JsonParseException, JsonMappingException, IOException  {
			EntityReturnData ret = new EntityReturnData();
			
			ObjectMapper mapper = new ObjectMapper();
			ShouJianRen[] shoujianrens = mapper.readValue(shoujianren, ShouJianRen[].class);
			 
			  
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			 MailSenderTask task = mailSenderTaskService.doUpdateMailSendTask(taskId,contentId,adminUser,shoujianrens);
			ret.setEntity(task);
			ret.setResult(true);
			ret.setMsg("执行成功");
			return ret;
	}
	
	@RequestMapping(value="/dosend",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData dosend( HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam(value="taskid",defaultValue="0") int taskId,
			ModelMap modelMap) throws SQLException, BindException, BusinessException   {
			EntityReturnData ret = new EntityReturnData();
			
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			 MailSenderTask task = mailSenderTaskService.doSendMailTask(taskId,adminUser);
			ret.setEntity(task);
			ret.setResult(true);
			ret.setMsg("执行成功");
			return ret;
	}
	@RequestMapping(value="/dodel",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData dodel( HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam(value="taskId",defaultValue="0") Integer taskId , 
			ModelMap modelMap) throws SQLException, BusinessException  {
			EntityReturnData ret = new EntityReturnData();
			mailSenderTaskService.doDelTask(taskId);
			ret.setMsg("执行成功");
			ret.setResult(true);
			return ret;
	}
	
	@RequestMapping(value="/detailpage",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doDetailPage( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="pageno",defaultValue="0") int pageno,
			@RequestParam(value="limit",defaultValue="20") int pagesize,
			@RequestParam(value="email",defaultValue="") String email ,
			@RequestParam(value="taskId",defaultValue="0") Integer taskId ,
			@RequestParam(value="status",required=false) MailSenderRefAddressListStatus status ,
			
			ModelMap modelMap) throws SQLException  {
			EntityReturnData ret = new EntityReturnData();
			if(taskId == 0){
				ret.setEntity(null);
			}else{
				ListPager paper = mailSenderRefAddressListService.doPage(pageno, pagesize, taskId, email, status);
				ret.setEntity(paper);
			}
			ret.setMsg("执行成功");
			ret.setResult(true);
			return ret;
	}
	@RequestMapping(value="/get",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData get( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="taskId",defaultValue="0") Integer taskId  ,
			
			ModelMap modelMap) throws SQLException  {
			EntityReturnData ret = new EntityReturnData();
			if(taskId == 0){
				ret.setEntity(null);
			}else{
				MailSenderTaskDTO task =  mailSenderTaskService.getMailSenderTaskById(taskId);
				 ret.setEntity(task);
			}
			ret.setMsg("执行成功");
			ret.setResult(true);
			return ret;
	}

}
