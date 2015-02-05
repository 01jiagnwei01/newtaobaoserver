package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.MailSenderRefAddressListDao;
import com.gxkj.taobaoservice.entitys.MailSenderRefAddressList;
import com.gxkj.taobaoservice.enums.MailSenderRefAddressListStatus;
@Repository
public class MailSenderRefAddressListDaoImpl extends BaseDAOImpl implements
		MailSenderRefAddressListDao {

	private String getReceiversByTaskIdSql = "from MailSenderRefAddressList where mailSenderTaskId=:taskId order by id";
	
	@SuppressWarnings("unchecked")
	public List<MailSenderRefAddressList> getReceiversByTaskId(int taskId) throws SQLException {
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("taskId", taskId);
		return ((List<MailSenderRefAddressList>) this.selectByHQL(getReceiversByTaskIdSql, parameters));
	}

	 
	public ListPager doPage(int pageno, int pagesize, Integer taskId,
			String email, MailSenderRefAddressListStatus status) throws SQLException {
		StringBuffer hql = new StringBuffer("from MailSenderRefAddressList ");
		hql.append(" where mailSenderTaskId=:taskId");
		Map<String,Object > params = new HashMap<String,Object > ();
		params.put("taskId", taskId);
		if(StringUtils.isNotBlank(email )){
			 hql.append(" and  email = :email");
			 params.put("email", email);
		 }
		 
		 if(status != null){
			 hql.append(" and  status = :status");
			 params.put("status", status);
		 }
		 
		 hql.append(" order by    id ");
		ListPager pager = new ListPager();
		pager.setPageNo(pageno);
		pager.setRowsPerPage(pagesize );
		ListPager page = this.selectPageByHql(hql.toString(), params, pager);
		return page;
	}


 
	public void deleReceiverByTaskId(Integer taskId) throws SQLException {
		String sql = "delete from mail_sender_ref_address_list where mail_sender_task_id = ?";
		 this.executeUpdateBySql(sql, new Object[]{taskId});
		
	}

}
