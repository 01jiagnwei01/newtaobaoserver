package com.gxkj.taobaoservice.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gxkj.taobaoservice.entitys.SubTaskInfo;
/**
 * 该类作为存储数据库中的数据的容器
 *
 */
public class SystemDbData {
	
	/**
	 * 基本业务
	 */
	private  static  List<SubTaskInfo> basicSubTaskInfo  = new ArrayList<SubTaskInfo>();
	
	/**
	 * 增值业务
	 */
	private  static List<SubTaskInfo> appreciationSubTaskInfo  = new ArrayList<SubTaskInfo>();
	
	/**
	 * 任务类型转成MAP类，方便根据key读取数据
	 */
	public static Map<String,SubTaskInfo> subTaskInfoMap = new HashMap<String,SubTaskInfo>();

	public static List<SubTaskInfo> getBasicSubTaskInfo() {
		return basicSubTaskInfo;
	}

	public static List<SubTaskInfo> getAppreciationSubTaskInfo() {
		return appreciationSubTaskInfo;
	}


	
	

}
