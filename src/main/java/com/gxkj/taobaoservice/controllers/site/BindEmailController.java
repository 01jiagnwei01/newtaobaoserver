package com.gxkj.taobaoservice.controllers.site;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.EmailService;
import com.gxkj.taobaoservice.util.RegexUtils;

@Controller
@RequestMapping("/site/bind/email")
public class BindEmailController {
 
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String password_phone(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "site/user/bind_email";
		return mv;	
	}
	
	@RequestMapping(value="/sendmail",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doSendRegCode(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,String tomail) throws BusinessException{
		EntityReturnData ret = new EntityReturnData();
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		try{
			if(RegexUtils.isEmail( tomail)){
				emailService.doSendMailCode(userBase, tomail );
				ret.setResult(true);
				ret.setEntity("");
			}else{
				ret.setResult(false);
				ret.setMsg("emailNoValid");//{Invalid Addresses}
			}
		
		}catch(Exception e){
			if(e instanceof javax.mail.SendFailedException){
				ret.setResult(false);
				ret.setMsg(e.getMessage());//{Invalid Addresses}
			}else if(e instanceof com.sun.mail.smtp.SMTPAddressFailedException){
				ret.setResult(false);
				ret.setMsg(e.getMessage());//{Invalid Addresses}
			}else if(e instanceof BusinessException){
				 throw (BusinessException)e;
			}else{
				ret.setResult(false);
				ret.setMsg(e.getMessage());//{Invalid Addresses}
			}
			e.printStackTrace();
		}
		return ret;
	}
	
	@RequestMapping(value="/doupdatebyemail",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doupdatebyemail(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			String email,String caozuoma,String yanzhengma) throws BusinessException, SQLException{
		EntityReturnData ret = new EntityReturnData();
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		emailService.doUpdateByEmail(userBase,email,caozuoma,yanzhengma);
		ret.setResult(true);
		ret.setEntity("");
		return ret;	
	}
}
