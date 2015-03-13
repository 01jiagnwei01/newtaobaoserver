package com.gxkj.taobaoservice.jmxs;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource (objectName = "bean:name=JMXSEntity", description = "systemBean", log = true, logFile = "jmxSystem.log", currencyTimeLimit = 15, persistPolicy = "OnUpdate", persistPeriod = 200, persistLocation = "foo", persistName = "bar")
public class JMXSEntity {
	
	private static String smsCodeTempletate = "【谷谷道场】您的手机验证码是%s";
	
	private static String smsSign = "谷谷道场";

	@ManagedAttribute( persistPeriod = 300)
	public static String getSmsCodeTempletate() {
		return smsCodeTempletate;
	}

	@ManagedAttribute(description = "sms模板", currencyTimeLimit = 20, defaultValue = "", persistPolicy = "OnUpdate")
	public static void setSmsCodeTempletate(String smsCodeTempletate) {
		JMXSEntity.smsCodeTempletate = smsCodeTempletate;
	}
	@ManagedAttribute( persistPeriod = 300)
	public static String getSmsSign() {
		return smsSign;
	}

	@ManagedAttribute(description = "sms签名", currencyTimeLimit = 20, defaultValue = "", persistPolicy = "OnUpdate")
	public static void setSmsSign(String smsSign) {
		JMXSEntity.smsSign = smsSign;
	} 
	
	

}
