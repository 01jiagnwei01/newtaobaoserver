package com.gxkj.taobaoservice.controllers.admin;

import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.services.CompanyAccountService;

@Controller
@RequestMapping("/admin/company_account")
public class AdminCompanyController {

	@Autowired
	private CompanyAccountService companyAccountService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
 
    }
	
	@RequestMapping(value="")
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws SQLException{
		String mv = "admin/company_account_get";
		 
		return mv;
	}
	 @RequestMapping(value="/dopage",method={RequestMethod.POST})
	 @ResponseBody
    public EntityReturnData doPage( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="beginTime" ,required=false)  Date beginTime,
			@RequestParam(value="endTime" ,required=false)  Date endTime,
			@RequestParam(value="pageno",defaultValue="0") int pageno,
    		@RequestParam(value="limit",defaultValue="20") int pagesize
    		,ModelMap modelMap)  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
			try {
				ListPager paper = companyAccountService.doPage(pageno, pagesize,beginTime ,endTime);
				ret.setEntity(paper);
			} catch (Exception e) {
				e.printStackTrace();
				ret.setMsg("失败");
				ret.setResult(false);
			}
			return ret;
	}
}
