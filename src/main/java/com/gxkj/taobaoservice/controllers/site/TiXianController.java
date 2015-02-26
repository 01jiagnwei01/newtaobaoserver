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
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.RandomValidateCode;
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.TiXianService;
/**
 * 提现
 *
 */
@Controller
@RequestMapping("/site/money/tixian")
public class TiXianController {
	
	@Autowired
	private TiXianService tiXianService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String chongzhiP1(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "site/money/tixian";
		return mv;	
	}
	/**
	 * 提现申请
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param alipyAccount 支付宝账户
	 * @param amount		提现金额
	 * @param yanzhengma	验证码
	 * @param caozuoma		操作码
	 * @return
	 * @throws SQLException 
	 * @throws BusinessException 
	 */
	@RequestMapping(value="/doapply",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doapply(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			String alipyAccount,BigDecimal amount,String yanzhengma,String caozuoma) throws BusinessException, SQLException{
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		String yanzhengmaInsession = RandomValidateCode.getRandcode(request); 
		userBase = tiXianService.doapply(userBase, alipyAccount, amount, yanzhengma, yanzhengmaInsession, caozuoma);
		EntityReturnData ret = new EntityReturnData();
		ret.setResult(true);
		ret.setEntity(userBase);
		return ret;	
	}

}
