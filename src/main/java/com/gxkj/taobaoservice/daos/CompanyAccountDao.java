package com.gxkj.taobaoservice.daos;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.gxkj.common.dao.BaseDAO;

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
			BigDecimal drawMoney)throws SQLException;
}
