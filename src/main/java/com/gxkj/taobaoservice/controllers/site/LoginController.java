package com.gxkj.taobaoservice.controllers.site;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.RandomValidateCode;
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.LoginProcessResults;
import com.gxkj.taobaoservice.services.UserBaseService;

@Controller(value = "siteLogin")
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserBaseService userBaseService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String login(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		
		String mv = "site/login";
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		if(userBase != null) {
			return "forward:/useraccount";  
		}
		return mv;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public EntityReturnData dologin(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("yanzhengma") String yanzhengma) throws BusinessException, SQLException {
 
		EntityReturnData ret = new EntityReturnData();
		String yanzhengMaInSession = RandomValidateCode.getRandcode(request); 
		ret.setResult(true);
		ret.setMsg(LoginProcessResults.SUCCESS.getName());
		UserBase userBase =	userBaseService.doLogin(username,password,yanzhengma, yanzhengMaInSession);
		ret.setEntity(userBase);
		 //将用户信息放到session里 
		 SessionUtil.setSiteUser2Session(request, userBase);
		return ret;
	}
}
