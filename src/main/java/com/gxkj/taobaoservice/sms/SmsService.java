package com.gxkj.taobaoservice.sms;

import java.util.Date;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.dto.SmsResponse;

public interface SmsService {

	/**
	 * 发生短信的接口
	 * @param content  短信内容
	 * @param mobiles	电话号码，多个用英文逗号隔开
	 * @param sendTime	发送时间 ,null表示立即发送,非null则不能晚于当前系统时间
	 * @return
	 */
	 public SmsResponse sendSms(String content,String mobiles,Date sendTime)throws BusinessException;
	 
	 /**
	  * 根据错误编码，返回错误信息
	  * @param code
	  * @return
	  */
	 public String getErrorMsg(String code);
}
