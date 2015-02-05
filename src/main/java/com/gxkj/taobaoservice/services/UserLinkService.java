package com.gxkj.taobaoservice.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.entitys.UserLink;
import com.gxkj.taobaoservice.enums.UserLinkActiveResult;
import com.gxkj.taobaoservice.enums.UserLinkTypes;

public interface UserLinkService {

	/**
	 * 激活邮箱 
	 * @param email
	 * @param id
	 * @param endDate
	 * @return
	 * @throws SQLException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws BusinessException
	 */
	UserLinkActiveResult activeEmail(String email,int id,Date endDate)throws SQLException, JsonGenerationException, JsonMappingException, IOException, BusinessException;

	/**
	 * 找回密码 
	 * @throws SQLException 
	 * @throws BusinessException 
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	EntityReturnData doFindBackUserPassword(String email) throws SQLException, BusinessException, AddressException, MessagingException;

	/**
	 * 修改某个用户的联系方式
	 * @param userBase
	 * @param userLinkType
	 * @param value
	 * @throws SQLException 
	 * @throws BusinessException 
	 */
	UserLink updateUserLink(UserBase userBase, UserLinkTypes userLinkType,
			String value) throws SQLException, BusinessException;
}
