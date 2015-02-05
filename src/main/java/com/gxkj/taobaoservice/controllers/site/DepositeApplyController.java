package com.gxkj.taobaoservice.controllers.site;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.DepositApply;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.RechargeApplyResults;
import com.gxkj.taobaoservice.services.DepositApplyService;
/**
 * 
 * 充值申请
 *
 */
@Controller
@RequestMapping("/depositapply")
public class DepositeApplyController {
	
	@Autowired
	public DepositApplyService depositApplyService;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public EntityReturnData addRechargeApplyService(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			@RequestParam(value="thirdOrderNo") String thirdOrderNo,@RequestParam(value="amount") BigDecimal amount) throws SQLException{
		EntityReturnData ret = new EntityReturnData(); 
		if(StringUtils.isBlank(thirdOrderNo)){
			ret.setMsg(RechargeApplyResults.THIRD_ORDER_No_IS_BLANK.getName());
			return ret;
		}
		if(amount == null || amount.intValue() == 0){
			ret.setMsg(RechargeApplyResults.AMOUNT_IS_BLANK.getName());
			return ret;
		}
		UserBase userBase  = SessionUtil.getSiteUserInSession(request);
		DepositApply apply = depositApplyService.addRechargeApply(thirdOrderNo, amount, userBase);
		ret.setResult(true);
		ret.setEntity(apply);
		return ret;	
	}

}
