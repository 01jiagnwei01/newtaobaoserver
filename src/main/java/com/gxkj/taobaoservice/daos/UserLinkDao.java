package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;
import java.util.List;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.taobaoservice.entitys.UserLink;
import com.gxkj.taobaoservice.enums.UserLinkTypes;

public interface UserLinkDao extends BaseDAO {

	

	/**
	 * 根据邮箱查询用户信息
	 * @param email
	 * @return
	 */
	List<UserLink> getUsersByEmail(String email)throws SQLException;

	/**
	 * 根据ID和Email查询UserLink
	 * @param id
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	UserLink getUserLinkByIdAndEmail(int id, String email)throws SQLException;

	/**
	 * 根据用户ID,查询用户的联系方式 
	 * @param id
	 * @return
	 */
	List<UserLink> getUsersByUserId(Integer userId)throws SQLException;

	/**
	 * 根据用户ID和查询类型查询用户的联系方式
	 * @param id
	 * @param userLinkType
	 * @return
	 * @throws SQLException
	 */
	UserLink getUserLinkByUserIdAndType(Integer id, UserLinkTypes userLinkType)throws SQLException;
}
