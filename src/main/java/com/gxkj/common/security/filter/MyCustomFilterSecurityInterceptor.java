package com.gxkj.common.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * 配置过滤器 
 * 自定义的过滤器，在<FILTER_SECURITY_INTERCEPTOR>之前执行
 *
 */
public class MyCustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
	private static final Logger logger = Logger
			.getLogger(MyCustomFilterSecurityInterceptor.class);
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	/**
	 * Method that is actually called by the filter chain. Simply delegates to
	 * the {@link #invoke(FilterInvocation)} method.
	 * 
	 * @param request
	 *            the servlet request
	 * @param response
	 *            the servlet response
	 * @param chain
	 *            the filter chain
	 * 
	 * @throws IOException
	 *             if the filter chain fails
	 * @throws ServletException
	 *             if the filter chain fails
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/**
		 * 创建<org.springframework.security.web.FilterInvocation>对象
		 */
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);

	}

	public void invoke(FilterInvocation fi) throws IOException,
			ServletException {
		/***
		 * 调用父类的<beforeInvocation>方法
		 * 获取token
		 */
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			/**
			 * 继续过滤器链
			 */
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} catch (Exception e) {
			logger.error(e.getStackTrace());
		} finally {
			/**
			 * 调用父类的<afterInvocation>方法
			 * 
			 * */
			super.afterInvocation(token, null);
		}
	}

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource newSource) {
		this.securityMetadataSource = newSource;
	}

	public void destroy() {

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
