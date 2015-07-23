package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.dto.StoryArticleDTO;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.StoryArticle;
import com.gxkj.taobaoservice.enums.StoryArticleStatus;

public interface StoryArticleService {

	/**
	 * 增加一篇故事
	 * @param story
	 * @param adminUser	管理员
	 * @return
	 * @throws SQLException 
	 */
	public StoryArticleDTO addStoryArticle(StoryArticleDTO story,AdminUser adminUser) throws SQLException;
	
	/**
	 * 修改一篇故事
	 * @param story
	 * @param adminUser	管理员
	 * @return
	 * @throws SQLException 
	 */
	public StoryArticleDTO updateStoryArticleDTO(StoryArticleDTO story,AdminUser adminUser) throws SQLException;
	 
	/**
	 * 设置故事的状态
	 * @param storyId 故事ID
	 * @param status	故事状态
	 * @param adminUser	管理员
	 * @throws SQLException 
	 */
	public void updateStoryArticleDTOStatus(int storyId,StoryArticleStatus status,AdminUser adminUser) throws SQLException;

	/**
	 * 根据故事id查询故事
	 * @param articleId
	 * @return
	 * @throws SQLException 
	 */
	public StoryArticleDTO getStoryArticleDTOById(int articleId) throws SQLException;
	
	/**
	 * 管理端分页查询文章
	 * @param pageno
	 * @param pagesize
	 * @param title
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public ListPager doPage4Admin(int pageno, int pagesize, String title ,StoryArticleStatus status) throws Exception;
	
	/**
	 * 前端分页查询文章
	 * @param pageno
	 * @param pagesize
	 * @param title
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public ListPager doPage4Site(int pageno, int pagesize, String title ,StoryArticleStatus status) throws Exception;
	
	/**
	 * 增加文章的点击次数
	 * @param storyId
	 * @throws Exception
	 */
	public void addHitTimes (int storyId)throws Exception;
	
	/**
	 * 增加点赞数
	 * @param storyId
	 * @param ip
	 * @return 
	 * @throws Exception
	 */
	public StoryArticle addPraiseNumber (int storyId,Integer uid )throws Exception;
	
	/**
	 * 增加不喜欢数值
	 * @param storyId
	 * @param ip
	 * @throws Exception
	 */
	public StoryArticle addTiresomeNumber (int storyId,Integer uid )throws Exception;
	
}
