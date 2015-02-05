package com.gxkj.common.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

public class CookieUtils {
	
    public static Map<String,Cookie> toMap(Cookie[] cookies){
    	if(cookies == null || cookies.length == 0)
    		return new HashMap<String,Cookie>(0);
    	
    	Map<String,Cookie> map = new HashMap<String,Cookie>(cookies.length * 2);
    	for(Cookie c : cookies) {
    		map.put(c.getName(), c);
    	}
    	return map;
    }

}
