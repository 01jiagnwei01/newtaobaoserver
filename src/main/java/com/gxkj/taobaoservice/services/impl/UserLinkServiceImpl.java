package com.gxkj.taobaoservice.services.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.taobaoservice.daos.OperateLogDao;
import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.daos.UserLinkDao;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.entitys.OperateLog;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.entitys.UserLink;
import com.gxkj.taobaoservice.enums.FindBackPasswordProcessResult;
import com.gxkj.taobaoservice.enums.OperateTypes;
import com.gxkj.taobaoservice.enums.UserBaseStatus;
import com.gxkj.taobaoservice.enums.UserLinkActiveResult;
import com.gxkj.taobaoservice.enums.UserLinkStatus;
import com.gxkj.taobaoservice.enums.UserLinkTypes;
import com.gxkj.taobaoservice.services.BusinessExceptionService;
import com.gxkj.taobaoservice.services.UserLinkService;
import com.gxkj.taobaoservice.util.mail.MailSender;
@Service
public class UserLinkServiceImpl implements UserLinkService {

	private static final Log log = 
			LogFactory.getLog(UserLinkServiceImpl.class);
	
	@Autowired
	private UserLinkDao userLinkDao;
	@Autowired
	private UserBaseDao userBaseDao;
	
	@Autowired
	private OperateLogDao operateLogDao;
	
	@Autowired
	private BusinessExceptionService businessExceptionService;
	
	 
	/**
	 * 邮箱激活 
	 */
	public UserLinkActiveResult activeEmail(String email, int id, Date endDate) throws SQLException, JsonGenerationException, JsonMappingException, IOException,BusinessException {
		 
		Date now = new Date();
		if(endDate == null){
			return UserLinkActiveResult.HAVE_PASS_ACTIVE_DATE_FAILER;
		}
		else if(!now.after( endDate)){
			return UserLinkActiveResult.HAVE_PASS_ACTIVE_DATE_FAILER;
		}
		UserLink userLink = userLinkDao.getUserLinkByIdAndEmail(id,email);
		if(userLink == null){
			return UserLinkActiveResult.ID_OR_EMAIL_ERRORE_FAILER;
		}
		 ObjectMapper mapper = new ObjectMapper();  
		 
		userLink.setStatus(UserLinkStatus.NORMAL);
		userLinkDao.update(userLink);
		 
		log.info(String.format("update userLink=%s", mapper.writeValueAsString(userLink)));
		
		int userId = userLink.getUserId();
		UserBase userBase = (UserBase) userBaseDao.selectById(userId, UserBase.class);
		if(userBase == null){
			throw new BusinessException(BusinessExceptionInfos.NO_USER_FOUND_BY_ID);
		}
		userBase.setStatus(UserBaseStatus.NORMAL);
		userBaseDao.update(userBase);
		log.info(String.format("update userBase=%s", mapper.writeValueAsString(userBase)));
		
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(now);
		operateLog.setOperateType(OperateTypes.ACTIVE_EMAIL);
		operateLog.setBeforeValue(userLink.getLinkValue());
		operateLog.setAfterValue(userLink.getLinkValue());
		operateLog.setUser_id(userBase.getId());
		operateLogDao.insert(operateLog);
		 
		return UserLinkActiveResult.SUCCESS;
	}


	 
	public EntityReturnData doFindBackUserPassword(String email) throws SQLException, BusinessException, AddressException, MessagingException {
		EntityReturnData entity = new EntityReturnData();
		List<UserLink> userLinks = this.userLinkDao.getUsersByEmail(email);
		if(CollectionUtils.isEmpty(userLinks)){
			entity.setMsg(FindBackPasswordProcessResult.EMAIL_NOT_EXIT.getName());
			return entity;
		}
		if(userLinks.size()>=2){
			com.gxkj.taobaoservice.entitys.BusinessExceptionEntity fentity = businessExceptionService.initBusinessException(this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()
					, BusinessExceptionInfos.EMAIL_DUPLICATE_EXIST, "{email:"+email+"}", 0);
			businessExceptionService.insertEntity(fentity);
			
			throw new BusinessException(BusinessExceptionInfos.EMAIL_DUPLICATE_EXIST);
		}
		UserLink userLink = userLinks.get(0);
		
		Date now = new Date();
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(now);
		operateLog.setBeforeValue( userLink.getLinkValue());
		operateLog.setOperateType(OperateTypes.FIND_PASSWORD_SEND_EMAIL);
		operateLog.setAfterValue(userLink.getLinkValue());
		operateLog.setUser_id(userLink.getUserId());
		operateLog.setIsUsed(0);
		operateLogDao.insert(operateLog);
		//发送邮件重置密码
		//当天重置 
		String sendtime = DateFormatUtils.format(now, "yyyy-MM-dd HH:mm:ss");
		String maiUrlParams = String.format("sendtime=%s&logid=%d", sendtime,operateLog.getId());
		MailSender mailSender = new MailSender();
		mailSender.findBackPasswordSendEmail(email, "找回密码 ", now, maiUrlParams);
		entity.setResult(true);
		entity.setMsg(FindBackPasswordProcessResult.SUCCESS.getName());
		
		return entity;
	}



	/**
	 * 修改某个用户的联系方式
	 * @throws SQLException 
	 * @throws BusinessException 
	 */
	public UserLink updateUserLink(UserBase userBase, UserLinkTypes userLinkType,
			String value) throws SQLException, BusinessException {
		/**
		 * 不支持邮箱更改
		 */
		if(userLinkType == UserLinkTypes.EMAIL){
			throw new BusinessException(BusinessExceptionInfos.EMAIL_LINNK_CANNOT_CHANGE);
		}
		UserLink userLink = userLinkDao.getUserLinkByUserIdAndType(userBase.getId(),userLinkType);
		boolean isAdd = false;
		
		if(userLink == null){
			userLink = new UserLink();
			isAdd = true;
		}
		String beforvalue = userLink.getLinkValue();
		userLink.setLinkType(userLinkType);
		userLink.setLinkValue(value);
		userLink.setUserId(userBase.getId());
		userLink.setStatus(UserLinkStatus.NORMAL);
		
		if(isAdd){
			userLinkDao.insert(userLink);
		}else{
			userLinkDao.update(userLink);
		}
		
		/**
		 * 添加日志
		 */
		OperateTypes operateType = null;
		switch(userLinkType){
			case  EMAIL :
					break;
			case  QQ :
				operateType = OperateTypes.UPDATE_QQ;
				break;
			case  TELPHONE :
				operateType = OperateTypes.UPDATE_TELPHONE;
				break;
			case  TAOBAO :
				operateType = OperateTypes.UPDATE_TAOBAOXIAOHAO;
				break;
			 
			
		}
		Date now = new Date();
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(now);
		operateLog.setOperateType(operateType);
		operateLog.setBeforeValue(beforvalue);
		operateLog.setAfterValue(userLink.getLinkValue());
		operateLog.setUser_id(userLink.getUserId());
		operateLog.setIsUsed(0);
		operateLogDao.insert(operateLog);
		
		return userLink;
		
	}

}
