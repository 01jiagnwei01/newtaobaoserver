package com.gxkj.taobaoservice.controllers.site;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.Encrypt;
import com.gxkj.taobaoservice.constants.EncryptKey;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.enums.FindBackPasswordProcessResult;
import com.gxkj.taobaoservice.services.UserLinkService;

@Controller
@RequestMapping("/findbackpassword")
public class FindBackPasswordController {
	
	@Autowired
	private UserLinkService userLinkService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String findbackpassword_GET(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("data") String data) {
		
		
		String mv = "site/findbackpassword";
		if(StringUtils.isNotBlank(data)){
			Encrypt encrypt = new Encrypt();
			encrypt.setKey(EncryptKey.encryptKeyString); 
			encrypt.setDesString(data); 
			String mingWen=encrypt.getStrM(); //调用get函数获取解密后明文。
			String[] args = mingWen.split("&");
			if(args.length != 2){
				modelMap.put("error", "您属于非法操作哦");
				mv = "error/active_error";
				return mv;
			}
			String sendtime = args[0].split("=")[1];
			String logid = args[1].split("=")[1];
			Date sendDate;
			try {
				sendDate = DateUtils.parseDateStrictly(sendtime, new String[]{"yyyy-MM-dd HH:mm:ss"});
				if( !DateUtils.isSameDay(sendDate, new Date())){
					modelMap.put("error", "已经过期了，无法进行操作");
					mv = "error/active_error";
					return mv;
				}else {
					//跳转到修改充值密码页面
					//undo
					modelMap.put("logid", logid);
				}
			} catch (ParseException e) {
				modelMap.put("error", "您属于非法操作哦");
				mv = "site/active_error";
				return mv;	
			}
			
		}
		
		
		return mv;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public EntityReturnData findbackpassword_POST(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam("email") String email,
			@RequestParam("yanzhengma") String yanzhengma) throws SQLException, BusinessException {
		EntityReturnData ret = new EntityReturnData();
		if(StringUtils.isBlank( email)){
			ret.setMsg(FindBackPasswordProcessResult.EMAIL_IS_BLANK.getName());
		}else if(StringUtils.isBlank( yanzhengma)){
			ret.setMsg(FindBackPasswordProcessResult.YANZHENGMA_IS_BLANK.getName());
		}else if(!yanzhengma.equals((String)request.getSession().getAttribute("yanzhengma"))){
			ret.setMsg(FindBackPasswordProcessResult.YANZHENGMA_IS_ERROR.getName());
		}else{
			try {
				ret = userLinkService.doFindBackUserPassword(email);
			} catch (MessagingException e) {
				ret.setMsg(FindBackPasswordProcessResult.SEND_FAILURE.getName());
				e.printStackTrace();
			}
		}
		
		
		return ret;
	}
}
