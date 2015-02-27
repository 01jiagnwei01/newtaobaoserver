package com.gxkj.taobaoservice.controllers.site;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.PointCard;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.PointCardService;

@Controller
@RequestMapping("/site/products/pointcard")
public class PointcardController {
	
	@Autowired
	private PointCardService pointCardService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String order_create_get(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws SQLException{
		String mv = "site/products/point_card";
		List<PointCard>  cards = pointCardService.getAllEnablePointCard();
		modelMap.put("cards", cards);
		return mv;	
	}
	
	@RequestMapping(value="/dobuy",method=RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doSendRegCode(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,Integer cardId) throws BusinessException, SQLException{
		EntityReturnData ret = new EntityReturnData();
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		pointCardService.doBuyCard(userBase,cardId);
		/**
		 * 修改金额后的用户信息放到session里
		 */
		 SessionUtil.setSiteUser2Session(request, userBase);
		 ret.setResult(true);
		return ret;
	}
		

}
