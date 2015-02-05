package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.enums.MailSenderRefAddressListStatus;

public interface MailSenderRefAddressListService {

	
	ListPager doPage(int pageno, int pagesize, Integer taskId,String email, MailSenderRefAddressListStatus status) throws SQLException;
}
