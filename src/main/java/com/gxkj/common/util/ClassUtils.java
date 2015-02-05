package com.gxkj.common.util;

public class ClassUtils {
	
	public  static String getClassPath(Class<?> clazz){
	 
		String packagePath = clazz.getPackage().getName();
		return String.format("%s.%s", packagePath,clazz.getName());
	}
	public static void main(String[] args) {
		System.out.println(ClassUtils.getClassPath(ClassUtils.class));
		//System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
	}

}
