package com.gxkj.taobaoservice.controllers;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.dto.StoryArticleDTO;
import com.gxkj.taobaoservice.entitys.StoryArticle;
import com.gxkj.taobaoservice.enums.StoryArticleStatus;
import com.gxkj.taobaoservice.services.StoryArticleService;

@Controller
@RequestMapping("/story")
public class SiteStoryController {

	protected final  Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired
	private StoryArticleService storyArticleService;
	
	/**
	 * 第一版先展示前100个故事
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String storyList(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		
		 logger.info( "/**********************************头部信息开始*******************************/");
			Enumeration<?> enumer = request.getHeaderNames();
	        while (enumer.hasMoreElements()) {
	            String name = (String) enumer.nextElement();
	            logger.info( "{}={}",name,request.getHeader(name));
	        }
	        logger.info( "/**********************************头部信息结束*******************************/");
	        
		String mv = "site/story/site_story_list";
		try {
			ListPager pager = storyArticleService.doPage4Site(0, 100, null, StoryArticleStatus.NORMAL);
			if(pager.getTotalRows()>0){
				modelMap.put("list", pager.getPageData());
			}else{
				modelMap.put("list", new ArrayList<Object>());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			modelMap.put("story", null);
		}
		
		return mv;	
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String storyDetail(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			@PathVariable("id")int id){
		String mv = "site/story/site_story_detail";
		String agent = request.getHeader("User-Agent");
		logger.info("User-Agent={}",agent);
		try {
			StoryArticleDTO story = storyArticleService.getStoryArticleDTOById(id);
			modelMap.put("story", story);
			modelMap.put("title", story!=null?story.getArticleTitle():null);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			modelMap.put("story", null);
		}
		
		return mv;	
	}
	@RequestMapping(value="look/{id}",method=RequestMethod.POST)
	public @ResponseBody EntityReturnData  addHitTimes(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			@PathVariable("id")int id){
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setResult(true);
		try {
			storyArticleService.addHitTimes(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			ret.setResult(false);
		}
		return ret;
	}
	@RequestMapping(value="tire/{id}",method=RequestMethod.POST)
	public @ResponseBody EntityReturnData  addTiresomeNumber(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			@PathVariable("id")int id){
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setResult(true);
		try {
			StoryArticle article =	storyArticleService.addTiresomeNumber(id,11111);
			ret.setEntity(article.getTiresomeNumber());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			ret.setResult(false);
		}
		return ret;
	}
	@RequestMapping(value="praise/{id}",method=RequestMethod.POST)
	public @ResponseBody EntityReturnData  addPraiseNumber(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			@PathVariable("id")int id){
		EntityReturnData ret = new EntityReturnData();
		ret.setMsg("执行成功");
		ret.setResult(true);
		try {
			StoryArticle article =	storyArticleService.addPraiseNumber(id,11111);
			ret.setEntity(article.getPraiseNumber());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			ret.setResult(false);
		}
		return ret;
	}
}
