package com.gxkj.common.listeners;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gxkj.common.util.SystemGlobals;
 

public class ConfigInitializer implements ServletContextListener {
	
	protected final  Logger logger = LoggerFactory.getLogger(getClass());   

	public void contextDestroyed(ServletContextEvent event) {
		 logger.info("开始关闭 red5server");
		 try{
			 org.red5.server.Shutdown.main(null);
			 logger.info(" red5server已关闭");
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
		 
		   
	}

	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();

	 
		String configFile = context.getInitParameter("SystemGlobalsProperties");
		if (StringUtils.isNotBlank(configFile)) {
		    configFile = configFile.trim();
		    System.out.println(configFile);
		    SystemGlobals.loadConfig(configFile);
		}
	}

}
