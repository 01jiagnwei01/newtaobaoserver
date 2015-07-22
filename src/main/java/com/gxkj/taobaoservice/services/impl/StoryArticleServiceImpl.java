package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.StoryArticleDao;
import com.gxkj.taobaoservice.dto.StoryArticleDTO;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.StoryArticle;
import com.gxkj.taobaoservice.entitys.StoryArticleDesc;
import com.gxkj.taobaoservice.enums.StoryArticleStatus;
import com.gxkj.taobaoservice.services.StoryArticleService;

@Service
public class StoryArticleServiceImpl implements StoryArticleService {

	@Autowired
	private StoryArticleDao storyArticleDao;

	public StoryArticleDTO addStoryArticle(StoryArticleDTO story,
			AdminUser adminUser) throws SQLException {
		story.setStatus(StoryArticleStatus.WAIT4REVIEW);
		Date now = new Date();
		story.setAddTime(now);
		story.setAddUserId(adminUser.getId());
		story.setUpdateTime(now);
		story.setUpdateUserId(adminUser.getId());
		StoryArticle article = new StoryArticle();

		BeanUtils.copyProperties(story, article);
		storyArticleDao.insert(article);
		story.setArticleId(article.getArticleId());
		if (StringUtils.isNotBlank(story.getArticleContent())) {
			StoryArticleDesc desc = new StoryArticleDesc();
			desc.setArticleId(article.getArticleId());
			desc.setArticleContent(story.getArticleContent());
			storyArticleDao.insert(desc);
		}
		return story;
	}

	public StoryArticleDTO updateStoryArticleDTO(StoryArticleDTO story,
			AdminUser adminUser) throws SQLException {
		story.setUpdateTime(new Date());
		
		story.setStatus(StoryArticleStatus.WAIT4REVIEW);
		story.setUpdateUserId(adminUser.getId());
		StoryArticle dbarticle = (StoryArticle) storyArticleDao.selectById(
				story.getArticleId(), StoryArticle.class);

		Date addTime = dbarticle.getAddTime();
		Integer addUserId = dbarticle.getAddUserId();
		Integer hitTimes = dbarticle.getHitTimes();
		Integer praiseNumber = dbarticle.getPraiseNumber();
		Integer tiresomeNumber = dbarticle.getTiresomeNumber();
		BeanUtils.copyProperties(story, dbarticle);
		dbarticle.setAddTime(addTime);
		dbarticle.setAddUserId(addUserId);
		dbarticle.setHitTimes(hitTimes);
		dbarticle.setTiresomeNumber(tiresomeNumber);
		dbarticle.setPraiseNumber(praiseNumber);
		storyArticleDao.update(dbarticle);
		storyArticleDao.deleteContentById(dbarticle.getArticleId());
		if (StringUtils.isNotBlank(story.getArticleContent())) {
			StoryArticleDesc desc = new StoryArticleDesc();
			desc.setArticleId(dbarticle.getArticleId());
			desc.setArticleContent(story.getArticleContent());
			storyArticleDao.insert(desc);
		}
		return story;
	}

	public void updateStoryArticleDTOStatus(int storyId,
			StoryArticleStatus status, AdminUser adminUser) throws SQLException {

		StoryArticle dbstory = (StoryArticle) storyArticleDao.selectById(storyId,
				StoryArticle.class);
		dbstory.setStatus(status);
		dbstory.setUpdateTime(new Date());
		dbstory.setUpdateUserId(adminUser.getId());
		storyArticleDao.update(dbstory);
	}

	public StoryArticleDTO getStoryArticleDTOById(int articleId)
			throws SQLException {
		StoryArticleDTO targetObj = new StoryArticleDTO();
		StoryArticle story = (StoryArticle) storyArticleDao.selectById(
				articleId, StoryArticle.class);
		StoryArticleDesc storyDesc = storyArticleDao
				.selectStoryArticleDescById(articleId);
		BeanUtils.copyProperties(story, targetObj);
		if (storyDesc != null) {
			targetObj.setArticleContent(storyDesc.getArticleContent());
		}

		return targetObj;
	}

	public ListPager doPage4Admin(int pageno, int pagesize, String title,
			StoryArticleStatus status) throws Exception {

		return storyArticleDao.doPageFromDb(pageno, pagesize, title, status);
	}

	public ListPager doPage4Site(int pageno, int pagesize, String title,
			StoryArticleStatus status) throws Exception {
		return storyArticleDao.doPageFromDb(pageno, pagesize, title, status);
	}

	public void addHitTimes(int storyId) throws Exception {
		StoryArticle story = (StoryArticle) storyArticleDao.selectById(storyId,
				StoryArticle.class);
		story.setHitTimes(story.getHitTimes() + 1);
		storyArticleDao.update(story);
	}

	public void addPraiseNumber(int articleId, Integer uid) throws Exception {
		StoryArticle story = (StoryArticle) storyArticleDao.selectById(
				articleId, StoryArticle.class);
		story.setPraiseNumber(story.getPraiseNumber() + 1);
		storyArticleDao.update(story);
	}

	public void addTiresomeNumber(int storyId, Integer uid) throws Exception {
		StoryArticle story = (StoryArticle) storyArticleDao.selectById(storyId,
				StoryArticle.class);
		story.setTiresomeNumber(story.getTiresomeNumber() + 1);
		storyArticleDao.update(story);	
	}

}
