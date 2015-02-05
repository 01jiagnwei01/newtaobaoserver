package com.gxkj.taobaoservice.controllers.site;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
		return mv;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public EntityReturnData dologin(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("yanzhengma") String yanzhengma) throws BusinessException, SQLException {
 
		EntityReturnData ret = new EntityReturnData();
		if(StringUtils.isBlank(username)){
			ret.setMsg(LoginProcessResults.USER_NAME_BLANK_FAILURE.getName());
		}else if(StringUtils.isBlank(password)){
			ret.setMsg(LoginProcessResults.PASSWORD_BLANK_FAILURE.getName());
		}else if(StringUtils.isBlank(yanzhengma)){
			ret.setMsg(LoginProcessResults.YANGZHENGMA_BLANK_FAILURE.getName());
		}else{
			String yanzhengMaInSession = (String) request.getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY); 
			if(!yanzhengMaInSession.equalsIgnoreCase(yanzhengma)){
				ret.setMsg(LoginProcessResults.YANGZHENGMA_ERROR.getName());
			}else{
				 UserBase userBase =	userBaseService.doLogin(username,password);
				 if(userBase == null){
					 ret.setMsg(LoginProcessResults.USER_NAME_OR_PASSWORD_ERROR_FAILURE.getName());
				 }else{
					 ret.setResult(true);
					 ret.setMsg(LoginProcessResults.SUCCESS.getName());
					 ret.setEntity(userBase);
					 //将用户信息放到session里 
					 SessionUtil.setSiteUser2Session(request, userBase);
				 }
			}
			
			
		}
		
		return ret;
	}
}
