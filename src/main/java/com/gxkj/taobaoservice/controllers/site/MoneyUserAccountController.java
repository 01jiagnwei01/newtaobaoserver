package com.gxkj.taobaoservice.controllers.site;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.UserAccountService;

@Controller
@RequestMapping("/site/money/useraccount")
public class MoneyUserAccountController {
	
	@Autowired
	private UserAccountService userAccountService;
	
	@RequestMapping(value="/log",method=RequestMethod.GET)
	public String chongzhiP1(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			@RequestParam(value="starttime",defaultValue="") String starttime,
			@RequestParam(value="endtime",defaultValue="") String endtime, 
			@RequestParam(value="pageno",defaultValue="0") int pageno,
	   		@RequestParam(value="limit",defaultValue="20") int pagesize) throws BusinessException, SQLException{
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
		ListPager paper = userAccountService.doPage(userBase,pageno, pagesize, startTime, endTime);
		modelMap.put("starttime", starttime);
		modelMap.put("endtime", endtime);
		modelMap.put("pageno", pageno);
		modelMap.put("pagesize", pagesize);
		modelMap.put("paper", paper);
		String mv = "site/money/useraccount_log";
		return mv;	
	}

}
