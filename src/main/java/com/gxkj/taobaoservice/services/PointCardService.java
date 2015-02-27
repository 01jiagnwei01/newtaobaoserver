package com.gxkj.taobaoservice.services;

import java.sql.SQLException;
import java.util.List;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.PointCard;
import com.gxkj.taobaoservice.entitys.UserBase;

public interface PointCardService {
	
	/**
	 * 查询所有可用的点卡
	 * @return
	 * @throws SQLException 
	 */
	public List<PointCard> getAllEnablePointCard() throws SQLException;
	
	/**
	 * 用户购买点卡操作
	 * @param userBase
	 * @param cardId
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public void doBuyCard(UserBase userBase, Integer cardId)throws BusinessException,SQLException;

	/**
	 * 增加点卡
	 * @param adminUser
	 * @param entity
	 * @throws SQLException 
	 */
	public void doAddPointCard(AdminUser adminUser,PointCard entity) throws SQLException;
	/**
	 * 修改点卡
	 * @param adminUser
	 * @param entity
	 */
	public void doUpdatePointCard(AdminUser adminUser,PointCard entity)throws SQLException;
	/**
	 * 删除点卡
	 * @param adminUser
	 * @param entity
	 */
	public void doDelPointCard(AdminUser adminUser, Integer cardId)throws SQLException;

}
