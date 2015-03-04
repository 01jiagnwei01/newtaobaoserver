package com.gxkj.taobaoservice.services;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.test.BaseSpringTest;

public class TaskOrderServiceImplTest extends BaseSpringTest{

	@Autowired
	private TaskOrderService taskOrderService;
	
	@Test
	public void doapplyTaskOrderByOrderIdAndUserId( ) throws SQLException, BusinessException{
		UserBase userBase = new UserBase();
		userBase.setId(1);
		 Integer orderId = 1;
		taskOrderService.doapplyTaskOrderByOrderIdAndUserId(userBase, orderId);
		
	}
}
