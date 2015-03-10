package com.gxkj.taobaoservice.services;

import java.sql.SQLException;
import java.util.Date;

import com.gxkj.common.util.ListPager;

public interface CompanyAccountService {
	

	public ListPager doPage(int pageno, int pagesize, Date beginTime,
			Date endTime)throws SQLException;

}
