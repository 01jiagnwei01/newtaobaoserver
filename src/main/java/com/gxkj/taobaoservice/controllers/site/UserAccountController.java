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
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.UserAccountService;

@Controller
@RequestMapping("/site/useraccount")
public class UserAccountController {

	@Autowired
	private UserAccountService userAccountService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String order_create_get(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		String mv = null;
		if(StringUtils.isBlank(userBase.getCaoZuoMa())){
			mv = "forward:/site/bind/caozuoma";
		}else{
			mv = "forward:/site/task/list";
		}
		
		return mv;	
	}
	@RequestMapping(value="get",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData get_UserAccount(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws  SQLException{
		EntityReturnData ret = new EntityReturnData();
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		UserAccount userAccount = userAccountService.getUserAccountByUserBaseId(userBase.getId());
		ret.setResult(true);
		ret.setEntity(userAccount);
		return ret;		
	}
}
