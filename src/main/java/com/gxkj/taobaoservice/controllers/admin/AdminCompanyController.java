package com.gxkj.taobaoservice.controllers.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gxkj.taobaoservice.entitys.CompanyAccount;
import com.gxkj.taobaoservice.services.CompanyAccountService;

@Controller
@RequestMapping("/admin/company_account")
public class AdminCompanyController {

	@Autowired
	private CompanyAccountService companyAccountService;
	
	@RequestMapping(value="/get")
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws SQLException{
		String mv = "admin/company_account_get";
		CompanyAccount companyAccount = companyAccountService.getCompanyAccount();
		modelMap.put("companyAccount", companyAccount);
		return mv;
	}
}
