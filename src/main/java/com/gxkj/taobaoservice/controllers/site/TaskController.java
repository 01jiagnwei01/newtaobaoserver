package com.gxkj.taobaoservice.controllers.site;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
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
import com.gxkj.taobaoservice.entitys.TaskBasic;
import com.gxkj.taobaoservice.entitys.TaskOrderSubTaskInfo;
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
	@RequestMapping(value="/mypublishlist",method={RequestMethod.GET})
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
				String mv = "site/task/mypublishlist";
				return mv;	
	}
	/**
	 * 我接的的任务列表
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
	@RequestMapping(value="/myreceivelist",method={RequestMethod.GET})
	   public String myreceivelist( HttpServletRequest request,
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
				log.info("myreceivelist ");
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
				ListPager paper = taskBasicService.doMyReceiveTaskPage(userBase,null,pageno, pagesize, startTime, endTime);
				modelMap.put("starttime", starttime);
				modelMap.put("endtime", endtime);
				modelMap.put("pageno", pageno);
				modelMap.put("pagesize", pagesize);
				modelMap.put("paper", paper);
				String mv = "site/task/myreceivelist";
				return mv;	
	}
	/**
	 * 查看我完成的任务
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
	@RequestMapping(value="/mycompletelist",method={RequestMethod.GET})
	   public String mycompletelist( HttpServletRequest request,
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
				ListPager paper = taskBasicService.getMyCompletelistPage(userBase,pageno, pagesize, startTime, endTime);
				modelMap.put("starttime", starttime);
				modelMap.put("endtime", endtime);
				modelMap.put("pageno", pageno);
				modelMap.put("pagesize", pagesize);
				modelMap.put("paper", paper);
				String mv = "site/task/mycompletelist";
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
			HttpServletResponse response, ModelMap modelMap,Integer taskId) throws SQLException   {
		String mv = "site/task/task_detail_page";
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		modelMap.put("userBase", userBase);
		
		try{
			TaskBasic taskBasic = taskBasicService.getTaskByIdForSite( taskId);
			modelMap.put("taskBasic", taskBasic);
			
			List<TaskOrderSubTaskInfo>  taskOrderSubTaskInfos = taskBasic.getTaskOrderSubTaskInfos();
			if(CollectionUtils.isNotEmpty(taskOrderSubTaskInfos)){
				for(TaskOrderSubTaskInfo item : taskOrderSubTaskInfos){
					modelMap.put( item.getTaskKey(), item);
				}
			}
		}catch(BusinessException e){
			modelMap.put("error", e);
			mv = "site/task/task_no_exist";
			
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
				String mv = "site/task/task_page";
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
	 * @throws ParseException 
	 */
	@RequestMapping(value="/recievetask",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doReceiveTask(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap, 
			@RequestParam(value="taskid",defaultValue="0") int taskid) throws SQLException, BusinessException, ParseException {
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		 EntityReturnData ret = new EntityReturnData();
		 taskBasicService.doReceiveTask(userBase,taskid);
		 ret.setResult(true);
		 ret.setMsg("接单成功");
 		 return ret;	
	}
	/**
	 * 接单人任务完成任务
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param taskid
	 * @return
	 * @throws SQLException
	 * @throws BusinessException 
	 */
	@RequestMapping(value="/reciercomplete",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData reciercomplete(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap, 
			@RequestParam(value="taskid",defaultValue="0") int taskid) throws SQLException, BusinessException {
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		 EntityReturnData ret = new EntityReturnData();
		 taskBasicService.doRecierCompleteTask(userBase,taskid);
		ret.setResult(true);
		ret.setMsg("任务完成");
	 		 return ret;
	}
	/**
	 * 接单人放弃任务
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param taskid
	 * @return
	 * @throws SQLException
	 * @throws BusinessException 
	 */
	@RequestMapping(value="/reciergiveup",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData reciergiveup(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap, 
			@RequestParam(value="taskid",defaultValue="0") int taskid) throws SQLException, BusinessException {
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		 EntityReturnData ret = new EntityReturnData();
			 taskBasicService.doRergiveupTask(userBase,taskid);
			 ret.setResult(true);
				ret.setMsg("任务放弃");
			 		 return ret;
	}
	/**
	 * 确定接单人完成任务
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param taskid
	 * @return
	 * @throws SQLException
	 * @throws BusinessException 
	 */
	@RequestMapping(value="/surecomplete",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData  surecomplete(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap, 
			@RequestParam(value="taskid",defaultValue="0") int taskid) throws SQLException, BusinessException {
		UserBase userBase = SessionUtil.getSiteUserInSession(request);
		 EntityReturnData ret = new EntityReturnData();
		 taskBasicService.doSureRecierCompleteTask(userBase,taskid);
		 ret.setResult(true);
			ret.setMsg("任务结束");
		 		 return ret;
	}
}
