package com.gxkj.taobaoservice.controllers.site;

import java.lang.reflect.InvocationTargetException;
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
import com.gxkj.common.util.SystemGlobals;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.TaskOrder;
import com.gxkj.taobaoservice.entitys.TaskOrderSubTaskInfo;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.TaskOrderStatus;
import com.gxkj.taobaoservice.services.TaskOrderService;
import com.gxkj.taobaoservice.util.MoneyCalculateUtil;
import com.gxkj.taobaoservice.util.SubHeaderTag;

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
		modelMap.put("basicPingtaiGainPoint", SystemGlobals.getPreference("taobao.order.grant.point", "0"));
		modelMap.put(SubHeaderTag.tagName, "ordercreate");
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

		String orderid = request.getParameter("orderid");
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
		
		modelMap.put(SubHeaderTag.tagName, "ordercreate");
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
		Integer dbOrderId = 0;
		Integer jieShouRenId = 0;
		BigDecimal guaranteePrice = BigDecimal.ZERO;
		BigDecimal encourage  = BigDecimal.ZERO;
		if(StringUtils.isNoneBlank(orderid)){
			dbOrderId = Integer.parseInt(orderid);
		}
		  
		try {
			if("1".equals(PI_LIANG_FA_BU)){
				piLiangFabuCount = Integer.parseInt(PI_LIANG_FA_BU_input);
			}
			
		} catch (Exception e) {
			log.error("PI_LIANG_FA_BU_input ="+PI_LIANG_FA_BU_input);
		}
		try {
			 
			if(StringUtils.isNotBlank(jieShouRenIdB)){
				jieShouRenId = Integer.parseInt(jieShouRenIdB);
			}
			 
		} catch (Exception e) {
			log.error("jieShouRenIdB ="+jieShouRenIdB);
		}
		try {
			guaranteePrice = new BigDecimal(guaranteePriceB);
		} catch (Exception e) {
			log.error("guaranteePriceB ="+guaranteePriceB);
		}
		try {
			if(StringUtils.isNotBlank(encourageB)){
				encourage = new BigDecimal(encourageB);
			}
		} catch (Exception e) {
			log.error("encourageB ="+encourageB);
		}

		try {
			
			TaskOrder order  = null;
			if(dbOrderId == 0){
				order = taskOrderService.doAddTaskOrder(  userBase, taobaoXiaohao, userQq, productTitle, productLink, guaranteePrice, 
						encourage, goodCommentTimeLimit, goodCommentContent, needWangWangTalk, noRepeatTalk, needZhiDingJieShouRen, jieShouRenId, needZhiDingSouHuoDiZhi, shouHuoDiZhi, piLiangFabuCount);
				
			}else {
				order = taskOrderService.doUpdateTaskOrder( dbOrderId,userBase, taobaoXiaohao, userQq, productTitle, productLink, guaranteePrice, 
						encourage, goodCommentTimeLimit, goodCommentContent, needWangWangTalk, noRepeatTalk, needZhiDingJieShouRen, jieShouRenId, needZhiDingSouHuoDiZhi, shouHuoDiZhi, piLiangFabuCount);
				
			}
			 
			/**
			 * 计算总消耗费用
			 */
			MoneyCalculateUtil.caculateOrderAccount(order);
			modelMap.put("order", order);
			
			List<TaskOrderSubTaskInfo> taskOrderSubTaskInfos = order.getTaskOrderSubTaskInfos();
			if(CollectionUtils.isNotEmpty(taskOrderSubTaskInfos)){
				for(TaskOrderSubTaskInfo item:taskOrderSubTaskInfos){
					modelMap.put(item.getTaskKey(), item);
				}
			}
			modelMap.put("tag", "order_sure");
			String mv = "site/order/order_detail_page";
			return mv;
		} catch (BusinessException e) {
			e.printStackTrace();
			modelMap.put("error", e);
			if(dbOrderId != 0){
				TaskOrder taskOrder;
				try {
					taskOrder = taskOrderService.getTaskOrderByOrderIdAndUserId(userBase.getId(), dbOrderId);
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
				} catch (BusinessException e1) {
					 
					e1.printStackTrace();
				}
			}
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
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String order_update(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,Integer orderId) throws SQLException, BusinessException {
		String mv = "site/order/order_create_page";
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		TaskOrder order  = taskOrderService.getTaskOrderByOrderIdAndUserId(userBase.getId(),orderId);

		modelMap.put("taobaoXiaohao", order.getTaobaoXiaohao());
		modelMap.put("userQq", order.getUserQq());
		modelMap.put("productTitle", order.getProductTitle());
		modelMap.put("productLink", order.getProductLink());
		modelMap.put("guaranteePrice", order.getGuaranteePrice());
		modelMap.put("encourage", order.getEncourage());
		
		List<TaskOrderSubTaskInfo> taskOrderSubTaskInfos = order.getTaskOrderSubTaskInfos();
		String goodCommentTimeLimit = "";
		String goodCommentContent = "";
		String NEED_WANGWANG_TALK = "0";
		String NO_REPEAT_TASK = "";
		String ZHI_DING_JIE_SHOU_REN ="0";
		String jieShouRenIdB ="";
		String ZHI_DING_SHOU_HUO_DI_ZHI = "0";
		String shouHuoDiZhi = "";
		String PI_LIANG_FA_BU = "0";
		String PI_LIANG_FA_BU_input = "";
		BigDecimal basicReceiverGainMoney = order.getBasicReceiverGainMoney();
		for(TaskOrderSubTaskInfo item : taskOrderSubTaskInfos){
			if(item.getTaskKey().equals("GOOD_COMMENT_TIME_LIMIT")){
				goodCommentTimeLimit = item.getInputValue();
			}else if(item.getTaskKey().equals("GOOD_COMMENT_CONTENT")){
				goodCommentContent = item.getInputValue();
			}else if(item.getTaskKey().equals("NEED_WANGWANG_TALK")){
				NEED_WANGWANG_TALK = "1";
			}else if(item.getTaskKey().equals("NO_REPEAT_TASK")){
				NO_REPEAT_TASK = item.getInputValue();
			}else if(item.getTaskKey().equals("ZHI_DING_JIE_SHOU_REN")){
				ZHI_DING_JIE_SHOU_REN = "1";
				jieShouRenIdB = item.getInputValue();
			}else if(item.getTaskKey().equals("ZHI_DING_SHOU_HUO_DI_ZHI")){
				ZHI_DING_SHOU_HUO_DI_ZHI = "1";
				shouHuoDiZhi = item.getInputValue();
			}else if(item.getTaskKey().equals("PI_LIANG_FA_BU")){
				PI_LIANG_FA_BU = "1";
				PI_LIANG_FA_BU_input = item.getInputValue();
			}
		}
		modelMap.put("goodCommentTimeLimit", goodCommentTimeLimit );
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
		modelMap.put("basicPingtaiGainPoint", order.getBasicPingtaiGainPoint());
		
		modelMap.put("order", order);
		return mv;
	}
	
	@RequestMapping(value = "/sure", method = RequestMethod.POST)
	public String order_sure(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,Integer orderId) throws SQLException, IllegalAccessException, InvocationTargetException   {
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
			mv = "site/order/order_detail_page";
			modelMap.put("error", e);
		}
		return mv;
	}
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String order_detail(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,Integer orderId) throws SQLException   {
		modelMap.put("tag", "order_detail");
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
			mv = "site/order/order_no_exist";
			
		}
		return mv;
	}
	/**
	 * 订单列表页取消订单
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param orderId
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/docancel", method = RequestMethod.POST)
	public String docancel(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,Integer orderId) throws SQLException{
		String mv = "site/order/order_detail_page";
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		  try {
			taskOrderService.docancelTaskOrderByOrderIdAndUserId(userBase.getId(),orderId);
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
		} catch (BusinessException e) {
			e.printStackTrace();
			modelMap.put("error", e);
			mv = "site/error/business_error_page";
			
		}
		  
		return mv;	
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
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value = "/doapply", method = RequestMethod.POST)
	@ResponseBody
	public EntityReturnData doapply(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,Integer orderId) throws SQLException, BusinessException, IllegalAccessException, InvocationTargetException {
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		  taskOrderService.doapplyTaskOrderByOrderIdAndUserId(userBase,orderId);
		EntityReturnData ret = new EntityReturnData();
		ret.setResult(true);
		ret.setEntity(userBase);
		return ret;	
	}
	
	@RequestMapping(value="/myorderpage",method={RequestMethod.GET})
	   public String myorderpage( HttpServletRequest request,
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
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String order_update_Get(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap){
		return "forward:/site/order/create";
	}
	@RequestMapping(value = "/docancel", method = RequestMethod.GET)
	public String order_docancel_Get(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap){
		return "forward:/site/order/create";
	}
	@RequestMapping(value = "/sure", method = RequestMethod.GET)
	public String order_sure_Get(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap){
		return "forward:/site/order/create";
	}
	
}
