package com.gxkj.taobaoservice.services;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.MailAddressList;
import com.gxkj.taobaoservice.enums.MailAddressListStatus;

public interface MailAddressListService {

	ListPager doPage(int pageno, int pagesize, String name, String email,
			MailAddressListStatus status)throws SQLException;
	
	/**
	 * 增加通讯录内容
	 * @param mailAddressList
	 * @param adminUser
	 * @return
	 */
	MailAddressList doAddMailAddressList(MailAddressList mailAddressList,
			AdminUser adminUser)throws SQLException, BindException ;
	
	/**
	 * 修改通讯录内容
	 * @param mailAddressList
	 * @param adminUser
	 * @return
	 */
	MailAddressList doUpdateMailAddressList(MailAddressList mailAddressList,
			AdminUser adminUser)throws SQLException , BindException;
	
	/**
	 * 删除通讯录内容
	 * @param id
	 * @param adminUser
	 * @return
	 */
	void doDelMailAddressList(Integer id, AdminUser adminUser) throws SQLException ;

	/**
	 * 批量导入通讯录
	 * @param excel
	 * @param adminUser
	 * @throws SQLException
	 * @throws BindException
	 */
	void doImport(MultipartFile excel, AdminUser adminUser)throws SQLException ,BusinessException, IOException;

}
