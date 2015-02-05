package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.taobaoservice.daos.CompanyAccountDao;
import com.gxkj.taobaoservice.entitys.CompanyAccount;
import com.gxkj.taobaoservice.services.CompanyAccountService;
@Service
public class CompanyAccountServiceImpl implements CompanyAccountService {

	@Autowired
	private CompanyAccountDao companyAccountDao;
	
	public CompanyAccount getCompanyAccount() throws SQLException{
		return (CompanyAccount) companyAccountDao.selectById(1, CompanyAccount.class);
	}
}
