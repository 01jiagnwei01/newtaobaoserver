package com.gxkj.common.security.filter;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.gxkj.common.security.constants.ThreadConsts;


public class LogoutSuccessfulHandlerImpl implements LogoutSuccessHandler {
    private static Logger logger = Logger
            .getLogger(LogoutSuccessfulHandlerImpl.class);

        public void onLogoutSuccess(HttpServletRequest request,
                HttpServletResponse response, Authentication authentication)
                throws IOException {

        	String[] paths = new String[]{"/ss2/","/ss3/"};
        	  for(String path :paths){
        		  Cookie deleteNewCookie=new Cookie("JSESSIONID",null);
        		  deleteNewCookie.setMaxAge(0); //删除该Cookie
        		  deleteNewCookie.setPath(path);
        		  response.addCookie(deleteNewCookie); 
        	  }
            logger.debug("Logout succeeded");
            request.getServletContext().removeAttribute(ThreadConsts.LOGIN_FLAG);
            response.sendRedirect("");
        }
    }