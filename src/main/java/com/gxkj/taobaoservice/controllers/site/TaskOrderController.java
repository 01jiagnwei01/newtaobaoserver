package com.gxkj.taobaoservice.controllers.site;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.TaskOrder;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.TaskOrderService;

@Controller
@RequestMapping("/taskorder")
public class TaskOrderController {
	
	@Autowired
	private TaskOrderService taskOrderService;
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String login(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		String mv = "site/taskorder_new";
		return mv;
	}
	
	/**
	 * 创建一个任务订单，每个任务订单需要需要 0.5元平台币
	 * @param taskOrder
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@ResponseBody
	public EntityReturnData createTaskOrder(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,TaskOrder taskOrder) throws SQLException{
		EntityReturnData result = new EntityReturnData();
		UserBase userBase  = SessionUtil.getSiteUserInSession(request);
		result = taskOrderService.addTaskOrder(taskOrder,userBase);
		return  result;
		
	}

}
