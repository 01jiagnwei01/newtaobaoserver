package com.gxkj.taobaoservice.daos.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gxkj.common.dao.BaseDAOImpl;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.daos.MailAddressListDao;
import com.gxkj.taobaoservice.entitys.MailAddressList;
import com.gxkj.taobaoservice.enums.MailAddressListStatus;
@Repository
public class MailAddressListDaoImpl extends BaseDAOImpl implements
		MailAddressListDao {

	private final String emailIsExistHql = "from MailAddressList where email =?";
	
	public ListPager doPage(int pageno, int pagesize, String name,
			String email, MailAddressListStatus status) throws SQLException {
		
		String hql =  " select mail_address_list.* ,admin_user.name as createUserName  from mail_address_list left join admin_user on  mail_address_list.create_user_id = admin_user.id  where  ";
		Map<String,Object > params = new HashMap<String,Object > ();
		boolean  haveParam = false;
		if(StringUtils.isNotBlank(name)){
			haveParam = true;
			 hql += "  mail_address_list.name like :name";
			 params.put("name", "%"+name+"%");
		}
		if(StringUtils.isNotBlank(email)){
			if(haveParam){
				 hql += " and ";
			}
			haveParam = true;
			 hql += "  mail_address_list.email like :email";
			 params.put("email", "%"+email+"%");
		}
		if(status != null){
			if(haveParam){
				 hql += " and ";
			}
			haveParam = true;
			 hql += "   mail_address_list.status = :status";
			 params.put("status", status.name());
		}
		if(!haveParam){
			hql = hql.replace("where", "");
		}
		ListPager pager = new ListPager();
		pager.setPageNo(pageno);
		pager.setRowsPerPage(pagesize );
		pager =  this.selectPageBySQL(hql, params, MailAddressList.class, pager);
		return pager;
	}


	public boolean emailIsExist(String email) throws SQLException {
		MailAddressList address =  (MailAddressList) this.selectOneByHQL(emailIsExistHql, new Object[]{email});
		if(address == null){
			return false;
		}else{
			return true;
		}
		
	}

}
