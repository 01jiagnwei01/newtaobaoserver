package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gxkj.taobaoservice.dto.StoryArticleDTO;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.enums.StoryArticleStatus;
import com.gxkj.taobaoservice.enums.StoryTypes;

public class StoryArticleServiceTest extends BaseSpringTest {

	protected final  Logger logger = LoggerFactory.getLogger(getClass());   
	
	@Autowired
	private StoryArticleService storyArticleService;
	
	private static int articleId = 1;
	@Test
	public void testAddStoryArticle() {
		AdminUser adminUser = new AdminUser();
		adminUser.setId(1);
		
		StoryArticleDTO dto = new StoryArticleDTO();
		dto.setArticleTitle("精卫填海");
		dto.setFromBookName("山海经");
		dto.setStorySummary("太阳神的女儿精卫，变成了一只鸟，她每天都衔木填海");
		dto.setStoryTime("远古时期");
		dto.setType(StoryTypes.MYTH);
		dto.setArticleContent("<p>太阳神的女儿精卫，变成了一只鸟，她每天都衔木填海</p>");
		
		 try {
			dto = storyArticleService.addStoryArticle(dto, adminUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 logger.info("{}",com.alibaba.fastjson.JSONObject.toJSONString(dto));
		 articleId = dto.getArticleId();
		 
	}

	@Test
	public void testUpdateStoryArticleDTO() {
		if(articleId==0)return;
		AdminUser adminUser = new AdminUser();
		adminUser.setId(1);
		
		StoryArticleDTO dto = new StoryArticleDTO();
		dto.setArticleId(articleId);
		dto.setArticleTitle("精卫填海3");
		dto.setFromBookName("山海经3");
		dto.setStorySummary("太阳神的女儿精卫，变成了一只鸟，她每天都衔木填海3");
		dto.setStoryTime("远古时期");
		dto.setType(StoryTypes.MYTH);
		dto.setArticleContent("<p>太阳神的女儿精卫，变成了一只鸟，她每天都衔木填海3</p>");
		
		 try {
			dto = storyArticleService.updateStoryArticleDTO(dto, adminUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 logger.info("{}",com.alibaba.fastjson.JSONObject.toJSONString(dto));
	}

	@Test
	public void testSetStoryArticleDTOStatus() {
		if(articleId==0)return;
		AdminUser adminUser = new AdminUser();
		adminUser.setId(1);
		try {
			storyArticleService.updateStoryArticleDTOStatus(articleId, StoryArticleStatus.NORMAL, adminUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetStoryArticleDTOById() {
		if(articleId==0)return;
		StoryArticleDTO dto;
		try {
			dto = storyArticleService.getStoryArticleDTOById(articleId);
			logger.info("{}",com.alibaba.fastjson.JSONObject.toJSONString(dto));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	}

	@Test
	public void testDoPage4Admin() {
	}

	@Test
	public void testDoPage4Site() {
	}
	@Test
	public void addHitTimes() throws Exception {
		storyArticleService.addHitTimes(1);
	}
	@Test
	public void addPraiseNumber() throws Exception {
		storyArticleService.addPraiseNumber(1, 1);
	}
	@Test
	public void addTiresomeNumber() throws Exception {
		storyArticleService.addTiresomeNumber(1,1);
	}
	

}
