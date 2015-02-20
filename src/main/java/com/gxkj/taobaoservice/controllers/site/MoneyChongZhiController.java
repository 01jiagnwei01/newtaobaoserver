package com.gxkj.taobaoservice.controllers.site;

import java.math.BigDecimal;
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
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.ChongZhiService;

@Controller
@RequestMapping("/site/money/chongzhi")
public class MoneyChongZhiController {

	@Autowired
	private ChongZhiService chongZhiService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String chongzhiP1(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "site/money/chongzhi";
		return mv;	
	}
	@RequestMapping(value="/s2",method=RequestMethod.GET)
	public String chongzhiP2(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "site/money/chongzhis2";
		return mv;	
	}
	@RequestMapping(value="/s3",method=RequestMethod.GET)
	public String chongzhiP23(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "site/money/chongzhis3";
		return mv;	
	}
	@RequestMapping(value="/dochongzhi",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData dochongzhi(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			BigDecimal amount,String orderno) throws BusinessException, SQLException, BindException{
		EntityReturnData ret = new EntityReturnData();
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		/**
		 * 
		 */
		chongZhiService.doChongZhiForNoInstant(userBase, amount, orderno);
		ret.setResult(true);
		return ret;	
	}
}
