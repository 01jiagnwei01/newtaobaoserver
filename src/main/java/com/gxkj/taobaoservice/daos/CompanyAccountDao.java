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
	 * @param sellPoint			卖出点数
	 * @param sellPointsMoney	卖点收入金额
	 * @param getPoints			收回点数
	 * @param supplyPoints		支持点数
	 * @param depositMoney		存款金额
	 * @param drawMoney			取款金额
	 * @param getMoney			收回服务费
	 * @param reason			资金变化原因
	 */
	public void executeUpdateCompanyAccount(BigDecimal sellPoint,
			BigDecimal sellPointsMoney, BigDecimal getPoints,
			BigDecimal supplyPoints, BigDecimal depositMoney,
			BigDecimal drawMoney,CompanyAccountReason reasonType,Integer refId, BigDecimal getMoney,String reason)throws SQLException;

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
