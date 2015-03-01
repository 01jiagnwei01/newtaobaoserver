package com.gxkj.taobaoservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.taobaoservice.daos.TaskBasicDao;
import com.gxkj.taobaoservice.daos.TaskBasicLogDao;
import com.gxkj.taobaoservice.services.TaskBasicService;
@Service
public class TaskBasicServiceImpl implements TaskBasicService {
	
	@Autowired
	private TaskBasicDao taskBasicDao;
	
	@Autowired
	private TaskBasicLogDao taskBasicLogDao;

}
