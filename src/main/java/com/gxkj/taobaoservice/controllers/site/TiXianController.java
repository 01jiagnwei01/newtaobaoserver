package com.gxkj.taobaoservice.controllers.site;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.common.util.RandomValidateCode;
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.TiXianService;
import com.gxkj.taobaoservice.services.UserAccountService;
/**
 * 提现
 *
 */
@Controller
@RequestMapping("/site/money/tixian")
public class TiXianController {
	
	@Autowired
	private TiXianService tiXianService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String chongzhiP1(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws SQLException{
		String mv = "site/money/tixian";
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		UserAccount userAccount =userAccountService.getUserAccountByUserBaseId(userBase.getId());
		modelMap.put("userAccount", userAccount);
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
	
	@RequestMapping(value="/recordpage",method={RequestMethod.GET})
	   public String doPage( HttpServletRequest request,
				HttpServletResponse response,
			@RequestParam(value="starttime",defaultValue="") String starttime,
			@RequestParam(value="endtime",defaultValue="") String endtime, 
			@RequestParam(value="pageno",defaultValue="1") int pageno,
	   		@RequestParam(value="limit",defaultValue="20") int pagesize
	   		,ModelMap modelMap) throws SQLException, BusinessException  {
				 
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
				ListPager paper = tiXianService.doPage(userBase,pageno, pagesize, startTime, endTime);
				modelMap.put("starttime", starttime);
				modelMap.put("endtime", endtime);
				modelMap.put("pageno", pageno);
				modelMap.put("pagesize", pagesize);
				modelMap.put("paper", paper);
				String mv = "site/money/tixian_recordpage";
				
				UserAccount userAccount =userAccountService.getUserAccountByUserBaseId(userBase.getId());
				modelMap.put("userAccount", userAccount);
				return mv;	
		}

}
