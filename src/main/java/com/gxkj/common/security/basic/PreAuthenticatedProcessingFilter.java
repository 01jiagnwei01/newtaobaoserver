package com.gxkj.common.security.basic;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;

import com.gxkj.common.security.constants.ThreadConsts;

 



public class PreAuthenticatedProcessingFilter extends GenericFilterBean {
    private static Logger log = Logger
            .getLogger(PreAuthenticatedProcessingFilter.class);
        
        private AuthenticationEntryPoint authenticationEntryPoint;

        public void doFilter(ServletRequest request, ServletResponse response,
                FilterChain chain) throws IOException, ServletException {
            HttpServletRequest req = (HttpServletRequest) request;

            Object flag =  req.getServletContext().getAttribute(
                ThreadConsts.LOGIN_FLAG);
            PreAuthenticatedProcessingFilter.log.debug(req.getContextPath() + ", "
                + ThreadConsts.LOGIN_FLAG + ": " + flag);

            if (flag == null || !(Boolean) flag) {
                req.getServletContext().setAttribute(ThreadConsts.LOGIN_FLAG, true);
                this.authenticationEntryPoint.commence(
                    (HttpServletRequest) request, (HttpServletResponse) response,
                    new CredentialsExpiredException("Please login"));
            } else {
                chain.doFilter(request, response);
            }
        }

		public AuthenticationEntryPoint getAuthenticationEntryPoint() {
			return authenticationEntryPoint;
		}

		public void setAuthenticationEntryPoint(
				AuthenticationEntryPoint authenticationEntryPoint) {
			this.authenticationEntryPoint = authenticationEntryPoint;
		}
        
        
    }