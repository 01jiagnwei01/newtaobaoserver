package com.gxkj.taobaoservice.filters;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
//import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;



import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gxkj.common.annotation.WithoutAuthorize;
import com.gxkj.common.util.SystemGlobals;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.dto.SessionConstant;
import com.gxkj.taobaoservice.entitys.AdminUser;

 
 

public class AuthorizationInterceptor extends HandlerInterceptorAdapter  {

	 
	/**
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(ex!=null)
		ex.printStackTrace();
		 super.afterCompletion(request, response, handler, ex) ;  
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		 
		super.postHandle(arg0, arg1, arg2, arg3) ;  
	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse response,
			Object handler) throws Exception {
		MethodNameResolver methodNameResolver = new InternalPathMethodNameResolver();
		System.out.println("methodName="+methodNameResolver.getHandlerMethodName(req));
		System.out.println("权限拦截---------------");
		
//		HandlerMethod handlerMethod = (HandlerMethod) handler;        
//		Method method = handlerMethod.getMethod();
		
		String url = req.getRequestURI();
		String ctx = req.getContextPath();
		url = url.replace(ctx, "");
		Enumeration<String> keys = req.getParameterNames();
		while(keys.hasMoreElements()) {
		    String k = keys.nextElement();
		    System.out.println(k + " = " + req.getParameter(k) );
		} 
		
		Map pathVariables = (Map) req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		if(pathVariables!=null){
			Set set = pathVariables.keySet();
			Iterator it =set.iterator();
			while(it.hasNext()){
				System.out.println(it.next());
			}
		}
		
		
		
		if(handler !=null  ){
			Class<? extends Object> clazz =  handler.getClass();
			if(clazz.isAnnotationPresent(WithoutAuthorize.class)){
				return true;
			} 
		}
		if(url.indexOf("/exception")>=0){
			return true;
			
		}
		if(url.indexOf("/admin")>=0){
			//管理员过滤
			if(url.indexOf("login")>=0 || url.indexOf("dologin")>=0 || url.indexOf("logout")>=0){
				return true;
			}
			AdminUser user = SessionConstant.getAdminUserInSession(req);
			if(user == null){
				//无权限
				response.sendRedirect(req.getContextPath() +"/admin/login");
				return false;
			}
			if(SystemGlobals.getPreference("system.admin.allow.urls").indexOf(","+url+",")>=0){
				return true;
			}
			 
			String authPath = user.getMenupaths();
			if(authPath.indexOf(";"+url+";")>=0){
				return true;
			}
			String requestType = req.getHeader("X-Requested-With");
			if(StringUtils.isNotBlank(requestType )){
				//是ajax请求，返回json对象
				ObjectMapper mapper = new ObjectMapper();
            	EntityReturnData json = new EntityReturnData();
            	json.setMsg("您没有权限"); 
				 response.setContentType("text/javascript;charset=UTF-8");   
				 PrintWriter writer = response.getWriter();   
				 writer.write(mapper.writeValueAsString(json));  
				
			} else{
				response.sendRedirect(req.getContextPath()+"/exception/noauth");
			}
		}else {
			//前台来的
			return true;
		}
		
		
		return false;
	}

}
