package com.gxkj.taobaoservice.controllers.site;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.ChongZhiService;
import com.gxkj.taobaoservice.services.UserAccountService;

@Controller
@RequestMapping("/site/money/chongzhi")
public class MoneyChongZhiController {

	@Autowired
	private ChongZhiService chongZhiService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String chongzhiP1(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws SQLException{
		String mv = "site/money/chongzhi";
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		UserAccount userAccount =userAccountService.getUserAccountByUserBaseId(userBase.getId());
		modelMap.put("userAccount", userAccount);
		return mv;	
	}
	@RequestMapping(value="/s2",method=RequestMethod.GET)
	public String chongzhiP2(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws SQLException{
		String mv = "site/money/chongzhis2";
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		UserAccount userAccount =userAccountService.getUserAccountByUserBaseId(userBase.getId());
		modelMap.put("userAccount", userAccount);
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

	/**
	 * 分页显示充值记录
	 * @param request
	 * @param response
	 * @param realname
	 * @param status
	 * @param pageno
	 * @param pagesize
	 * @param modelMap
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping(value="/topage",method={RequestMethod.GET})
   public String doPage( HttpServletRequest request,
			HttpServletResponse response,
		@RequestParam(value="starttime",defaultValue="") String starttime,
		@RequestParam(value="endtime",defaultValue="") String endtime, 
		@RequestParam(value="pageno",defaultValue="0") int pageno,
   		@RequestParam(value="limit",defaultValue="20") int pagesize
   		,ModelMap modelMap) throws SQLException  {
			 
			Date startTime = null;
			Date endTime = null;
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			UserBase userBase = SessionUtil.getSiteUserInSession(request);
			
			try{
				if(StringUtils.isNotBlank(starttime)){
					starttime += " 00:00:00";
					startTime = formatter.parse(starttime);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				 
				if(StringUtils.isNotBlank(endtime)){
					endtime += " 23:59:59";
					endTime = formatter.parse(endtime);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			ListPager paper = chongZhiService.doPage(userBase,pageno, pagesize, startTime, endTime);
			modelMap.put("starttime", starttime);
			modelMap.put("endtime", endtime);
			modelMap.put("pageno", pageno);
			modelMap.put("pagesize", pagesize);
			modelMap.put("paper", paper);
			String mv = "site/money/chongzhi_page";
			return mv;	
	}
}
