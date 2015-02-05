package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.PicsDao;
import com.gxkj.taobaoservice.entitys.Pics;
import com.gxkj.taobaoservice.enums.PicStatus;
@Repository
public class PicsDaoImpl extends BaseDAOImpl implements PicsDao {

	 
	public ListPager doPage(int pageno, int pagesize, String name,
			PicStatus status) throws SQLException {
		ListPager pager = new ListPager();
		pager.setPageNo(pageno);
		pager.setRowsPerPage(pagesize );
		
		String sql = "select pics.*,admin_user.name createUserName from pics left join admin_user on admin_user.id = pics.create_user where";
		Map<String,Object> parameterMap = new HashMap<String,Object> ();
		boolean haveParam = false;
		if(StringUtils.isNotBlank(name)){
			sql += "  pics.pic_name like :pic_name ";
			parameterMap.put("pic_name", "%"+name+"%");
			 haveParam = true;
		}
		if(status != null){
			if(haveParam){sql += " and "; }
			sql += "  pics.status = :status ";
			parameterMap.put("status", status.name());
			haveParam = true;
		}
		if(!haveParam){
			sql  = sql.replace("where", "");
		}
		sql += " order by pics.id desc";
		ListPager page = this.selectPageBySQL(sql, parameterMap,Pics.class, pager);
		return page;
	}
	public static void main(String[] args) {
		System.out.println(PicStatus.NORMAL.name());
	}

	 

}
