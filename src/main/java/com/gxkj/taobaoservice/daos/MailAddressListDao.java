package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.enums.MailAddressListStatus;

public interface MailAddressListDao extends BaseDAO {

	ListPager doPage(int pageno, int pagesize, String name, String email,
			MailAddressListStatus status) throws SQLException;

	boolean emailIsExist(String email)throws SQLException;

 
}
