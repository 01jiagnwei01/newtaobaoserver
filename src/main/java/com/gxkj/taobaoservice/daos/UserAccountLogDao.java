package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;
import java.util.Date;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.enums.UserAccountTypes;

public interface UserAccountLogDao extends BaseDAO {

	ListPager doPage(int pageno, int pagesize, Integer userID, Integer adminId,
			UserAccountTypes userAccountTypes, Date beginTime, Date endTime) throws SQLException;

}
