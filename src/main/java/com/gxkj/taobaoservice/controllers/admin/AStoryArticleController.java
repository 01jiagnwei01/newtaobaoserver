package com.gxkj.taobaoservice.controllers.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.dto.SessionConstant;
import com.gxkj.taobaoservice.dto.StoryArticleDTO;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.enums.StoryArticleStatus;
import com.gxkj.taobaoservice.enums.StoryTypes;
import com.gxkj.taobaoservice.services.StoryArticleService;

@Controller
@RequestMapping("/admin/{storytype}")
public class AStoryArticleController {

	@Autowired
	private StoryArticleService storyArticleService;
	
	@RequestMapping(value="index",method={RequestMethod.GET})
	public String index(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap
			,@PathVariable StoryTypes storytype){
		modelMap.put("type", storytype);
		String mv = "admin/story/story_index";
		return mv;
	}
	@RequestMapping(value="dopage",method={RequestMethod.GET})
	public  @ResponseBody EntityReturnData doPage(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap
			,@RequestParam(value="pageno",defaultValue="0") int pageno
    		,@RequestParam(value="limit",defaultValue="20") int pagesize
    		,@RequestParam(value="status",required=false) StoryArticleStatus status
    		,@RequestParam(value="title",required=false) String title
			,@PathVariable StoryTypes storytype) throws Exception{
		modelMap.put("type", storytype);
		ListPager pager = storyArticleService.doPage4Admin(pageno, pagesize, title, status);
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setResult(true);
		ret.setEntity(pager);
		return ret;
	}
	
	@RequestMapping(value="/doadd",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doAdd( HttpServletRequest request, HttpServletResponse response,
			StoryArticleDTO entity,@PathVariable StoryTypes storytype, 
			ModelMap modelMap) throws Exception  {
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setResult(true);
		entity.setType(storytype);
		AdminUser  adminUser = SessionConstant.getAdminUserInSession(request);
		entity = storyArticleService.addStoryArticle(entity, adminUser);
		 
		ret.setEntity(entity);
		
		return ret;
	}
	@RequestMapping(value="/doupdate",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData doUpdate( HttpServletRequest request, HttpServletResponse response,
			StoryArticleDTO entity,@PathVariable StoryTypes storytype, 
			ModelMap modelMap) throws Exception  {
		EntityReturnData ret = new EntityReturnData();
		entity.setType(storytype);
		ret.setMsg("执行成功");
		ret.setResult(true);
		AdminUser  adminUser = SessionConstant.getAdminUserInSession(request);
		entity = storyArticleService.updateStoryArticleDTO(entity, adminUser);
		ret.setEntity(entity);
		return ret;
	}
	@RequestMapping(value="/setstatus",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData setstatus( HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="storyId")Integer storyId, StoryArticleStatus status,
			ModelMap modelMap) throws Exception  {
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setResult(true);
		AdminUser  adminUser = SessionConstant.getAdminUserInSession(request);
		storyArticleService.updateStoryArticleDTOStatus(storyId, status, adminUser);
		 
		return ret;
	}
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	@ResponseBody
	public EntityReturnData delete( HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="storyId")Integer storyId,
			ModelMap modelMap) throws Exception  {
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setResult(true);
		AdminUser  adminUser = SessionConstant.getAdminUserInSession(request);
		storyArticleService.updateStoryArticleDTOStatus(storyId, StoryArticleStatus.DEL, adminUser);
		 
		return ret;
	}
	@RequestMapping(value="/detail",method={RequestMethod.GET})
	@ResponseBody
	public EntityReturnData detail( HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="storyId")Integer articleId,
			ModelMap modelMap) throws Exception  {
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setResult(true);
	 
		StoryArticleDTO dto = storyArticleService.getStoryArticleDTOById(articleId);
		ret.setEntity(dto);
		return ret;
	}
}
