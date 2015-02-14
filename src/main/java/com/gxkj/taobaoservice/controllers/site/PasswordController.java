package com.gxkj.taobaoservice.controllers.site;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.PasswordService;
import com.gxkj.taobaoservice.util.RegexUtils;

@Controller
@RequestMapping("/site/user/password")
public class PasswordController {
	
	@Autowired
	private PasswordService passwordService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String password_email(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "site/user/password-email";
		return mv;	
	}
	@RequestMapping(value="/phone",method=RequestMethod.GET)
	public String password_phone(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "site/user/password-phone";
		return mv;	
	}
	@RequestMapping(value="/sendmail",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doSendRegCode(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,String tomail) throws BusinessException{
		EntityReturnData ret = new EntityReturnData();
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		try{
			if(RegexUtils.isEmail( tomail)){
				passwordService.doSendMail(userBase, tomail );
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
}
