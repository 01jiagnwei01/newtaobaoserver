package com.gxkj.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatchUtil {
	
	public static boolean isEmail(String email){
		String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);     
        Matcher m = p.matcher(email);     
        return m.matches();   
	}
	public static void main(String[] args) {
		String email = "01jaingwei01";
		boolean result = StringMatchUtil.isEmail(email);
		System.out.println("result="+result);
	}

}
