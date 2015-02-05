package com.gxkj.taobaoservice.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;



public class RegexUtils {
	
	/**
	 * 验证是否是邮箱
	 * @param mail
	 * @return
	 */
	public static boolean isEmail(String mail){
		if(StringUtils.isEmpty(mail)){
			return false;
		}
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(mail);
		return matcher.matches();
	}
	
	/**
	 * 验证是否是手机号码
	 * @param mobiles
	 * @return
	 */
	 public static boolean isMobileNO(String mobiles){     
	        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");     
	        Matcher m = p.matcher(mobiles);     
	        return m.matches();     
	  } 
	 
	 /**
	  * 判断是否是闰年
	  * @param time
	  * @return
	  */
	 public static boolean isLeapYear(Date time){
		String format = "yyyy-MM-dd hh:mm:ss";
		String timsString =  DateFormatUtils.format(time, format);
		Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher matcher = pattern.matcher(timsString);
		 return matcher.matches();
	 }
	 public static void main(String[] args) {
		  System.out.println(RegexUtils.isLeapYear(new Date()));
		 
	}

}
