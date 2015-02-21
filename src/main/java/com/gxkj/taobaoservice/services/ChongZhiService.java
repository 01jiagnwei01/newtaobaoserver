package com.gxkj.taobaoservice.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.validation.BindException;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.UserBase;

public interface ChongZhiService {
	
	/**
	 * 非即时充值操申请操作
	 * @param userBase
	 * @param amount
	 * @param orderNo
	 * @throws BusinessException
	 * @throws SQLException
	 * @throws BindException
	 */
	public void doChongZhiForNoInstant(UserBase userBase,BigDecimal amount,String orderNo) throws BusinessException, SQLException, BindException;

	/**
	 * 分页显示充值记录
	 * @param pageno
	 * @param pagesize
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws SQLException 
	 */
	public ListPager doPage(UserBase userBase,int pageno, int pagesize, Date startTime,
			Date endTime) throws SQLException;
}
