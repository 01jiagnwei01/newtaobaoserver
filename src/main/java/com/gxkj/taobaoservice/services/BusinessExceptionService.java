package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.taobaoservice.entitys.BusinessExceptionEntity;

public interface BusinessExceptionService {
	
	public void insertEntity(BusinessExceptionEntity entity) throws SQLException;
	
	public BusinessExceptionEntity initBusinessException(Class<?> clazz,String methodName,BusinessExceptionInfos type,String params,Integer userid);

}
