package com.gxkj.taobaoservice.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.daos.PointCardDao;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.PointCard;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.PointCardStatus;
import com.gxkj.taobaoservice.enums.UserAccountTypes;
import com.gxkj.taobaoservice.services.PointCardService;
import com.gxkj.taobaoservice.services.UserAccountService;
@Service
public class PointCardServiceImpl implements PointCardService {
	
	private static final Log log = 
			LogFactory.getLog(PointCardServiceImpl.class);
	
	
	@Autowired
	private PointCardDao pointCardDao;
	
	@Autowired
	private UserAccountService userAccountService;

	 
	public List<PointCard> getAllEnablePointCard() throws SQLException {
		 
		return pointCardDao.getAllEnablePointCard();
	}


	public void doBuyCard(UserBase userBase, Integer cardId)
			throws BusinessException, SQLException {
		 
		PointCard pointCard =  (PointCard) pointCardDao.selectById(cardId, PointCard.class);
		if(pointCard == null){
			log.error(String.format("点卡ID错误，没有查到对应的点卡,cardId=%d",cardId));
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"cardId");
		}
		BigDecimal points = pointCard.getPoints();
		BigDecimal amount = pointCard.getMoney();
		
		userAccountService.updateUserAccount(userBase, amount, BigDecimal.ZERO,points,BigDecimal.ZERO,   UserAccountTypes.BUY_POINTS, null, null);
		
		
	}

	 
	public void doAddPointCard(AdminUser adminUser, PointCard entity) throws SQLException {
		entity.setAdminUserId(adminUser.getId());
		entity.setUpdateTime(new Date());
		entity.setStatus(PointCardStatus.NORMAL);
		pointCardDao.insert(entity);
		
	}

 
	public void doUpdatePointCard(AdminUser adminUser, PointCard entity) throws SQLException {
		entity.setAdminUserId(adminUser.getId());
		entity.setUpdateTime(new Date());
		entity.setStatus(PointCardStatus.NORMAL);
		pointCardDao.update(entity);
		
	}


	public void doDelPointCard(AdminUser adminUser, Integer cardId) throws SQLException {
		PointCard entity = (PointCard) pointCardDao.selectById(cardId, PointCard.class);
		entity.setAdminUserId(adminUser.getId());
		entity.setUpdateTime(new Date());
		entity.setStatus(PointCardStatus.DELERATE);
		pointCardDao.update(entity);
		
	}
}
