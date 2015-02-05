package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;

public interface MailTempleteDao extends BaseDAO {

	ListPager doPage(int pageno, int pagesize, String templeteName)throws SQLException;

}
