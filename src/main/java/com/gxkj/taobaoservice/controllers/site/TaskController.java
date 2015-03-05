package com.gxkj.taobaoservice.controllers.site;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.entitys.TaskBasic;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.TaskBasicService;

@Controller
@RequestMapping("/site/task")
public class TaskController {
	private static final Log log =  LogFactory.getLog(TaskController.class);
	
	@Autowired
	private TaskBasicService taskBasicService;
	
	/**
	 * 我发布的任务列表
	 * @param request
	 * @param response
	 * @param starttime
	 * @param endtime
	 * @param pageno
	 * @param pagesize
	 * @param modelMap
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	@RequestMapping(value="/mylist",method={RequestMethod.GET})
	   public String doPage( HttpServletRequest request,
				HttpServletResponse response,
			@RequestParam(value="starttime",defaultValue="") String starttime,
			@RequestParam(value="endtime",defaultValue="") String endtime, 
			@RequestParam(value="pageno",defaultValue="0") int pageno,
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
				ListPager paper = taskBasicService.doPage(userBase,null,pageno, pagesize, startTime, endTime);
				modelMap.put("starttime", starttime);
				modelMap.put("endtime", endtime);
				modelMap.put("pageno", pageno);
				modelMap.put("pagesize", pagesize);
				modelMap.put("paper", paper);
				String mv = "site/order/myorder_page";
				return mv;	
	}
	/**
	 * 查看任务
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param orderId
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String order_detail(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,Integer orderId) throws SQLException   {
		String mv = "site/order/order_detail_page";
		try{
			TaskBasic taskBasic = taskBasicService.getTaskById( orderId);
			modelMap.put("taskBasic", taskBasic);
		}catch(BusinessException e){
			modelMap.put("error", e);
			mv = "site/order/task_no_exist";
			
		}
		return mv;
	}
	/**
	 * 任务大厅
	 * @param request
	 * @param response
	 * @param starttime
	 * @param endtime
	 * @param pageno
	 * @param pagesize
	 * @param modelMap
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	@RequestMapping(value="/list",method={RequestMethod.GET})
	   public String doALLPage( HttpServletRequest request,
				HttpServletResponse response,
			@RequestParam(value="starttime",defaultValue="") String starttime,
			@RequestParam(value="endtime",defaultValue="") String endtime, 
			@RequestParam(value="pageno",defaultValue="0") int pageno,
	   		@RequestParam(value="limit",defaultValue="20") int pagesize
	   		,ModelMap modelMap) throws SQLException, BusinessException  {
				Date startTime = null;
				Date endTime = null;
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				
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
				ListPager paper = taskBasicService.doPageForDaTing(pageno, pagesize, startTime, endTime);
				modelMap.put("starttime", starttime);
				modelMap.put("endtime", endtime);
				modelMap.put("pageno", pageno);
				modelMap.put("pagesize", pagesize);
				modelMap.put("paper", paper);
				String mv = "site/order/myorder_page";
				return mv;	
	}
	/**
	 * 接单
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param taskid
	 * @return
	 * @throws BusinessException 
	 * @throws SQLException 
	 */
	@RequestMapping(value="/recievetask",method={RequestMethod.POST})
	public String doReceiveTask(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap, 
			@RequestParam(value="taskid",defaultValue="0") int taskid) throws SQLException {
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		TaskBasic taskBasic;
		try {
			taskBasic = taskBasicService.doReceiveTask(userBase,taskid);
			taskBasic = taskBasicService.getTaskById( taskid);
			modelMap.put("taskBasic", taskBasic);
		} catch (BusinessException e) {
			 if(e.getSiteFlag().equals("status")){
				 try {
					taskBasic = taskBasicService.getTaskById( taskid);
					modelMap.put("taskBasic", taskBasic);
				} catch (BusinessException e1) {
					e1.printStackTrace();
				}
			 }else{
				 modelMap.put("error", e);
			 }
			e.printStackTrace();
		}
		return "site/task/task_detail";
	}
	/**
	 * 接单任务完成任务
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param taskid
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value="/reciercomplete",method={RequestMethod.POST})
	public String reciercomplete(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap, 
			@RequestParam(value="taskid",defaultValue="0") int taskid) throws SQLException {
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		TaskBasic taskBasic;
		try {
			taskBasic = taskBasicService.doRecierCompleteTask(userBase,taskid);
			taskBasic = taskBasicService.getTaskById( taskid);
			modelMap.put("taskBasic", taskBasic);
		} catch (BusinessException e) {
			 if(e.getSiteFlag().equals("status")){
				 try {
					taskBasic = taskBasicService.getTaskById( taskid);
					modelMap.put("taskBasic", taskBasic);
				} catch (BusinessException e1) {
					e1.printStackTrace();
				}
			 }else{
				 modelMap.put("error", e);
			 }
			e.printStackTrace();
		}
		return "site/task/task_detail";
	}
	/**
	 * 确定接单人完成任务
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param taskid
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value="/surecomplete",method={RequestMethod.POST})
	public String surecomplete(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap, 
			@RequestParam(value="taskid",defaultValue="0") int taskid) throws SQLException {
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		TaskBasic taskBasic;
		try {
			taskBasic = taskBasicService.doSureRecierCompleteTask(userBase,taskid);
			taskBasic = taskBasicService.getTaskById( taskid);
			modelMap.put("taskBasic", taskBasic);
		} catch (BusinessException e) {
			 if(e.getSiteFlag().equals("status")){
				 try {
					taskBasic = taskBasicService.getTaskById( taskid);
					modelMap.put("taskBasic", taskBasic);
				} catch (BusinessException e1) {
					e1.printStackTrace();
				}
			 }else{
				 modelMap.put("error", e);
			 }
			e.printStackTrace();
		}
		return "site/task/task_detail";
	}
}
