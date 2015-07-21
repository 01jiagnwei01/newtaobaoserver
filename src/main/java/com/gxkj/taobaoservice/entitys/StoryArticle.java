package com.gxkj.taobaoservice.entitys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.gxkj.taobaoservice.enums.StoryArticleStatus;
import com.gxkj.taobaoservice.enums.StoryTypes;

@Entity
@Table(name = "story_article")
public class StoryArticle implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2705683426909982218L;

	// 主键 ：@Id    主键生成方式：strategy = "increment"
	//映射表中id这个字段，不能为空，并且是唯一的
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "article_id", unique = true, nullable = false)
	private Integer  articleId;
	 
	@Column(name = "article_title", length = 128)
	private String articleTitle;
	
	@Column(name = "add_time")
	private Date addTime;
	
	@Column(name = "add_user_id")
	private Integer addUserId;
	
	@Column(name = "update_time")
	private Date updateTime;
	
	@Column(name = "update_user_id")
	private Integer updateUserId;
	
	/**
	 *故事发生时间 
	 */
	@Column(name = "story_time")
	private String storyTime;
	/**
	 * 故事简介
	 */
	@Column(name = "story_summary")
	private String storySummary;
	
	/**
	 * 浏览这个故事的人数
	 */
	@Column(name = "hit_times")
	private Integer hitTimes = 0;
	
	/**
	 * 喜欢这个故事的人数
	 */
	@Column(name = "praise_number")
	private Integer praiseNumber = 0;
	
	/**
	 * 讨厌的这个故事的人数
	 */
	@Column(name = "tiresome_number")
	private Integer tiresomeNumber = 0;
	
	/**
	 * 来源与哪本书
	 */
	@Column(name = "from_book_name")
	private String fromBookName;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private  StoryTypes type;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StoryArticleStatus status;

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getStoryTime() {
		return storyTime;
	}

	public void setStoryTime(String storyTime) {
		this.storyTime = storyTime;
	}

	public String getStorySummary() {
		return storySummary;
	}

	public void setStorySummary(String storySummary) {
		this.storySummary = storySummary;
	}

	public Integer getHitTimes() {
		return hitTimes;
	}

	public void setHitTimes(Integer hitTimes) {
		this.hitTimes = hitTimes;
	}

	public Integer getPraiseNumber() {
		return praiseNumber;
	}

	public void setPraiseNumber(Integer praiseNumber) {
		this.praiseNumber = praiseNumber;
	}

	public Integer getTiresomeNumber() {
		return tiresomeNumber;
	}

	public void setTiresomeNumber(Integer tiresomeNumber) {
		this.tiresomeNumber = tiresomeNumber;
	}

	public String getFromBookName() {
		return fromBookName;
	}

	public void setFromBookName(String fromBookName) {
		this.fromBookName = fromBookName;
	}

	public StoryTypes getType() {
		return type;
	}

	public void setType(StoryTypes type) {
		this.type = type;
	}

	public StoryArticleStatus getStatus() {
		return status;
	}

	public void setStatus(StoryArticleStatus status) {
		this.status = status;
	}
}
