package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.CompanyAccountDao;
import com.gxkj.taobaoservice.services.CompanyAccountService;
@Service
public class CompanyAccountServiceImpl implements CompanyAccountService {

	@Autowired
	private CompanyAccountDao companyAccountDao;
	
	 
	public ListPager doPage(int pageno, int pagesize, Date beginTime,
			Date endTime) throws SQLException {
		 
		return companyAccountDao.doPage( pageno,  pagesize,  beginTime,
				 endTime) ;
		 
	}
}
