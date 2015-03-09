package com.gxkj.taobaoservice.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.common.util.PWDGenter;
import com.gxkj.common.util.StringMatchUtil;
import com.gxkj.taobaoservice.daos.CompanyAccountDao;
import com.gxkj.taobaoservice.daos.OperateLogDao;
import com.gxkj.taobaoservice.daos.UserAccountDao;
import com.gxkj.taobaoservice.daos.UserAccountLogDao;
import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.daos.UserLinkDao;
import com.gxkj.taobaoservice.dto.EntityReturnData;
import com.gxkj.taobaoservice.dto.RegObjDTO;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.BusinessExceptionEntity;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.entitys.UserLink;
import com.gxkj.taobaoservice.enums.RegProcessResult;
import com.gxkj.taobaoservice.enums.UserBaseStatus;
import com.gxkj.taobaoservice.enums.YANS;
import com.gxkj.taobaoservice.services.BusinessExceptionService;
import com.gxkj.taobaoservice.services.UserBaseService;
@Service
public class UserBaseServiceImpl implements UserBaseService {

	private static final Log log = 
			LogFactory.getLog(UserBaseServiceImpl.class);

	@Autowired
	private UserBaseDao userBaseDao;
	
	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private OperateLogDao operateLogDao;
	
	@Autowired
	private UserLinkDao userLinkDao;
	
	@Autowired
	private BusinessExceptionService businessExceptionService;
	
	@Autowired
	private UserAccountLogDao userAccountLogDao;
	
	@Autowired
	private  CompanyAccountDao companyAccountDao;
	
	/**
	 * 1：判断邮箱是否为空，邮箱是否被使用
	 * 2：用户名是否为空，用户名是否被使用过
	 * 3: 密码是否为空，密码与确认密码是否一致，确认密码是否为空
	 * @param regObjDTO
	 * @param ret
	 * @return
	 * @throws SQLException 
	 */
	public boolean checkRegCondition(RegObjDTO regObjDTO,EntityReturnData ret) throws SQLException{
		String userName = regObjDTO.getUserName();
		String email = regObjDTO.getEmail();
		String passowrd = regObjDTO.getPassword();
		String rePassword = regObjDTO.getRePassword();
		if(StringUtils.isBlank(userName)){
			ret.setMsg(RegProcessResult.USER_NAME_BLANK_FAILURE.toString());
			return false;
		}
		if(StringUtils.isBlank(email)){
			ret.setMsg(RegProcessResult.EMAIL_BLANK_FAILURE.toString());
			return false;
		}
		if(!StringMatchUtil.isEmail(email)){
			ret.setMsg(RegProcessResult.EMAIL_ERROR_FAILURE.toString());
			return false;
		}
		if(StringUtils.isBlank(passowrd)){
			ret.setMsg(RegProcessResult.PASSWORD_BLANK_FAILURE.toString());
			return false;
		}
		if(StringUtils.isBlank(rePassword)){
			ret.setMsg(RegProcessResult.REPASSWORD_BLANK_FAILURE.toString());
			return false;
		}
		if (!rePassword.equals(passowrd)){
			ret.setMsg(RegProcessResult.PASSWORD_NOT_EQUAL_REPASSWORD_FAILURE.toString());
			return false;
		}
		
		
		 UserBase  user  =userBaseDao.getUsersByUserName(userName);
		if(user != null){
			ret.setMsg(RegProcessResult.USER_NAME_IS_USED_FAILURE.toString());
			return false;
		}
		List<UserLink> emailLinks =  userLinkDao.getUsersByEmail(email);
		if(CollectionUtils.isNotEmpty(emailLinks)){
			ret.setMsg(RegProcessResult.EMAIL_IS_USED_FAILURE.toString());
			return false;
		}
		return true;
	}

	/**
	 * 用户登陆操作  
	 * @throws SQLException 
	 * @throws BusinessException 
	 */
	public UserBase doLogin(String username, String password,String yanzhengma,String yanzhengMaInSession) throws SQLException, BusinessException {
		 
		if(StringUtils.isBlank(username)){
			throw new BusinessException(BusinessExceptionInfos.USER_NAME_IS_BLANK,"username");
		}else if(StringUtils.isBlank(password)){
			throw new BusinessException(BusinessExceptionInfos.PASSWORD_IS_BLANK,"password");
		}else if(StringUtils.isBlank(yanzhengma)){
			throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_IS_BLANK,"yanzhengma");
		}else if(!yanzhengMaInSession.equalsIgnoreCase(yanzhengma)){
			throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_ERROR,"yanzhengma");
		}
		
		UserBase  userBase =   userBaseDao.getUsersByUserName(username);
		if(userBase == null) {
			throw new BusinessException(BusinessExceptionInfos.USER_NAME_OR_PASSWORD_ERROR,"username");
		}
		password = PWDGenter.generateKen(password);
		if(!userBase.getPassword().equals(password)){
			throw new BusinessException(BusinessExceptionInfos.USER_NAME_OR_PASSWORD_ERROR,"password");
		}
		if(userBase.getStatus() != UserBaseStatus.NORMAL){
			return userBase;
		}
		log.info("test");
		//查看该用户的帐务信息 
		UserAccount uerAccount = userAccountDao.getUserAccountByUserId(userBase.getId());
		if(uerAccount == null){
			
			/**
			 * 记录用户登录异常
			 */
			 BusinessExceptionEntity fentity = businessExceptionService.initBusinessException(this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()
					, BusinessExceptionInfos.NO_USER_ACCOUNT_BY_USERID, "{username:"+username+"}", userBase.getId());
			businessExceptionService.insertEntity(fentity);
			
			throw new BusinessException(BusinessExceptionInfos.NO_USER_ACCOUNT_BY_USERID);
		}
		userBase.setUerAccount(uerAccount);
		 
		return userBase;
	}

	 
	@SuppressWarnings("unchecked")
	public ListPager doPage(int pageno, int pagesize, String name,
			UserBaseStatus status, Date regBeignTime, Date regEndTime,YANS supplyMoneystatus,BigDecimal supplyMoney) throws SQLException {
	 
		ListPager pager =  userBaseDao.doPage(pageno, pagesize, name, status, regBeignTime, regEndTime, supplyMoneystatus, supplyMoney);
		if(pager.getPageData() != null){
			List<UserBase> datas  = (List<UserBase>) pager.getPageData();
			for(UserBase userBase :datas){
				UserAccount uerAccount = userAccountDao.getUserAccountByUserId(userBase.getId());
				userBase.setUerAccount(uerAccount);
			}
			
		}
		
		return pager;
	}

	/**
	 *  设置公司补助金额
	 *  该方法废弃，不使用
	 */
	public EntityReturnData doSetSupplyMoney(AdminUser adminUser,Integer userId,
			BigDecimal supplyMoney) throws SQLException, BusinessException {
		EntityReturnData result = new EntityReturnData();
//		if(BigDecimal.ZERO.compareTo(supplyMoney) >0){
//			throw  new BusinessException(BusinessExceptionInfos.ACCOUNT_CAN_NOT_BE_NEGATIVE);
//		}else if(new BigDecimal("50").compareTo(supplyMoney) <0){
//			throw  new BusinessException(BusinessExceptionInfos.OUT_THE_LARGE_RANGE);
//		} 
//		UserBase userBase = (UserBase) userBaseDao.selectById(userId, UserBase.class);
//		
//		if(userBase == null){
//			throw  new BusinessException(BusinessExceptionInfos.NO_USER_FOUND_BY_ID);
//		} 
//		BigDecimal beforeValue = userBase.getSupplyMoney();
//		 ObjectMapper mapper = new ObjectMapper();
//		userBase.setSupplyMoney(supplyMoney);
//		userBaseDao.update(userBase);
//		
//		UserAccount uerAccount = userAccountDao.getUserAccountByUserId(userBase.getId());
//		List<UserLink> userLinks = userLinkDao.getUsersByUserId(userBase.getId());
//		userBase.setUserLinks(userLinks);
//		userBase.setUerAccount(uerAccount);
//		try{
//			log.info(String.format("update userBaseDao =%s", mapper.writeValueAsString(userBase)));
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		//记录操作日志
//		OperateLog operateLog  = new OperateLog();
//		operateLog.setAfterValue(supplyMoney.toPlainString());
//		operateLog.setBeforeValue(beforeValue.toPlainString());
//		//操作者id
//		operateLog.setUser_id(adminUser.getId());
//		operateLog.setOperateTime(new Date());
//		operateLog.setOperateType(OperateTypes.SET_SUPPLY_MONEY);
//		this.operateLogDao.insert(operateLog);
//		try{
//			log.info(String.format("insert OperateLog =%s", mapper.writeValueAsString(operateLog)));
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		result.setEntity(userBase);
//		result.setResult(true);
		return result;
	}

	 /**
	  * 清空公司对所有已补助的公司的补助支持
	  * 该方法废弃，不使用
	  */
	public EntityReturnData doClearSupplyMone(AdminUser adminUser ) throws SQLException {
//		Date now = new Date();
		EntityReturnData result = new EntityReturnData();
//		List<UserBase> userBases = this.userBaseDao.getAllSupplyUsers();
//		if(CollectionUtils.isNotEmpty(userBases)){
//			for(UserBase userBase :userBases){
//				
//				BigDecimal beforeValue = userBase.getSupplyMoney();
//				
//				userBase.setSupplyMoney(BigDecimal.ZERO);
//				
//				//记录日志
//				OperateLog operateLog  = new OperateLog();
//				operateLog.setAfterValue("0");
//				operateLog.setBeforeValue(beforeValue.toPlainString());
//				//操作者id
//				operateLog.setUser_id(adminUser.getId());
//				operateLog.setOperateTime(now);
//				operateLog.setOperateType(OperateTypes.SET_SUPPLY_MONEY);
//				this.operateLogDao.insert(operateLog);
//				
//			}
//			result.setMsg(userBases.size()+"");
//		}else{
//			result.setMsg( "0");
//		}
//		
//		result.setResult(true);
		return result;
	}


	/**
	 * 设置公司对某个用户赞助点数
	 */
	public void doSupplypoint(AdminUser adminUser, Integer userId,
			BigDecimal supplyPoint) throws SQLException, BusinessException {
		
		
		
		 
	}

	/**
	 * 修改用户密码 
	 */
	public boolean doUpdatePasswordBy(UserBase userBase, String newpassword,
			String repassword, String caozuoma) throws SQLException,
			BusinessException {
		 
		if(StringUtils.isBlank(newpassword)) {
			throw  new BusinessException(BusinessExceptionInfos.PASSWORD_IS_BLANK,"newpassword");
		}
		if(StringUtils.isBlank(repassword)) {
			throw  new BusinessException(BusinessExceptionInfos.REPASSWORD_IS_BLANK,"repassword");
		}
		if(StringUtils.isBlank(caozuoma)) {
			throw  new BusinessException(BusinessExceptionInfos.CAO_ZUO_MA_IS_BLANK,"caozuoma");
		}
		if(!repassword.equals(newpassword )) {
			throw  new BusinessException(BusinessExceptionInfos.REPASSWORD_NOT_EQUAL_PASSOWRD,"repassword");
		}
		String md5caozuoma = PWDGenter.generateKen(caozuoma);
		if( !md5caozuoma.equals(userBase.getCaoZuoMa())) {
			throw  new BusinessException(BusinessExceptionInfos.CAO_ZUO_MA_IS_ERROR,"caozuoma");
		}
		
		//进行修改密码
		String md5passoword = PWDGenter.generateKen(newpassword);
		userBase.setPassword(md5passoword);
		this.userBaseDao.update(userBase);
		return true;
		
	}

}
