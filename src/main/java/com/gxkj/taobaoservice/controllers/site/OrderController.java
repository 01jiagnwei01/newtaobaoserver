package com.gxkj.taobaoservice.controllers.site;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.TaskOrder;
import com.gxkj.taobaoservice.entitys.TaskOrderSubTaskInfo;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.TaskOrderService;
import com.gxkj.taobaoservice.util.MoneyCalculateUtil;

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
	/**
	 * 从订单创建页创建订单
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws SQLException
	 */
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
					modelMap.put(item.getTaskKey(), item);
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
	/**
	 * 从订单确认页返回创建订单页
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws BusinessException 
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/back", method = RequestMethod.POST)
	public String order_cancel(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,Integer orderId) throws SQLException, BusinessException {
		String mv = "site/order/order_create_page";
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		TaskOrder order  = taskOrderService.getTaskOrderByOrderIdAndUserId(userBase.getId(),orderId);
		modelMap.put("order", order);
		return mv;
	}
	
	@RequestMapping(value = "/sure", method = RequestMethod.POST)
	public String order_sure(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,Integer orderId) throws SQLException   {
		String mv = "site/order/order_sure_result";
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		try{
			taskOrderService.doapplyTaskOrderByOrderIdAndUserId(userBase,orderId);
		}catch(BusinessException e){
			e.printStackTrace();
			try{
				TaskOrder taskOrder = taskOrderService.getTaskOrderByOrderIdAndUserId(userBase.getId(), orderId);
				/**
				 * 计算总消耗费用
				 */
				MoneyCalculateUtil.caculateOrderAccount(taskOrder);
				modelMap.put("order", taskOrder);
				
				List<TaskOrderSubTaskInfo> taskOrderSubTaskInfos = taskOrder.getTaskOrderSubTaskInfos();
				if(CollectionUtils.isNotEmpty(taskOrderSubTaskInfos)){
					for(TaskOrderSubTaskInfo item:taskOrderSubTaskInfos){
						modelMap.put(item.getTaskKey(), item);
					}
				}  
			}catch(BusinessException e1){
				modelMap.put("error", e1);
				
			}
			mv = "site/order/order_sure_page";
			modelMap.put("error", e);
		}
		return mv;
	}
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String order_detail(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,Integer orderId) throws SQLException   {
		String mv = "site/order/order_detail_page";
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		try{
			TaskOrder taskOrder = taskOrderService.getTaskOrderByOrderIdAndUserId(userBase.getId(), orderId);
			/**
			 * 计算总消耗费用
			 */
			MoneyCalculateUtil.caculateOrderAccount(taskOrder);
			modelMap.put("order", taskOrder);
			
			List<TaskOrderSubTaskInfo> taskOrderSubTaskInfos = taskOrder.getTaskOrderSubTaskInfos();
			if(CollectionUtils.isNotEmpty(taskOrderSubTaskInfos)){
				for(TaskOrderSubTaskInfo item:taskOrderSubTaskInfos){
					modelMap.put(item.getTaskKey(), item);
				}
			}
			
			
		}catch(BusinessException e){
			modelMap.put("error", e);
			
		}
		return mv;
	}
	/**
	 * 订单列表页异步取消订单
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param orderId
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/docancel", method = RequestMethod.POST)
	@ResponseBody
	public EntityReturnData docancel(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,Integer orderId) throws SQLException, BusinessException {
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		  taskOrderService.docancelTaskOrderByOrderIdAndUserId(userBase.getId(),orderId);
		EntityReturnData ret = new EntityReturnData();
		ret.setResult(true);
		ret.setEntity(userBase);
		return ret;	
	}
	/**
	 * 订单列表页异步订单确认
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param orderId
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/doapply", method = RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doapply(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,Integer orderId) throws SQLException, BusinessException {
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		  taskOrderService.doapplyTaskOrderByOrderIdAndUserId(userBase,orderId);
		EntityReturnData ret = new EntityReturnData();
		ret.setResult(true);
		ret.setEntity(userBase);
		return ret;	
	}
	
	@RequestMapping(value="/mylist",method={RequestMethod.GET})
	   public String doPage( HttpServletRequest request,
				HttpServletResponse response,
			@RequestParam(value="starttime",defaultValue="") String starttime,
			@RequestParam(value="endtime",defaultValue="") String endtime, 
			@RequestParam(value="pageno",defaultValue="0") int pageno,
	   		@RequestParam(value="limit",defaultValue="20") int pagesize
	   		,ModelMap modelMap) throws SQLException  {
				 
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
				ListPager paper = taskOrderService.doPage(userBase,pageno, pagesize, startTime, endTime);
				modelMap.put("starttime", starttime);
				modelMap.put("endtime", endtime);
				modelMap.put("pageno", pageno);
				modelMap.put("pagesize", pagesize);
				modelMap.put("paper", paper);
				String mv = "site/order/myorder_page";
				return mv;	
		}
}
