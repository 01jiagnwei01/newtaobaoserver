package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.MailTempleteDao;
import com.gxkj.taobaoservice.entitys.MailTemplete;
import com.gxkj.taobaoservice.enums.MailTempleteStatus;
@Repository
public class MailTempleteDaoImpl extends BaseDAOImpl implements MailTempleteDao {

	public ListPager doPage(int pageno, int pagesize, String templeteName)
			throws SQLException {
		StringBuffer sql = new StringBuffer("select * from mail_templete where ");
		List<Object> parameters = new ArrayList<Object>();
		sql.append(" status = ?");
		parameters.add(MailTempleteStatus.NORMAL.name());
		 
		 if(StringUtils.isNotBlank(templeteName )){
			 sql.append(" and templete_name like ?");
			 parameters.add("%"+templeteName+"%");
		 }
		sql.append(" order by  id ");
		ListPager pager = new ListPager();
		pager.setPageNo(pageno);
		pager.setRowsPerPage(pagesize );
		ListPager page = this.selectPageBySQL(sql.toString(), parameters.toArray(),MailTemplete.class,pager);
		return page;
	}

}
