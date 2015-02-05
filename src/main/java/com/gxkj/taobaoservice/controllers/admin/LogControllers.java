package com.gxkj.taobaoservice.controllers.admin;

import groovy.json.JsonOutput;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.enums.UserAccountTypes;
import com.gxkj.taobaoservice.services.UserAccountLogService;

@Controller
@RequestMapping("/admin/log")
public class LogControllers {
	@Autowired
	private UserAccountLogService userAccountLogService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	@RequestMapping(value="/{type}",method={RequestMethod.GET})
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			@PathVariable(value="type") UserAccountTypes userAccountType) throws Exception{
		if(userAccountType == UserAccountTypes.COMPANY_SUPPLY){
			String mv = "admin/log/log_supply_point";
			return mv;
		}else{
			throw new Exception("暂不支持");
		}
		
	}
	
	@RequestMapping(value="/dopage/{type}",method={RequestMethod.POST})
	 @ResponseBody
  public EntityReturnData doPage( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="pageno",defaultValue="0") int pageno,
  		@RequestParam(value="limit",defaultValue="20") int pagesize,
		@RequestParam(value="userID",required =false ) Integer userID,
		@RequestParam(value="adminId" ,required =false)  Integer adminId,
		@PathVariable(value="type" ) UserAccountTypes userAccountType, 
		@RequestParam(value="beginTime" ,required=false)  Date beginTime,
		@RequestParam(value="endTime" ,required=false)  Date endTime,
  		 ModelMap modelMap)  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
			try {
				ListPager paper = userAccountLogService.doPage(pageno, pagesize, userID, adminId, userAccountType, beginTime,endTime);
				System.out.println(JsonOutput.toJson(paper));
				ret.setEntity(paper);
			} catch (Exception e) {
				e.printStackTrace();
				ret.setMsg("失败");
				ret.setResult(false);
			}
			return ret;
	}
}
