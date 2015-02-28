package com.gxkj.taobaoservice.services.sys;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.taobaoservice.daos.SubTaskInfoDao;
import com.gxkj.taobaoservice.entitys.SubTaskInfo;
import com.gxkj.taobaoservice.enums.SubTaskInfoTypes;
import com.gxkj.taobaoservice.util.SystemDbData;
@Service
public class DBDataInitServiceImpl {

	@Autowired
	private SubTaskInfoDao subTaskInfoDao;
	
	public void getAllEnableSubTaskInfo() throws SQLException {
		
		List<SubTaskInfo>  subTaskInfos = subTaskInfoDao.getAllEnableSubTaskInfo();
		if(CollectionUtils.isNotEmpty(subTaskInfos)){
			for(SubTaskInfo item:subTaskInfos){
				SystemDbData.subTaskInfoMap.put(item.getKey(), item);
				if(item.getType() == SubTaskInfoTypes.BASIC){
					/**
					 * 基本业务
					 */
					SystemDbData.getBasicSubTaskInfo().add(item);
				}else if(item.getType() == SubTaskInfoTypes.APPRECIATION){
					/**
					 * 增值业务
					 */
					SystemDbData.getAppreciationSubTaskInfo().add(item);
					
				}
			}
		}
		 
	}
}
