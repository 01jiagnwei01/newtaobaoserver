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
import com.gxkj.taobaoservice.entitys.DepositAppLog;
import com.gxkj.taobaoservice.enums.RechargeApplyStatus;
import com.gxkj.taobaoservice.services.DepositApplyService;

/**
 * 
 * 存款管理
 */
@Controller
@RequestMapping("/admin/deposit")
public class DDepositController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
 
    }
	
	@Autowired
	public DepositApplyService rechargeApplyService;

	
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "admin/depositapply";
		return mv;
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @param thirdOrderNo
	 * @param amount
	 * @param userId
	 * @param status
	 * @param createBeginTime
	 * @param createEndTime
	 * @param reviewBeginTime
	 * @param reviewEndTime
	 * @param auditorId
	 * @param pageno
	 * @param pagesize
	 * @param modelMap
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping(value="/dopage",method={RequestMethod.POST})
	@ResponseBody
    public EntityReturnData doPage( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="thirdOrderNo",defaultValue="") String thirdOrderNo,
			@RequestParam(value="amount",defaultValue="0")  BigDecimal amount,
			@RequestParam(value="userId",defaultValue="0")  Integer userId,
			@RequestParam(value="status",defaultValue="")  RechargeApplyStatus status,
			@RequestParam(value="createBeginTime" ,required=false)  Date createBeginTime,
			@RequestParam(value="createEndTime" ,required=false)  Date createEndTime, 
			@RequestParam(value="reviewBeginTime" ,required=false)  Date reviewBeginTime,
			@RequestParam(value="reviewEndTime",required=false )  Date reviewEndTime,
			@RequestParam(value="auditorId",defaultValue="0")  Integer auditorId,
			@RequestParam(value="pageno",defaultValue="0") int pageno,
    		@RequestParam(value="limit",defaultValue="20") int pagesize
    		,ModelMap modelMap) throws SQLException  {
			EntityReturnData ret = new EntityReturnData();
			ret.setResult(true);
		
			ListPager paper = rechargeApplyService.doPage(pageno, pagesize, thirdOrderNo, amount, userId, status, createBeginTime, createEndTime, reviewBeginTime, reviewEndTime, auditorId);
			ret.setEntity(paper);
			
			return ret;
	}
	
	/**
	 * 审核通过
	 * @param request
	 * @param response
	 * @param applyId
	 * @param modelMap
	 * @return
	 * @throws BusinessException 
	 * @throws SQLException 
	 */
	@RequestMapping(value="/doagree",method={RequestMethod.POST})
	@ResponseBody
    public EntityReturnData doagree( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="applyId",defaultValue="0")  Integer applyId
    		,ModelMap modelMap) throws SQLException, BusinessException  {
			EntityReturnData ret = new EntityReturnData();
			ret.setResult(true);
			 
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			DepositAppLog entity =rechargeApplyService.doAgreeRechargeApply(applyId, adminUser);
			ret.setEntity(entity);
			 
			return ret;
	}
	/**
	 *  审核拒绝
	 * @param request
	 * @param response
	 * @param applyId
	 * @param reason
	 * @param modelMap
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping(value="/doarefuse",method={RequestMethod.POST})
	@ResponseBody
    public EntityReturnData doarefuse( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="applyId",defaultValue="0")  Integer applyId,
			@RequestParam(value="reason",defaultValue="")  String reason
    		,ModelMap modelMap) throws SQLException  {
			EntityReturnData ret = new EntityReturnData();
			ret.setResult(true);
			 
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			DepositAppLog entity =rechargeApplyService.doRefuseRechargeApply(applyId, adminUser, reason);
			ret.setEntity(entity);
			 
			return ret;
	}
}
