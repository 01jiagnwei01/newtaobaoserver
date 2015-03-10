package com.gxkj.taobaoservice.controllers.admin;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.TaskBasic;
import com.gxkj.taobaoservice.enums.TaskStatus;
import com.gxkj.taobaoservice.services.TaskBasicService;

@Controller
@RequestMapping("/admin/task")
public class TTarkController {
	
	@Autowired
	private  TaskBasicService taskBasicService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
 
    }

	@RequestMapping(value="",method={RequestMethod.GET})
	public String haveclose(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "admin/task/task_page";
		return mv;
	}
	
	@RequestMapping(value="/dopage",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doPage( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="pageno",defaultValue="0") int pageno,
  		@RequestParam(value="limit",defaultValue="20") int pagesize,
		@RequestParam(value="producttittle",defaultValue="") String producttittle,
		@RequestParam(value="status",defaultValue="",required=false)   TaskStatus status,
		@RequestParam(value="userId" ,required=false)  Integer userId,  
		@RequestParam(value="beginTime" ,required=false)  Date beginTime,
		@RequestParam(value="endTime" ,required=false)  Date endTime, 
		@RequestParam(value="taobao" ,required=false)  String taobao,
		@RequestParam(value="qq" ,required=false)  String qq, 
		@RequestParam(value="receivetaobao" ,required=false)  String receivetaobao,
		@RequestParam(value="receiveqq" ,required=false)  String receiveqq, 
		@RequestParam(value="receivebeginTime" ,required=false)  Date receivebeginTime,
		@RequestParam(value="receiveendTime" ,required=false)  Date receiveendTime,
  		 ModelMap modelMap)  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
			try {
				ListPager paper = taskBasicService.doPageForAdmin(pageno, pagesize, producttittle, status, userId, beginTime,endTime,taobao,qq,receivetaobao,receiveqq,receivebeginTime,receiveendTime);
				ret.setEntity(paper);
			} catch (Exception e) {
				e.printStackTrace();
				ret.setMsg("失败");
				ret.setResult(false);
			}
			return ret;
	}
	
	@RequestMapping(value="detail",method={RequestMethod.GET})
	@ResponseBody
	public EntityReturnData  detail(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,@RequestParam(value="id") int id) throws SQLException, BusinessException{
		TaskBasic taskBasic = taskBasicService.getTaskByIdForAdmin(id);
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setEntity(taskBasic);
		ret.setResult(true);
		return ret;
	}
	
	
	
}
