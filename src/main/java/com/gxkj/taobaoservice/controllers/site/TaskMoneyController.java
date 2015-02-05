package com.gxkj.taobaoservice.controllers.site;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.UserBaseService;

/**
 * 任务币兑换
 *
 */
@RequestMapping("/taskmoney")
public class TaskMoneyController {

	@Autowired
	private UserBaseService userBaseService;
	/**
	 * 兑换任务币
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param amount	兑换多少  1:1的兑换
	 * @return
	 * @throws SQLException
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/exchange", method = RequestMethod.POST)
	public EntityReturnData exchange(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, BigDecimal amount)
			throws SQLException, BusinessException {
		
		UserBase userBase  = SessionUtil.getSiteUserInSession(request);
		EntityReturnData ret = userBaseService.exchangePublishMoney(userBase,amount);
		if(ret.isResult()){
			//修改Session中的值
			UserAccount  userAccount = userBase.getUerAccount();
			userAccount.setCurrentBalance(userAccount.getCurrentBalance().subtract(amount));
			userAccount.setCurrentRestPoints(userAccount.getCurrentRestPoints().add(amount));
			userBase.setUerAccount(userAccount);
			SessionUtil.setSiteUser2Session(request, userBase);
			ret.setEntity(userBase);
		}
		return ret;
	}
}
