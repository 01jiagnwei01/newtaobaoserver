package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.taobaoservice.daos.UserAccountDao;
import com.gxkj.taobaoservice.entitys.UserAccount;
@Repository
public class UserAccountDaoImpl extends BaseDAOImpl implements UserAccountDao {

	 
	public UserAccount getUserAccountByUserId(Integer userid) throws SQLException {
		String hql = "from UserAccount where userId = ?";
		return (UserAccount) this.selectOneByHQL(hql, new Object[]{userid});
	}
	 

	 
	

}
