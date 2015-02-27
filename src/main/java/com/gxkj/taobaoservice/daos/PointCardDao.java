package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;
import java.util.List;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.taobaoservice.entitys.PointCard;

public interface PointCardDao extends BaseDAO {

	List<PointCard> getAllEnablePointCard() throws SQLException;

}
