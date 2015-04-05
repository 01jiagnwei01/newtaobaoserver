package com.gxkj.common.listeners;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.red5.server.net.rtmp.RTMPMinaTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gxkj.common.util.ContextUtil;
import com.gxkj.common.util.SystemGlobals;
 

public class ConfigInitializer implements ServletContextListener {
	
	protected final  Logger logger = LoggerFactory.getLogger(getClass());   

	public void contextDestroyed(ServletContextEvent event) {
		
		
		 RTMPMinaTransport rtmpMinaTransport = (RTMPMinaTransport) ContextUtil.getBean("rtmpTransport");
		 if(rtmpMinaTransport!=null){
			 logger.info("开始关闭 rtmp端口服务");
			 rtmpMinaTransport.stop();
			 logger.info(" rtmp端口服务已关闭");
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
