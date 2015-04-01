package com.gxkj.taobaoservice.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gxkj.common.util.SessionUtil;
import com.gxkj.taobaoservice.dto.SessionConstant;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.UserBase;

public class LogInterceptor extends HandlerInterceptorAdapter{

	/**
     * 会话ID
     */
    private final static String SESSION_TYPE = "usertype";
    private final static String SESSION_USERID = "userid";
    private final static String SESSION_KEY = "sessionId";
    
	@Override
    public void afterCompletion(HttpServletRequest arg0,
      HttpServletResponse arg1, Object arg2, Exception arg3)
      throws Exception {

		  // 删除
		  MDC. remove(SESSION_TYPE);
		  MDC. remove(SESSION_USERID);
		  MDC. remove(SESSION_KEY);
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
      Object arg2, ModelAndView arg3) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response, Object handler) throws Exception {
		
    	UserBase base = SessionUtil.getSiteUserInSession(request);
    	AdminUser adminUser =  SessionConstant.getAdminUserInSession(request);
    	if(base!=null){
    		MDC. put(SESSION_TYPE, "site");
    		MDC. put(SESSION_USERID, ""+base.getId());
    	}else if(adminUser!=null) {
    		
    		MDC. put(SESSION_TYPE, "admin");
    		MDC. put(SESSION_USERID, ""+adminUser.getId());
    	}else {
    		MDC. put(SESSION_TYPE, "nologin");
    		MDC. put(SESSION_USERID, "0");
    		
    	}
    	String token =  java.util.UUID.randomUUID().toString();
    	MDC. put(SESSION_KEY, token);
		  
		
		  return true;
    }
}
