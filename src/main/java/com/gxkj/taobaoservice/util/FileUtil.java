package com.gxkj.taobaoservice.util;

import java.util.Calendar;

import org.apache.commons.lang3.time.DateFormatUtils;

public class FileUtil {
	
	public static final String generateFileNameWithDate(String originalFilename){
		String dateString = DateFormatUtils.format(Calendar.getInstance().getTimeInMillis(), "yyyyMMddHHmmssSS");// 2009-03-20 22:24:30
		String suffix = getFileExtension(originalFilename);
		return dateString+"."+suffix;
	}
	/**
	 * 获取文件扩展名
	 * @param file
	 * @return
	 */
	private static String getFileExtension(String fileName) {
	 
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return "";
		}
	}
	public static void main(String[] args) {
		System.out.println(FileUtil.generateFileNameWithDate("a.jpg"));
	}
}
