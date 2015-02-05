package com.gxkj.taobaoservice.sms;

import groovy.json.JsonOutput;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.dto.SmsResponse;
import com.gxkj.test.BaseSpringTest;

public class SmsServiceImplTest   extends BaseSpringTest{

	@Autowired
	@Qualifier("F1SmsServiceImpl")
	private SmsService smsService;
	
	@Test
	public void sendSmsTest() throws BusinessException{
		
		String content = "亲爱的老婆，这是我将要做的刷钻平台的发短信部分。给你发个短信测试一下。小小惊喜。";
		String mobiles = "15210578828";
		Date sendTime = null;
		SmsResponse resonse = smsService.sendSms(content, mobiles, sendTime);
		System.out.println(JsonOutput.toJson(resonse));
		
	}
}
