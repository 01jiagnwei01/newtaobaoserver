package com.gxkj.taobaoservice.controllers.site;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.entitys.UserLink;
import com.gxkj.taobaoservice.enums.UserLinkTypes;
import com.gxkj.taobaoservice.services.UserLinkService;

@Controller
@RequestMapping("/ulink")
public class UserLinkController {

	@Autowired
	private UserLinkService userLinkService;

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doreg(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,
			@RequestParam(value="userLinkType",required=true) UserLinkTypes userLinkType,
			@RequestParam(value="value",required=true) String value) throws SQLException, BusinessException {
		EntityReturnData ret = new EntityReturnData();
		
		UserBase userBase  = SessionUtil.getSiteUserInSession(request);
		UserLink link = userLinkService.updateUserLink(userBase,userLinkType,value);
		ret.setEntity(link);
		ret.setResult(true);
		ret.setMsg("SUCCESS");
		return ret;
	}

}
