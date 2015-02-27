package com.gxkj.taobaoservice.controllers.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.dto.SessionConstant;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.PointCard;
import com.gxkj.taobaoservice.services.PointCardService;

@Controller
@RequestMapping("/admin/products/point_card")
public class PPointCardController {
	
	@Autowired
	private PointCardService pointCardService;
	

	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "admin/products/admin_pointcards";
		return mv;
	}
	
	 @RequestMapping(value="/dopage",method={RequestMethod.GET})
	 @ResponseBody
    public EntityReturnData doPage( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="realname",defaultValue="") String realname,
			@RequestParam(value="status",defaultValue="0")  int status,
			@RequestParam(value="pageno",defaultValue="0") int pageno,
    		@RequestParam(value="limit",defaultValue="10000") int pagesize
    		,ModelMap modelMap)  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
			try {
				ListPager pager = new ListPager();
				pager.setPageNo(pageno);
				pager.setRowsPerPage(pagesize );
				List<PointCard> cards = pointCardService.getAllEnablePointCard();
				pager.setPageData(cards);
				 
				ret.setEntity(pager);
			} catch (Exception e) {
				e.printStackTrace();
				ret.setMsg("失败");
				ret.setResult(false);
			}
			return ret;
	}
	 
	@RequestMapping(value="/doadd",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doAdd( HttpServletRequest request, HttpServletResponse response,
			PointCard entity,  
			ModelMap modelMap)  {
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setResult(true);
		try {
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			pointCardService.doAddPointCard(adminUser,entity);
			ret.setEntity(entity);
		} catch (Exception e) {
			e.printStackTrace();
			ret.setMsg("失败");
			ret.setResult(false);
		}
		return ret;
	}
	@RequestMapping(value="/doupdate",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doUpdate( HttpServletRequest request, HttpServletResponse response,
			PointCard entity,  
			ModelMap modelMap)  {
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setResult(true);
		try {
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			pointCardService.doUpdatePointCard(adminUser,entity);
			ret.setEntity(entity);
		} catch (Exception e) {
			e.printStackTrace();
			ret.setMsg("失败");
			ret.setResult(false);
		}
		return ret;
	}
	@RequestMapping(value="/dodel",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doDel( HttpServletRequest request, HttpServletResponse response,
			Integer cardId,  
			ModelMap modelMap)  {
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setResult(true);
		try {
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			pointCardService.doDelPointCard(adminUser,cardId);
			 
		} catch (Exception e) {
			e.printStackTrace();
			ret.setMsg("失败");
			ret.setResult(false);
		}
		return ret;
	}
}
