package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.MailSenderRefAddressListDao;
import com.gxkj.taobaoservice.enums.MailSenderRefAddressListStatus;
import com.gxkj.taobaoservice.services.MailSenderRefAddressListService;
@Service
public class MailSenderRefAddressListServiceImpl implements
		MailSenderRefAddressListService {

	@Autowired
	private MailSenderRefAddressListDao mailSenderRefAddressListDao;
	
 
	public ListPager doPage(int pageno, int pagesize,Integer taskId, String email,
			MailSenderRefAddressListStatus status) throws SQLException {
		return mailSenderRefAddressListDao.doPage( pageno,  pagesize, taskId,  email,
				 status) ;
		 
	}

}
