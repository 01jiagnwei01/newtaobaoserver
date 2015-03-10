package com.gxkj.taobaoservice.daos;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.enums.CompanyAccountReason;

public interface CompanyAccountDao extends BaseDAO {

	/**
	 * 
	 * @param sellPoint
	 * @param sellPointsMoney
	 * @param getPoints
	 * @param supplyPoints
	 * @param depositMoney
	 * @param drawMoney
	 */
	public void executeUpdateCompanyAccount(BigDecimal sellPoint,
			BigDecimal sellPointsMoney, BigDecimal getPoints,
			BigDecimal supplyPoints, BigDecimal depositMoney,
			BigDecimal drawMoney,CompanyAccountReason reasonType,Integer refId)throws SQLException;

	/**
	 * 查询公司账务信息
	 * @param pageno
	 * @param pagesize
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws SQLException
	 */
	public ListPager doPage(int pageno, int pagesize, Date beginTime,
			Date endTime)throws SQLException;
}
