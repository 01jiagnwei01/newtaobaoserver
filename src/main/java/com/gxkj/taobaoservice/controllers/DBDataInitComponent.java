package com.gxkj.taobaoservice.controllers;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gxkj.taobaoservice.services.sys.DBDataInitServiceImpl;
/**
 * 系统启动完毕，初始化数据库参数到jvm中
 * @author admin
 *
 */
@Controller
public class DBDataInitComponent implements InitializingBean {

	@Autowired
	private DBDataInitServiceImpl dBDataInitServiceImpl;
	
	public void afterPropertiesSet() throws Exception{//new ArrayList();//
 		dBDataInitServiceImpl.getAllEnableSubTaskInfo();
 	
		
	}
}
