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
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.TelService;

@Controller
@RequestMapping("/site/bind/tel")
public class BindTelController {
	
	@Autowired
	private TelService telService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String password_phone(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "site/user/bind_tel";
		return mv;	
	}
	
	@RequestMapping(value="/sendcode",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doSendRegCode(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,String telNo) throws BusinessException, SQLException{
		EntityReturnData ret = new EntityReturnData();
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		telService.doSendTelCodeForBind(userBase, telNo);
		ret.setResult(true);	 
		return ret;
	}
	
	@RequestMapping(value="/doupdatebyetel",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doupdatebyemail(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			String telNo,String caozuoma,String yanzhengma) throws BusinessException, SQLException{
		EntityReturnData ret = new EntityReturnData();
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		telService.doUpdateByPhone(userBase,telNo,caozuoma,yanzhengma);
		ret.setResult(true);
		ret.setEntity("");
		return ret;	
	}

}
