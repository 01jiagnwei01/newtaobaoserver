package com.gxkj.test;


import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gxkj.common.util.SystemGlobals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-*.xml","file:WebContent/WEB-INF/mvc-dispatcher-servlet.xml" })
public class BaseSpringTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@BeforeClass
	public  static void init(){
		SystemGlobals.loadConfig("SystemGlobals.properties");
		System.out.println(SystemGlobals.getPreference("system.index.url"));
	}

}
