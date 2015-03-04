package com.gxkj.taobaoservice.daos.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.taobaoservice.daos.CompanyAccountDao;
import com.gxkj.taobaoservice.entitys.CompanyAccount;
import com.gxkj.taobaoservice.enums.CompanyAccountReason;
@Repository
public class CompanyAccountDaoImpl extends BaseDAOImpl implements
		CompanyAccountDao {

	 
	public void executeUpdateCompanyAccount(BigDecimal sellPoint,
			BigDecimal sellPointsMoney, BigDecimal getPoints,
			BigDecimal supplyPoints, BigDecimal depositMoney,
			BigDecimal drawMoney,CompanyAccountReason reasonType,Integer refId) throws SQLException {
		String hql = "from CompanyAccount order by id  desc ";
		List<CompanyAccount> companyAccounts =  (List<CompanyAccount>) super.selectPageByHQL(hql, new Object[0], 0, 1);
		Date now = new Date();
		CompanyAccount companyAccount = new CompanyAccount();
		companyAccount.setReasonType(reasonType);
		companyAccount.setRefId(refId);
		if(CollectionUtils.isEmpty(companyAccounts)){
		 
			companyAccount.setCreateTime(now);
			companyAccount.setDepositMoney(depositMoney==null?BigDecimal.ZERO:depositMoney);
			companyAccount.setDrawMoney(drawMoney==null?BigDecimal.ZERO:drawMoney);
			companyAccount.setGetPoints(getPoints==null?BigDecimal.ZERO:getPoints);
			companyAccount.setSellPoint(sellPoint==null?BigDecimal.ZERO:sellPoint);
			companyAccount.setSellPointsMoney(sellPointsMoney==null?BigDecimal.ZERO:sellPointsMoney);
			companyAccount.setSupplyPoints(supplyPoints==null?BigDecimal.ZERO:supplyPoints);
		}else{
			CompanyAccount oldCompanyAccount = companyAccounts.get(0);
			companyAccount.setCreateTime(now);
			companyAccount.setDepositMoney(oldCompanyAccount.getDepositMoney().add(depositMoney==null?BigDecimal.ZERO:depositMoney) );
			companyAccount.setDrawMoney( oldCompanyAccount.getDrawMoney().add( drawMoney==null?BigDecimal.ZERO:drawMoney));
			companyAccount.setGetPoints(oldCompanyAccount.getGetPoints().add( getPoints==null?BigDecimal.ZERO:getPoints));
			companyAccount.setSellPoint(oldCompanyAccount.getSellPoint().add(  sellPoint==null?BigDecimal.ZERO:sellPoint));
			companyAccount.setSellPointsMoney(oldCompanyAccount.getSellPointsMoney().add( sellPointsMoney==null?BigDecimal.ZERO:sellPointsMoney));
			companyAccount.setSupplyPoints(oldCompanyAccount.getSupplyPoints().add(  supplyPoints==null?BigDecimal.ZERO:supplyPoints));
		}
		this.insert(companyAccount);  
		
	}

}
