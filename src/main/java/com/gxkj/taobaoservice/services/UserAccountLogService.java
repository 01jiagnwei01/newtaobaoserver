package com.gxkj.taobaoservice.services;

import java.sql.SQLException;
import java.util.Date;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.enums.UserAccountTypes;

public interface UserAccountLogService {

	ListPager doPage(int pageno, int pagesize, Integer userID, Integer adminId,
			UserAccountTypes userAccountTypes, Date beginTime, Date endTime) throws SQLException;

}
