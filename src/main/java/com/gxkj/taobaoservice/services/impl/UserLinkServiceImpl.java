package com.gxkj.taobaoservice.services.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.PWDGenter;
import com.gxkj.taobaoservice.daos.OperateLogDao;
import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.daos.UserLinkDao;
import com.gxkj.taobaoservice.daos.YanZhengMaLogDao;
import com.gxkj.taobaoservice.entitys.OperateLog;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.entitys.UserLink;
import com.gxkj.taobaoservice.entitys.YanzhengmaLog;
import com.gxkj.taobaoservice.enums.OperateTypes;
import com.gxkj.taobaoservice.enums.UserBaseStatus;
import com.gxkj.taobaoservice.enums.UserLinkActiveResult;
import com.gxkj.taobaoservice.enums.UserLinkStatus;
import com.gxkj.taobaoservice.enums.UserLinkTypes;
import com.gxkj.taobaoservice.enums.YanZhengMaLogTranTypes;
import com.gxkj.taobaoservice.enums.YanZhengMaTypes;
import com.gxkj.taobaoservice.services.BusinessExceptionService;
import com.gxkj.taobaoservice.services.UserLinkService;
import com.gxkj.taobaoservice.util.RegexUtils;
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
	
	@Autowired
	private YanZhengMaLogDao yanZhengMaLogDao;
	 
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
	/**
	 * 找回密码
	 * @throws BusinessException 
	 * @throws SQLException 
	 */
	public void doFindBackPassword(String email, String emailCode,
			String yanzhengma, String yanzhengMaInSession, String password, String surePassword) throws BusinessException, SQLException {
		if(StringUtils.isBlank(email)) {
			 throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_BLANK,"email");
		 }
		if(!RegexUtils.isEmail( email)){
			 throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_INVALID,"email");
		}
		if(StringUtils.isBlank(yanzhengma)) {
			throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_IS_BLANK,"yanzhengma");
		}
		if(!yanzhengMaInSession.equals( yanzhengma)) {
			throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_ERROR,"yanzhengma");
		}
		if(StringUtils.isBlank(emailCode)) {
			throw new BusinessException(BusinessExceptionInfos.DBYan_Zheng_MA_IS_BLANK,"dbyanzhengma");
		}
		
		if(StringUtils.isBlank(password)) {
			 throw new BusinessException(BusinessExceptionInfos.PASSWORD_IS_BLANK,"password");
		 }
		
		if(StringUtils.isBlank(surePassword)) {
			 throw new BusinessException(BusinessExceptionInfos.REPASSWORD_IS_BLANK,"rePassword");
		 }
		if(!surePassword.equals(password)){
			 throw new BusinessException(BusinessExceptionInfos.REPASSWORD_NOT_EQUAL_PASSOWRD,"rePassword");
		}
		
		YanzhengmaLog yanzhengmaLog = yanZhengMaLogDao.getRegLogByTypeAndValue(YanZhengMaTypes.email,  YanZhengMaLogTranTypes.FIND_BACK_PASSWORD, email);
		if(yanzhengmaLog == null) {
			throw new BusinessException(BusinessExceptionInfos.DBYan_Zheng_MA_IS_ERROR
					,"dbyanzhengma");
		}
		UserBase userBase = userBaseDao.getUsersByBindEmail(email);
		if(userBase == null){
			 throw new BusinessException(BusinessExceptionInfos.EMAIL_IS_NOT_REG,"email");
		}
		password = PWDGenter.generateKen(password);
		userBase.setPassword(password);
		userBaseDao.update(userBase);
	}

}
