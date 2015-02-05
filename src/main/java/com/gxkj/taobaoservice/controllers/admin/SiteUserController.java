package com.gxkj.taobaoservice.controllers.admin;

import java.math.BigDecimal;
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

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.dto.SessionConstant;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.enums.UserBaseStatus;
import com.gxkj.taobaoservice.enums.YANS;
import com.gxkj.taobaoservice.services.UserBaseService;

@Controller
@RequestMapping("/admin/siteuser")
public class SiteUserController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
 
    }
	
	@Autowired
	private UserBaseService userBaseService;
	
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "admin/siteuser";
		return mv;
	}
	
	@RequestMapping(value="/dopage",method={RequestMethod.POST})
	 @ResponseBody
   public EntityReturnData doPage( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="pageno",defaultValue="0") int pageno,
   		@RequestParam(value="limit",defaultValue="20") int pagesize,
		@RequestParam(value="name",defaultValue="") String name,
		@RequestParam(value="status",defaultValue="")  UserBaseStatus status,
		@RequestParam(value="regBeignTime" ,required=false)  Date regBeignTime, 
		@RequestParam(value="regEndTime" ,required=false)  Date regEndTime,
		@RequestParam(value="supplyMoneystatus",defaultValue="")  YANS supplyMoneystatus,
		@RequestParam(value="supplyMoney",defaultValue="0") BigDecimal supplyMoney,
   		 ModelMap modelMap)  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
			try {
				ListPager paper = userBaseService.doPage(pageno, pagesize, name, status, regBeignTime, regEndTime,supplyMoneystatus,supplyMoney);
				ret.setEntity(paper);
			} catch (Exception e) {
				e.printStackTrace();
				ret.setMsg("失败");
				ret.setResult(false);
			}
			return ret;
	}
	/**
	 * 设置公司补助金额
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param userId
	 * @param supplyMoney
	 * @return
	 * @throws SQLException 
	 * @throws BusinessException 
	 */
//	@RequestMapping(value="/supplyMoney",method={RequestMethod.POST})
//	 @ResponseBody
//	public EntityReturnData supplyMoney(HttpServletRequest request,
//			HttpServletResponse response, ModelMap modelMap,
//			@RequestParam(value="userId",required=true) Integer userId,
//			@RequestParam(value="supplyMoney",required=true) BigDecimal supplyMoney) throws SQLException, BusinessException{
//		AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
//		return userBaseService.doSetSupplyMoney(adminUser,userId,supplyMoney);
//	}
	/**
	 * 清空公司对所有已支持的公司的补助
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param userId
	 * @param supplyMoney
	 * @return
	 * @throws SQLException 
	 */
//	@RequestMapping(value="/clearSupplyMone",method={RequestMethod.POST})
//	 @ResponseBody
//	public EntityReturnData clearSupplyMoney(HttpServletRequest request,
//			HttpServletResponse response, ModelMap modelMap  ) throws SQLException{
//		AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
//		return userBaseService.doClearSupplyMone(adminUser);
//	}
	/**
	 * 设置支持某个用户多少点数
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param userId
	 * @param supplyMoney
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	@RequestMapping(value="/supplypoint",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData supplypoint(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam(value="userId",required=true) Integer userId,
			@RequestParam(value="supplyPoint",required=true) BigDecimal supplyPoint) throws SQLException, BusinessException{
		AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
		 userBaseService.doSupplypoint(adminUser,userId, supplyPoint);
		 EntityReturnData ret = new EntityReturnData();
		 ret.setResult(true);
		 return ret;
		 
	}

}
