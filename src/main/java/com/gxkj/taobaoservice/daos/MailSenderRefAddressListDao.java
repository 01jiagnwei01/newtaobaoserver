package com.gxkj.taobaoservice.daos;

import java.sql.SQLException;
import java.util.List;

import com.gxkj.common.dao.BaseDAO;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.MailSenderRefAddressList;
import com.gxkj.taobaoservice.enums.MailSenderRefAddressListStatus;

public interface MailSenderRefAddressListDao extends BaseDAO {

	/**
	 * 根据任务ID 查询其设置的接收人
	 * @param taskId
	 * @return
	 * @throws SQLException 
	 */
	List<MailSenderRefAddressList> getReceiversByTaskId(int taskId) throws SQLException;

	/**
	 * 根据任务ID分页查看接收人
	 * @param pageno
	 * @param pagesize
	 * @param taskId
	 * @param email
	 * @param status
	 * @return
	 */
	ListPager doPage(int pageno, int pagesize, Integer taskId, String email,
			MailSenderRefAddressListStatus status)throws SQLException;

	/**
	 * 根据任务ID删除相关联系人
	 * @param taskId
	 * @throws SQLException
	 */
	void deleReceiverByTaskId(Integer taskId)throws SQLException;

}
