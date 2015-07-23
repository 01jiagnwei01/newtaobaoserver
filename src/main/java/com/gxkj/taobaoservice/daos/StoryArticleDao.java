package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.StoryArticleDesc;
import com.gxkj.taobaoservice.enums.StoryArticleStatus;

public interface StoryArticleDao extends BaseDAO {
	
	/**
	 * 根据ID删除故事的内容 clob
	 * @param articleId
	 */
	void deleteContentById(Integer articleId);

	/**
	 * 根据ID查询故事信息
	 * @param articleId
	 * @return
	 * @throws SQLException 
	 */
	StoryArticleDesc selectStoryArticleDescById(int articleId) throws SQLException;
	
	/**
	 * 分页查看故事列表
	 * @param pageno
	 * @param pagesize
	 * @param title
	 * @param status
	 * @return
	 * @throws SQLException 
	 */
	ListPager doPageFromDb(int pageno, int pagesize, String title,
			StoryArticleStatus status) throws SQLException;

}
