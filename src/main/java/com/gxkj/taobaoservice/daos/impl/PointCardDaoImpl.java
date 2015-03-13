package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.taobaoservice.daos.PointCardDao;
import com.gxkj.taobaoservice.entitys.PointCard;
import com.gxkj.taobaoservice.enums.PointCardStatus;
@Repository
public class PointCardDaoImpl extends BaseDAOImpl implements PointCardDao {

	 private String getAllEnablePointCardHql = "from PointCard where status = :status order by orders desc ";
	 
	@SuppressWarnings("unchecked")
	public List<PointCard> getAllEnablePointCard() throws SQLException {
		 Map<String,Object> param = new HashMap<String,Object>();
		 param.put("status", PointCardStatus.NORMAL);
		return (List<PointCard>) this.selectByHQL(getAllEnablePointCardHql, param);
	}

}
