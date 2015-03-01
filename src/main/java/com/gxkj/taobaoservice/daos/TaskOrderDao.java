package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;
import java.util.Date;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.UserBase;

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

}
