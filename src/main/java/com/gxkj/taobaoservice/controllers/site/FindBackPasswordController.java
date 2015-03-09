package com.gxkj.taobaoservice.controllers.site;

import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.RandomValidateCode;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.services.EmailService;
import com.gxkj.taobaoservice.services.UserLinkService;

@Controller
@RequestMapping("/findbackpassword")
public class FindBackPasswordController {
	
	@Autowired
	private UserLinkService userLinkService;
	
	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String findbackpassword_GET(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		String mv = "site/findbackpassword";
		return mv;
	}
	
	@RequestMapping(value = "/sendmail", method = RequestMethod.POST)
	@ResponseBody
	public EntityReturnData sendmail(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,String email) throws BusinessException, SQLException, MessagingException, BindException {
		EntityReturnData ret = new EntityReturnData();
		emailService.doFindBackPasswordMailCode(email );
		ret.setResult(true);
		ret.setEntity("");
		return ret;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public EntityReturnData findbackpassword_POST(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("email") String email,@RequestParam("emailCode") String emailCode,
			@RequestParam("yanzhengma") String yanzhengma,@RequestParam("password") String password,@RequestParam("surepassword") String surePassword ) throws SQLException, BusinessException {
		EntityReturnData ret = new EntityReturnData();
 
		String yanzhengMaInSession = RandomValidateCode.getRandcode(request);
		userLinkService.doFindBackPassword(email,emailCode,yanzhengma,yanzhengMaInSession,password,surePassword);
		
		ret.setResult(true);
		ret.setEntity("");
		return ret;
	}
}
