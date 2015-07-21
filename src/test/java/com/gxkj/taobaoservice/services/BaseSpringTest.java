package com.gxkj.taobaoservice.services;


import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-*.xml","file:WebContent/WEB-INF/mvc-dispatcher-servlet.xml" })
@ActiveProfiles("test")
public class BaseSpringTest {//extends AbstractTransactionalJUnit4SpringContextTests
	private  static Logger logger = LoggerFactory.getLogger(BaseSpringTest.class);
	
	@BeforeClass
	public  static void init(){
		logger.info("执行顺序：{}","@BeforeClass"); 
	}
//	@Test
//	public void test() throws SQLException{
//		System.out.println("test");
//	}

}
