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
import com.gxkj.taobaoservice.services.BindAlipayService;

@Controller
@RequestMapping("/site/bind/alipay")
public class BindAlipayController {
 
	
	@Autowired
	private BindAlipayService bindAlipayService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String bindAlipay(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "site/user/bind_alipay";
		return mv;	
	}
	
	
	@RequestMapping(value="/dobindalipay",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData dobindalipay(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			String caozuoma,String yanzhengma,String alipay) throws BusinessException, SQLException{
		EntityReturnData ret = new EntityReturnData();
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		String yanzhengMaInSession = RandomValidateCode.getRandcode(request); 
		bindAlipayService.doBindAlipay(userBase,alipay,caozuoma,yanzhengma,yanzhengMaInSession);
		userBase.setBindAlipay(alipay);
		
		 //将修改后的用户信息放到session里 
		 SessionUtil.setSiteUser2Session(request, userBase);
		 
		ret.setResult(true);
		ret.setEntity("");
		return ret;	
	}
}
