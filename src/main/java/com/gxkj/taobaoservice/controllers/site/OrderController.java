package com.gxkj.taobaoservice.controllers.site;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.entitys.SubTaskInfo;
import com.gxkj.taobaoservice.entitys.TaskOrder;
import com.gxkj.taobaoservice.entitys.TaskOrderSubTaskInfo;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.TaskOrderService;

/**
 * 订单管理
 *
 */
@Controller
@RequestMapping("/site/order")
public class OrderController {

	private static final Log log = 
			LogFactory.getLog(OrderController.class);
	
	@Autowired
	private TaskOrderService taskOrderService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String order_create_get(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		String mv = "site/order/order_create_page";
		return mv;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String order_create_post(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws SQLException {

		String taobaoXiaohao = request.getParameter("taobaoXiaohao");
		String userQq = request.getParameter("userQq");
		String productTitle = request.getParameter("productTitle");
		String productLink = request.getParameter("productLink");

		String guaranteePriceB = request.getParameter("guaranteePrice");
		/**
		 * 基本佣金
		 */
 		String basicReceiverGainMoney = request
 				.getParameter("basicReceiverGainMoney");
		/**
		 * 奖励金额
		 */
		String encourageB = request.getParameter("encourage");
		/**
		 * 好评时效 
		 */
		String goodCommentTimeLimit = request
				.getParameter("goodCommentTimeLimit");
		/**
		 * 好评内容
		 */
		String goodCommentContent = request.getParameter("goodCommentContent");

		/**
		 * 需要旺旺聊天
		 */
		String NEED_WANGWANG_TALK = request.getParameter("NEED_WANGWANG_TALK");
		/**
		 * 限制重复接任务
		 */
		String NO_REPEAT_TASK = request.getParameter("NO_REPEAT_TASK");
		/**
		 * 指定接手人
		 */
		String ZHI_DING_JIE_SHOU_REN = request
				.getParameter("ZHI_DING_JIE_SHOU_REN");
		/**
		 * 接手人ID
		 */
		String jieShouRenIdB = request.getParameter("ZHI_DING_JIE_SHOU_REN_ID");
		/**
		 * 指定收货地址
		 */
		String ZHI_DING_SHOU_HUO_DI_ZHI = request
				.getParameter("ZHI_DING_SHOU_HUO_DI_ZHI");
		/**
		 * 收货地址
		 */
		String shouHuoDiZhi = request
				.getParameter("ZHI_DING_SHOU_HUO_DI_ZHI_ADDRESS");
		/**
		 * 批量发布条数
		 */
		String PI_LIANG_FA_BU = request.getParameter("PI_LIANG_FA_BU");
		
		String PI_LIANG_FA_BU_input = request.getParameter("PI_LIANG_FA_BU_input");
		
		
		modelMap.put("taobaoXiaohao", taobaoXiaohao);
		modelMap.put("userQq", userQq);
		modelMap.put("productTitle", productTitle);
		modelMap.put("productLink", productLink);
		modelMap.put("guaranteePrice", guaranteePriceB);
		modelMap.put("encourage", encourageB);
		modelMap.put("goodCommentTimeLimit", goodCommentTimeLimit);
		modelMap.put("goodCommentContent", goodCommentContent);
		modelMap.put("NEED_WANGWANG_TALK", NEED_WANGWANG_TALK);
		modelMap.put("NO_REPEAT_TASK", NO_REPEAT_TASK);
		modelMap.put("ZHI_DING_JIE_SHOU_REN", ZHI_DING_JIE_SHOU_REN);
		modelMap.put("ZHI_DING_JIE_SHOU_REN_ID", jieShouRenIdB);
		modelMap.put("ZHI_DING_SHOU_HUO_DI_ZHI", ZHI_DING_SHOU_HUO_DI_ZHI);
		
		modelMap.put("ZHI_DING_SHOU_HUO_DI_ZHI_ADDRESS", shouHuoDiZhi);
		modelMap.put("PI_LIANG_FA_BU", PI_LIANG_FA_BU);
		modelMap.put("PI_LIANG_FA_BU_input", PI_LIANG_FA_BU_input);
		modelMap.put("basicReceiverGainMoney", basicReceiverGainMoney);

		// BigDecimal guaranteePrice
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		boolean needWangWangTalk = false;
		if ("1".equals(NEED_WANGWANG_TALK)) {
			needWangWangTalk = true;
		}
		boolean noRepeatTalk = false;
		if ("1".equals(NO_REPEAT_TASK)) {
			noRepeatTalk = true;
		}
		boolean needZhiDingJieShouRen = false;
		if ("1".equals(ZHI_DING_JIE_SHOU_REN)) {
			needZhiDingJieShouRen = true;
		}
		boolean needZhiDingSouHuoDiZhi = false;
		if ("1".equals(ZHI_DING_SHOU_HUO_DI_ZHI)) {
			needZhiDingSouHuoDiZhi = true;
		}
		Integer piLiangFabuCount = 1;
		Integer jieShouRenId = 0;
		BigDecimal guaranteePrice = BigDecimal.ZERO;
		BigDecimal encourage  = BigDecimal.ZERO;
		  
		try {
			if("1".equals(PI_LIANG_FA_BU)){
				piLiangFabuCount = Integer.parseInt(PI_LIANG_FA_BU_input);
			}
			
		} catch (Exception e) {
			log.error("PI_LIANG_FA_BU_input ="+PI_LIANG_FA_BU_input);
		}
		try {
			 
			jieShouRenId = Integer.parseInt(jieShouRenIdB);
			 
		} catch (Exception e) {
			log.error("jieShouRenIdB ="+jieShouRenIdB);
		}
		try {
			guaranteePrice = new BigDecimal(guaranteePriceB);
		} catch (Exception e) {
			log.error("guaranteePriceB ="+guaranteePriceB);
		}
		try {
			encourage = new BigDecimal(encourageB);
		} catch (Exception e) {
			log.error("encourageB ="+encourageB);
		}

		try {
			TaskOrder order  = taskOrderService.doAddTaskOrder(userBase, taobaoXiaohao, userQq, productTitle, productLink, guaranteePrice, 
					encourage, goodCommentTimeLimit, goodCommentContent, needWangWangTalk, noRepeatTalk, needZhiDingJieShouRen, jieShouRenId, needZhiDingSouHuoDiZhi, shouHuoDiZhi, piLiangFabuCount);
			modelMap.put("order", order);
			
			List<TaskOrderSubTaskInfo> taskOrderSubTaskInfos = order.getTaskOrderSubTaskInfos();
			if(CollectionUtils.isNotEmpty(taskOrderSubTaskInfos)){
				for(TaskOrderSubTaskInfo item:taskOrderSubTaskInfos){
					modelMap.put(item.getKey(), item);
				}
			}
			String mv = "site/order/order_sure_page";
			return mv;
		} catch (BusinessException e) {
			e.printStackTrace();
			modelMap.put("error", e);
			
			String mv = "site/order/order_create_page";
			return mv;
		}

		
	}
}
