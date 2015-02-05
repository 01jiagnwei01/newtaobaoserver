package com.gxkj.common.listeners;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;

import com.gxkj.common.util.SystemGlobals;
 

public class ConfigInitializer implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
		  
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
