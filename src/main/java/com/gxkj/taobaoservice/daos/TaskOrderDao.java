package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;
import java.util.Date;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.TaskOrderStatus;

public interface TaskOrderDao extends BaseDAO {

	/**
	 * 查看用户发布的订单，供前台用户使用
	 * @param userBase
	 * @param pageno
	 * @param pagesize
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	ListPager doPageForSite(UserBase userBase, int pageno, int pagesize,
			Date startTime, Date endTime)throws SQLException;

	/**
	 * 后台分页查看订单
	 * @param pageno
	 * @param pagesize
	 * @param name
	 * @param status
	 * @param userId
	 * @param beginTime
	 * @param endTime
	 * @param taobao
	 * @param qq
	 * @return
	 * @throws SQLException
	 */
	ListPager doPageForAdmin(int pageno, int pagesize, String product_title,
			TaskOrderStatus status, Integer userId, Date beginTime, Date endTime,String taobao,String qq)throws SQLException;

}
