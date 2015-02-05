package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.UserAccountLogDao;
import com.gxkj.taobaoservice.enums.UserAccountTypes;
import com.gxkj.taobaoservice.services.UserAccountLogService;
@Service
public class UserAccountLogServiceImpl implements UserAccountLogService {
	
	private static final Log log = 
			LogFactory.getLog(UserAccountLogServiceImpl.class);

	@Autowired
	private UserAccountLogDao userAccountLogDao;
 
	public ListPager doPage(int pageno, int pagesize, Integer userID,
			Integer adminId, UserAccountTypes userAccountTypes, Date beginTime,
			Date endTime) throws SQLException {
		log.info(String.format("执行分页查询"));
		return userAccountLogDao.doPage(  pageno,   pagesize,   userID,
				  adminId,   userAccountTypes,   beginTime,
				  endTime);
	}

}
