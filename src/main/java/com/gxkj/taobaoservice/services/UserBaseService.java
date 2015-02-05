package com.gxkj.taobaoservice.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.dto.RegObjDTO;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.enums.UserBaseStatus;
import com.gxkj.taobaoservice.enums.YANS;

public interface UserBaseService {

	/**
	 * 用户注册
	 * @param regObjDTO
	 * @return
	 * @throws SQLException
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	EntityReturnData addRegUser(RegObjDTO regObjDTO)throws SQLException, JsonGenerationException, JsonMappingException, IOException, AddressException, MessagingException ;

	/**
	 * 用户登陆 
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException 
	 * @throws BusinessException 
	 */
	UserBase doLogin(String username, String password) throws SQLException, BusinessException;
	/**
	 * 分页查看
	 * @param pageno
	 * @param pagesize
	 * @param name
	 * @param status
	 * @param regBeignTime
	 * @param regEndTime
	 * @return
	 * @throws SQLException 
	 */
	public ListPager doPage(int pageno, int pagesize, String name,
			UserBaseStatus status, Date regBeignTime, Date regEndTime,YANS supplyMoneystatus,BigDecimal supplyMoney) throws SQLException;

	/**
	 * 设置公司补助金额(该方法废弃，不使用)
	 * @param userId
	 * @param supplyMoney
	 * @param adminUser  操作人员
	 * @return
	 * @throws SQLException
	 * @throws BusinessException 
	 */
	@Deprecated
	EntityReturnData doSetSupplyMoney(AdminUser adminUser ,Integer userId, BigDecimal supplyMoney) throws SQLException, BusinessException;

	/**
	 * 清空公司对所有已补助的公司的补助支持
	 * @param adminUser  操作人员
	 * @return
	 */
	@Deprecated
	EntityReturnData doClearSupplyMone(AdminUser adminUser) throws SQLException;

	/**
	 * 兑换平台币
	 * @param userBase
	 * @param amount
	 * @return
	 * @throws SQLException
	 * @throws BusinessException 
	 */
	EntityReturnData exchangePublishMoney(UserBase userBase, BigDecimal amount)throws SQLException, BusinessException;

	/**
	 * 设置支持用户点数
	 * @param adminUser
	 * @param userId
	 * @param supplyMoney
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	void doSupplypoint(AdminUser adminUser, Integer userId,
			BigDecimal supplyMoney)throws SQLException, BusinessException;
	
	

}
