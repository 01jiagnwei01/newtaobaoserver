package com.gxkj.taobaoservice.controllers.site;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.dto.RegObjDTO;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.RegService;
import com.gxkj.taobaoservice.services.UserBaseService;
import com.gxkj.taobaoservice.util.RegexUtils;

@Controller
@RequestMapping("/reg")
public class RegController {
	
	@Autowired
	private UserBaseService userBaseService;

	@Autowired
	private RegService regService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String reg(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "site/reg";
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		if(userBase != null) {
			return "forward:/useraccount";  
		}
		return mv;	
	}
	
	@RequestMapping(value="/doreg",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doreg(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,RegObjDTO regObjDTO) throws BusinessException, SQLException, BindException{
		 
		 EntityReturnData ret = new EntityReturnData();
		 regService.doRegFn(regObjDTO); 
		 ret.setResult(true);
		 ret.setMsg("注册成功");
 		 return ret;	
	}
	@RequestMapping(value="/sendmail",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doSendRegCode(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,String tomail) throws BusinessException{
		EntityReturnData ret = new EntityReturnData();
		try{
			if(RegexUtils.isEmail( tomail)){
				regService.doSendMail(tomail );
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
			}else{
				ret.setResult(false);
				ret.setMsg(e.getMessage());//{Invalid Addresses}
			}
			e.printStackTrace();
		}
		
		return ret;
		
	}
		
}
