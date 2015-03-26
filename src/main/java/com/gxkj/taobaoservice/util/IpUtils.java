package com.gxkj.taobaoservice.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtils {
	 public static String getIpAddr(HttpServletRequest request) {      

	        String ip = request.getHeader("x-forwarded-for"); 
	        /**
	         *  如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	         *  答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	         */
		       if(ip !=null && ip.contains(",")){
		        	String[] ips = ip.split(",");
		        	for(int i=0,l=ips.length;i<l;i++){
		        		String ip_temp = ips[i];
		        		if(!ip_temp.equals("unknown")){
		        			ip = ip_temp;
		        			break;
		        		}
		        	}
		        }

	            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     

	                ip = request.getHeader("Proxy-Client-IP");      

	            }     

	            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     

	                ip = request.getHeader("WL-Proxy-Client-IP");     

	            }     

	            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     

	                ip = request.getRemoteAddr();      

	            }   

	       return ip;     

	    }  
}
