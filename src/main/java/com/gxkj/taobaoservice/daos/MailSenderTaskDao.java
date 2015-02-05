package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.enums.MailSenderStatus;

public interface MailSenderTaskDao extends BaseDAO {

	ListPager doPage(int pageno, int pagesize, String title,
			MailSenderStatus status, Integer createUser) throws SQLException;

}
