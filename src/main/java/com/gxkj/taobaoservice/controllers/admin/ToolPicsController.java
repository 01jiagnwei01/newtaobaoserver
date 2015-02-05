package com.gxkj.taobaoservice.controllers.admin;

import groovy.json.JsonOutput;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.dto.SessionConstant;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.Pics;
import com.gxkj.taobaoservice.enums.PicStatus;
import com.gxkj.taobaoservice.services.PicsService;

@Controller
@RequestMapping("/admin/tool/pics")
public class ToolPicsController {
//http://www.tuicool.com/articles/NFnaYrF
	@Autowired
	private PicsService picsService;
	
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String mv = "admin/tool/pics";
		return mv;
	}
	
	 @RequestMapping(value="/dopage",method={RequestMethod.POST})
	 @ResponseBody
    public EntityReturnData doPage( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="name",defaultValue="") String name, 
			@RequestParam(value="pageno",defaultValue="0") int pageno,
    		@RequestParam(value="limit",defaultValue="20") int pagesize
    		,ModelMap modelMap) throws SQLException  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
			 
			ListPager paper = picsService.doPage(pageno, pagesize, name, PicStatus.NORMAL);
			ret.setEntity(paper);
			 
			return ret;
	}
	 //
	 @RequestMapping(value="/upload",method={RequestMethod.POST})
	 @ResponseBody
    public void upload( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="picName",defaultValue="") String picName, 
			@RequestParam(value="picDesc",defaultValue="") String picDesc, 
			 @RequestParam(value="pic") MultipartFile[] myfiles,  
    		 ModelMap modelMap) throws SQLException, BusinessException, IOException  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
			MultipartFile pic  = null;
			if(myfiles!=null && myfiles.length>0){
				pic = myfiles[0];
			}
			String saveDir = request.getSession().getServletContext().getRealPath("");  
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			Pics entity = picsService.addPics(pic, picName, picDesc, adminUser,saveDir);
			ret.setEntity(entity);
			//out.println("＜script＞parent.callback('upload file success')＜/script＞");     
			response.getWriter().write("<script>parent.callback("+JsonOutput.toJson(ret)+")</script>");
	}
	 //
	 
	 @RequestMapping(value="/dodel",method={RequestMethod.POST})
	 @ResponseBody
    public EntityReturnData dodel( HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="id",defaultValue="0") Integer id, 
    		 ModelMap modelMap) throws Exception  {
			EntityReturnData ret = new EntityReturnData();
			ret.setMsg("执行成功");
			ret.setResult(true);
			AdminUser adminUser = SessionConstant.getAdminUserInSession(request);
			picsService.doDelPics(id,adminUser);
			 
			return ret;
	}
}
