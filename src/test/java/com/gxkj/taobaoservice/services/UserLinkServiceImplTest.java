package com.gxkj.taobaoservice.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.enums.UserLinkActiveResult;
import com.gxkj.test.BaseSpringTest;

public class UserLinkServiceImplTest extends BaseSpringTest{

	@Autowired
	private UserLinkService userLinkService;
	
	@Test
	public void activeEmail()
	{
		UserLinkActiveResult result = null;
		Date now = new Date();
		now = DateUtils.addDays(now,  4);
		try {
			result = userLinkService.activeEmail("01jiangwei01@163.com", 1, now);
			System.out.println("result="+result.getName().toString());
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
