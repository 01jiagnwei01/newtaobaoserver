package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;
import java.util.Date;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.UserAccountTypes;

public interface UserAccountLogDao extends BaseDAO {

	ListPager doPage(int pageno, int pagesize, Integer userID, Integer adminId,
			UserAccountTypes userAccountTypes, Date beginTime, Date endTime) throws SQLException;
	
	/**
	 *  查询用户指定时间段内的资金变化记录
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
