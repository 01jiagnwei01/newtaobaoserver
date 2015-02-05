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
import org.springframework.web.multipart.MultipartFile;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.dto.SessionConstant;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.MailAddressList;
import com.gxkj.taobaoservice.enums.MailAddressListStatus;
import com.gxkj.taobaoservice.services.MailAddressListService;

@Controller
@RequestMapping("/admin/mail/addresslist")
public class MailAddressListController {
	
	@Autowired
	private MailAddressListService mailAddressListService;
	
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "admin/mail/mailaddress";
		return mv;
	}
	
	
	@RequestMapping(value="/dopage",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doPage( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="pageno",defaultValue="0") int pageno,
			@RequestParam(value="limit",defaultValue="20") int pagesize,
			@RequestParam(value="name",defaultValue="") String name ,
			@RequestParam(value="email",defaultValue="") String email ,
			@RequestParam(value="status",required=false) MailAddressListStatus status,
			ModelMap modelMap) throws SQLException  {
			EntityReturnData ret = new EntityReturnData();
			if(status==null)status = MailAddressListStatus.NORMAL;
		
			ListPager paper = mailAddressListService.doPage(pageno, pagesize, name,email,status);
			ret.setMsg("执行成功");
			ret.setResult(true);
			ret.setEntity(paper);
			return ret;
	}
	@RequestMapping(value="/doadd",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doAdd( HttpServletRequest request,
			HttpServletResponse response,
			MailAddressList mailAddressList,
			ModelMap modelMap) throws SQLException, BindException  {
			EntityReturnData ret = new EntityReturnData();
			
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			mailAddressList = mailAddressListService.doAddMailAddressList(mailAddressList, adminUser);
			ret.setEntity(mailAddressList);
			ret.setMsg("执行成功");
			ret.setResult(true);
			return ret;
	}
	@RequestMapping(value="/doupdate",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doupdate( HttpServletRequest request,
			HttpServletResponse response,
			MailAddressList mailAddressList,
			ModelMap modelMap) throws SQLException, BindException  {
			EntityReturnData ret = new EntityReturnData();
			
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			mailAddressList = mailAddressListService.doUpdateMailAddressList(mailAddressList, adminUser);
			ret.setEntity(mailAddressList);
			ret.setMsg("执行成功");
			ret.setResult(true);
			return ret;
	}
	@RequestMapping(value="/dodel",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData dodel( HttpServletRequest request,
			HttpServletResponse response,
			int id,
			ModelMap modelMap) throws SQLException, BindException  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			 mailAddressListService.doDelMailAddressList(id, adminUser);
			 ret.setResult(true);
			 
			return ret;
	}
	
	@RequestMapping(value="/import",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doimport( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="excel") MultipartFile excel,   
			ModelMap modelMap) throws SQLException, BindException, BusinessException, IOException  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			 mailAddressListService.doImport(excel, adminUser);
			 ret.setResult(true);
			 
			return ret;
	}

}
