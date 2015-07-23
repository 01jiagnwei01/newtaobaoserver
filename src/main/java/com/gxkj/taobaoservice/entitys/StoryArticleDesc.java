package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "story_article_desc")
public class StoryArticleDesc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5101253869300118131L;

	@GenericGenerator(name = "generator", strategy = "assigned")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "article_id", unique = true, nullable = false)
	private Integer  articleId;
	
	@Lob@Basic(fetch=FetchType.LAZY)
	@Column(name = "article_content",length = 1677721)
	private String  articleContent;

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	
	
}
