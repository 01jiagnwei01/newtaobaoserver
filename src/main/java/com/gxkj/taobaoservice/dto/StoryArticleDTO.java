package com.gxkj.taobaoservice.dto;

import javax.persistence.Transient;

import com.gxkj.taobaoservice.entitys.StoryArticle;

public class StoryArticleDTO extends StoryArticle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10866526872676526L;
	
	@Transient
	private String  articleContent;

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	
	

}
