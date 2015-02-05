package com.gxkj.common.security.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gxkj.common.security.entitys.AdminUserBean;


public class UserDaoImpl   {
	
	protected static Logger logger = Logger.getLogger("dao"); 
	
	public AdminUserBean getUser(String userName) {
		List<AdminUserBean> users = internalDatabase();  
		  
        for (AdminUserBean ub : users) {  
            if (ub.getUserName().equals(userName)) {  
                logger.debug("User found");  
                return ub;  
            }  
        }  
        logger.error("User does not exist!");  
        throw new RuntimeException("User does not exist!");
	}
	
	
	 private List<AdminUserBean> internalDatabase() {  
		  
	        List<AdminUserBean> users = new ArrayList<AdminUserBean>();  
	        AdminUserBean user = null;  
	  
	        //创建用户admin/admin，角色ROLE_ADMIN
	        user = new AdminUserBean();  
	        user.setId(1);
	        user.setUserName("admin");  	  
	        // "admin"经过MD5加密后  
	        user.setPassword("21232f297a57a5a743894a0e4a801fc3");
	         
	        users.add(user);  
	  
	        //创建用户user/user，角色ROLE_USER
	        user = new AdminUserBean();  
	        user.setId(2);
	        user.setUserName("user");  	  
	        // "user"经过MD5加密后  
	        user.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
	        users.add(user);  
	  
	        return users;  
	  
	    }  

}
