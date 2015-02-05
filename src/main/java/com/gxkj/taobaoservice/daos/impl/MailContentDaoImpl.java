package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.MailContentDao;
import com.gxkj.taobaoservice.entitys.MailContent;
import com.gxkj.taobaoservice.enums.MailContentStatus;
@Repository
public class MailContentDaoImpl extends BaseDAOImpl implements MailContentDao {

	 
	public ListPager doPage(int pageno, int pagesize, String title)
			throws SQLException {
		 
		 StringBuffer sql = new StringBuffer("select mail_content.* ,admin_user.name as updateUserName , mail_templete.templete_name as templeteName ");
		 sql.append(" from mail_content  ");
		 sql.append(" left join admin_user on mail_content.updateUserId = admin_user.id  ");
		 sql.append(" left join mail_templete on mail_templete.id = mail_content.templete_id  ");
		 sql.append(" where ");
		 List<Object> parameters = new ArrayList<Object>();
		
		 sql.append(" mail_content.status = ?");
		 parameters.add(MailContentStatus.NORMAL.name());
		
		 if(StringUtils.isNotBlank(title )){
			 sql.append(" and  mail_content.title like ?");
			 parameters.add("%"+title+"%");
		 }
		sql.append(" order by   mail_content.id ");
		ListPager pager = new ListPager();
		pager.setPageNo(pageno);
		pager.setRowsPerPage(pagesize );
		ListPager page = this.selectPageBySQL(sql.toString(), parameters.toArray(),MailContent.class,pager);
		return page;
	}

}
