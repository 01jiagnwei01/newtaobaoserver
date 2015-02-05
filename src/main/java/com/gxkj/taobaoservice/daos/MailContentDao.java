package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;

public interface MailContentDao extends BaseDAO {

	ListPager doPage(int pageno, int pagesize, String title)throws SQLException;

}
