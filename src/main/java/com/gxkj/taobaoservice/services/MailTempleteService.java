package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import org.springframework.validation.BindException;

import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.MailTemplete;

public interface MailTempleteService {
	
	 
	public MailTemplete doAddMailTemplete(MailTemplete entity,AdminUser adminUser) throws SQLException, BindException;
	
	public MailTemplete doUpdateMailTemplete(MailTemplete entity,AdminUser adminUser) throws SQLException, BindException;
	
	public boolean doDelMailTemplete(int id,AdminUser adminUser) throws SQLException;
	
	public MailTemplete getMailTempleteById (int id)throws SQLException;
	
	public ListPager doPage(int pageno, int pagesize, String name ) throws SQLException;

}
