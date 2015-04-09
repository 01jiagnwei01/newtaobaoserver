package com.gxkj.taobaoservice.controllers.site;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.gxkj.taobaoservice.services.UserBaseService;

@Controller
@RequestMapping("/site/bind/password")
public class BindPasswordController {
	protected final  Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired
	private UserBaseService userBaseService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String password_phone(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		logger.info("设置密码");
		String mv = "site/user/bind_password";
		return mv;	
	}
	@RequestMapping(value="/doupdatepassword",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doupdatebyemail(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			String password,String repassword,String caozuoma) throws BusinessException, SQLException{
		EntityReturnData ret = new EntityReturnData();
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		/**
		 * 修改用户密码
		 */
		userBaseService.doUpdatePasswordBy(userBase,password,repassword,caozuoma);
		ret.setResult(true);
		ret.setEntity("");
		return ret;	
	}
}
