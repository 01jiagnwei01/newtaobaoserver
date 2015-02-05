package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.MailSenderTaskDao;
import com.gxkj.taobaoservice.entitys.MailSenderTask;
import com.gxkj.taobaoservice.enums.MailSenderStatus;
@Repository
public class MailSenderTaskDaoImpl extends BaseDAOImpl implements MailSenderTaskDao {

	 
	public ListPager doPage(int pageno, int pagesize, String title,
			MailSenderStatus status, Integer createUser) throws SQLException {
	 
		String sql = "select mail_sender_task.*,admin_user.name as createUserName from mail_sender_task left join admin_user on admin_user.id = mail_sender_task.create_user where ";
		Map<String,Object > params = new HashMap<String,Object > ();
		if( status == null){
			sql += " mail_sender_task.status != :status" ;
			params.put("status", MailSenderStatus.DEL.name());
		}else {
			sql += " mail_sender_task.status  = :status" ;
			params.put("status", status.name());
		}
		if(StringUtils.isNotEmpty(title)){
			sql += " and mail_sender_task.title  = :title" ;
			params.put("title", "%"+title+"%");
		}
		
		if(createUser != null && createUser.intValue() != 0){
			sql += " and mail_sender_task.createUser  = :createUser" ;
			params.put("createUser", createUser);
		}
		ListPager pager = new ListPager();
		pager.setPageNo(pageno);
		pager.setRowsPerPage(pagesize );
		pager =  this.selectPageBySQL(sql, params, MailSenderTask.class, pager);
		return pager;
		
	}

}
