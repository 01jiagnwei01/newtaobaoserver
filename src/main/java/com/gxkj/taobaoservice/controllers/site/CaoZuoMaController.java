package com.gxkj.taobaoservice.controllers.site;

import java.sql.SQLException;

import javax.mail.MessagingException;
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
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.CaoZuoMaService;

@Controller
@RequestMapping("/site/bind/caozuoma")
public class CaoZuoMaController {
	@Autowired
	private CaoZuoMaService caoZuoMaService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String password_phone(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "site/user/bind_caozuoma";
		return mv;	
	}
	
	@RequestMapping(value="/sendmail",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doSendRegCode(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws BusinessException, SQLException, BindException, MessagingException{
		EntityReturnData ret = new EntityReturnData();
		UserBase base = SessionUtil.getSiteUserInSession(request);
		caoZuoMaService.doSendMail(base); 
		ret.setResult(true);
		return ret;
	}
	/**
	 * 向手机发验证码
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws BusinessException
	 * @throws SQLException
	 * @throws BindException
	 * @throws MessagingException
	 */
	@RequestMapping(value="/sendphone",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData sendphone(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws BusinessException, SQLException, BindException, MessagingException{
		EntityReturnData ret = new EntityReturnData();
		UserBase base = SessionUtil.getSiteUserInSession(request);
		caoZuoMaService.doSendPhone(base); 
		ret.setResult(true);
		return ret;
	}
	/**
	 * 使用邮箱修改验证码
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws BusinessException
	 * @throws SQLException
	 * @throws BindException
	 * @throws MessagingException
	 */
	@RequestMapping(value="/mailsubmit",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doMailsubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap,
			String caozuoma,String recaozuoma,String code) throws BusinessException, SQLException, BindException, MessagingException{
		EntityReturnData ret = new EntityReturnData();
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		String newCaoZuoMa = caoZuoMaService.doMailSubmitCaoZuoMa(userBase,caozuoma,recaozuoma,code);
		userBase.setCaoZuoMa(newCaoZuoMa);
		SessionUtil.setSiteUser2Session(request, userBase);
		ret.setResult(true);
		return ret;
	}
	@RequestMapping(value="/caozuomasubmit",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doCaoZuoMasubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap,
			String caozuoma,String recaozuoma,String oldcode) throws BusinessException, SQLException, BindException, MessagingException{
		EntityReturnData ret = new EntityReturnData();
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		String newCaoZuoMa = caoZuoMaService.doCaoZuoMaSubmitCaoZuoMa(userBase,caozuoma,recaozuoma,oldcode);
		userBase.setCaoZuoMa(newCaoZuoMa);
		SessionUtil.setSiteUser2Session(request, userBase);
		ret.setResult(true);
		return ret;
	}
	
	@RequestMapping(value="/phonesubmit",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData phonesubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap,
			String caozuoma,String recaozuoma,String code) throws BusinessException, SQLException, BindException, MessagingException{
		EntityReturnData ret = new EntityReturnData();
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		String newCaoZuoMa = caoZuoMaService.doPhoneSubmitCaoZuoMa(userBase,caozuoma,recaozuoma,code);
		userBase.setCaoZuoMa(newCaoZuoMa);
		SessionUtil.setSiteUser2Session(request, userBase);
		ret.setResult(true);
		return ret;
	}
}
