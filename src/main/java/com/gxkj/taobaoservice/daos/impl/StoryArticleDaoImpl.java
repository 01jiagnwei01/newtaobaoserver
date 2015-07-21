package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.constants.StatusConstant;
import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.StoryArticleDao;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.StoryArticle;
import com.gxkj.taobaoservice.entitys.StoryArticleDesc;
import com.gxkj.taobaoservice.enums.StoryArticleStatus;
@Repository
public class StoryArticleDaoImpl extends BaseDAOImpl implements StoryArticleDao {

	private String  deleteContentById_hql = "delete from StoryArticleDesc where articleId = :articleId";
	
	private String  selectStoryArticleDescById_hql = " from StoryArticleDesc where articleId = :articleId";
	
	private String doPageFromDb_hql = "from StoryArticle  ";
	
	public void deleteContentById(Integer articleId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("articleId", articleId);
		this.executeUpdateByHql(deleteContentById_hql, parameter);
		
	}

	 
	public StoryArticleDesc selectStoryArticleDescById(int articleId) throws SQLException {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("articleId", articleId);
		return (StoryArticleDesc) this.selectOneByHQL(selectStoryArticleDescById_hql, parameter);
	}


	 
	public ListPager doPageFromDb(int pageno, int pagesize, String title,
			StoryArticleStatus status) throws SQLException {
		
		 StringBuffer sql = new StringBuffer(doPageFromDb_hql);
		 List<Object> parameters = new ArrayList<Object>();
		 if(status != null){
			 sql.append(" status  = ").append(status);
		 } 
		 if(StringUtils.isNotBlank(title )){
			 sql.append(" and articleTitle like ?");
			 parameters.add("%"+title+"%");
		 }
		sql.append(" order by  id ");
		ListPager pager = new ListPager();
		pager.setPageNo(pageno);
		pager.setRowsPerPage(pagesize );
		ListPager page = this.selectPageBySQL(sql.toString(), parameters.toArray(),StoryArticle.class,pager);
		return page;
	}

}
