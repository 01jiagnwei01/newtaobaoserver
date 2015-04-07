package com.gxkj.common.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.red5.classloading.ClassLoaderBuilder;
import org.red5.server.Bootstrap;
import org.red5.server.jmx.mxbeans.ShutdownMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

 

/**
 * 
 */
public class ContextUtil extends ContextLoaderListener {

	protected final  Logger logger = LoggerFactory.getLogger(getClass());   
	
	/**
	 * spring上下文环境  
	 */
	private static WebApplicationContext applicationContext;

	/**
	 * servlet上下文环境
	 */
	private static ServletContext servletContext;

	public void contextInitialized(ServletContextEvent event) {
		System.setProperty("red5.deployment.type","war");
		File f = new File(ContextUtil.class.getResource("/").getPath());
		System.setProperty("red5.root",f.getAbsolutePath());
		System.setProperty("red5.config_root",f.getAbsolutePath());
//		System.setProperty("java.net.preferIPv4Stack","true");
		
		 
		logger.info("red5.config_root={}",System.getProperty("red5.config_root"));
		
		
		super.contextInitialized(event);
		servletContext = event.getServletContext();
		applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	}

	/**
	 * @return the applicationContext
	 */
	public static WebApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @param applicationContext
	 *            the applicationContext to set
	 */
	public static void setApplicationContext(WebApplicationContext applicationContext) {
		ContextUtil.applicationContext = applicationContext;
	}

	/**
	 * @return the servletContext
	 */
	public static ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * @param servletContext
	 *            the servletContext to set
	 */
	public static void setServletContext(ServletContext servletContext) {
		ContextUtil.servletContext = servletContext;
	}

	public static Object getBean(String beanName) {
		 
		if(applicationContext==null){
			 System.out.println("异常：applicationContext为空");
			return null;
		}
		if(!applicationContext.containsBean(beanName)){
			 System.out.println("异常：applicationContext中没有beanName="+beanName);
				return null;
		}
		 
		return applicationContext.getBean(beanName);
	}
	

	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
//		if(applicationContext.containsBean("rtmpScheduler")){
//			((org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler)applicationContext.getBean("rtmpScheduler")).shutdown();
//		}
//		if(applicationContext.containsBean("rtmptConnection")){
//			((org.red5.server.net.rtmp.RTMPConnection)applicationContext.getBean("rtmptConnection")).getExecutor().shutdown();
//		}
		
//		if(applicationContext.containsBean("schedulingService")){
//			try {
//				((org.red5.server.scheduling.QuartzSchedulingService)applicationContext.getBean("schedulingService")).destroy();
//			} catch (Exception e) {
//				 
//				e.printStackTrace();
//			}
//		}  
	
//		String disName = applicationContext.getDisplayName();
//		System.out.println("disName="+disName);
//		
//		
//		if(applicationContext.containsBean("web.scope")){
//			try {
//				((org.red5.server.scope.Scope)applicationContext.getBean("web.scope")).destroy();
//			} catch (Exception e) {
//				 
//				e.printStackTrace();
//			}
//		} 
//		if(applicationContext.containsBean("global.scope")){
//			try {
//				((org.red5.server.scope.GlobalScope)applicationContext.getBean("global.scope")).destroy();
//			} catch (Exception e) {
//				 
//				e.printStackTrace();
//			}
//		} 
		//org.red5.server.Shutdown.main(null);
		   
	}
	 
	
	 
}
