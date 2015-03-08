package com.gxkj.taobaoservice.controllers.site;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.RandomValidateCode;
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.BindQQService;

@Controller
@RequestMapping("/site/bind/qq")
public class BindQQController {
 
	
	@Autowired
	private BindQQService bindQQService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String bindQq(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "site/user/bind_qq";
		return mv;	
	}
	
	
	@RequestMapping(value="/dobindqq",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData dobindqq(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			 String caozuoma,String yanzhengma,String newQQ) throws BusinessException, SQLException{
		EntityReturnData ret = new EntityReturnData();
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		String yanzhengMaInSession = RandomValidateCode.getRandcode(request); 
		 bindQQService.doBindQQ(userBase,newQQ,caozuoma,yanzhengma,yanzhengMaInSession);
		userBase.setBindQq(newQQ);
		
		 //将修改后的用户信息放到session里 
		 SessionUtil.setSiteUser2Session(request, userBase);
		 
		ret.setResult(true);
		ret.setEntity("");
		return ret;	
	}
}
