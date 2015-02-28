package com.gxkj.taobaoservice.services.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.taobaoservice.daos.SubTaskInfoDao;
import com.gxkj.taobaoservice.entitys.SubTaskInfo;

@Service
public class DBDataInitService {

	@Autowired
	private SubTaskInfoDao subTaskInfoDao;
	
	void afterPropertiesSet() throws Exception{
		List<SubTaskInfo> subTaskInfos =  subTaskInfoDao.getAllEnableSubTaskInfo();
		System.out.println("查到subTaskInfos的长度为"+subTaskInfos.size());
	}
}
