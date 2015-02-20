package com.gxkj.taobaoservice.controllers.site;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.ApplyDrawLog;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.RechargeApplyResults;
import com.gxkj.taobaoservice.services.ApplyDrawService;

@Controller
@RequestMapping("/applydraw")
public class ApplyDrawController {
	
	@Autowired
	private ApplyDrawService applyDrawService;

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public EntityReturnData addRechargeApplyService(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			@RequestParam(value="amount") BigDecimal amount) throws SQLException{
		EntityReturnData ret = new EntityReturnData(); 
		
		if(amount == null || amount.intValue() == 0){
			ret.setMsg(RechargeApplyResults.AMOUNT_IS_BLANK.getName());
			return ret;
		}
		UserBase userBase  = SessionUtil.getSiteUserInSession(request);
		ApplyDrawLog apply = applyDrawService.addApplyDraw(amount, userBase);
		ret.setResult(true);
		ret.setEntity(apply);
		return ret;	
	}
}
