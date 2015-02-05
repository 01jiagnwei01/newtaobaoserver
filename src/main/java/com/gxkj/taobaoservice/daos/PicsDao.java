package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.enums.PicStatus;

public interface PicsDao extends BaseDAO {

	ListPager doPage(int pageno, int pagesize, String name, PicStatus status)throws SQLException;

}
