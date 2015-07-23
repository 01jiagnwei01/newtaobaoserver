package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.StoryArticleDao;
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
		 boolean haveParam = false;
		 if(status != null){
			 haveParam = true;
			 sql.append(" where status  = ?");
			 parameters.add(status);
		 }else {
			 haveParam = true;
			 sql.append(" where status  != ?") ;
			 parameters.add(StoryArticleStatus.DEL);
		 }
		 if(StringUtils.isNotBlank(title )){
			 if(haveParam){
				 sql.append(" and "); 
			 }else {
				 sql.append(" where "); 
			 }
			 sql.append("   articleTitle like ?");
			 parameters.add("%"+title+"%");
			 haveParam = true;
		 }
		sql.append(" order by  articleId ");
		ListPager pager = new ListPager();
		pager.setPageNo(pageno);
		pager.setRowsPerPage(pagesize );
		ListPager page = this.selectPageByHql(sql.toString(), parameters.toArray(),pager);
		return page;
	}

}
