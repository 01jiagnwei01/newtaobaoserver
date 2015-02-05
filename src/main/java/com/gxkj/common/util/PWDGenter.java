package com.gxkj.common.util;


public class PWDGenter {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String pwd = "x1";
		String s = org.springframework.util.DigestUtils.md5DigestAsHex(pwd.getBytes());
		System.out.println(s);
	}
	public static String generateKen(String key)
	{

		if(key == null)return "";
		else return org.springframework.util.DigestUtils.md5DigestAsHex(key.getBytes());

	}
}
