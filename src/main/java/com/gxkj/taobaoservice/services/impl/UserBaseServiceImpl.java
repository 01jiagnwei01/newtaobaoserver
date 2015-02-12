package com.gxkj.taobaoservice.services.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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
import com.gxkj.taobaoservice.entitys.CompanyAccount;
import com.gxkj.taobaoservice.entitys.OperateLog;
import com.gxkj.taobaoservice.entitys.UserAccount;
import com.gxkj.taobaoservice.entitys.UserAccountLog;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.entitys.UserLink;
import com.gxkj.taobaoservice.enums.OperateTypes;
import com.gxkj.taobaoservice.enums.RegProcessResult;
import com.gxkj.taobaoservice.enums.UserAccountTypes;
import com.gxkj.taobaoservice.enums.UserBaseStatus;
import com.gxkj.taobaoservice.enums.UserLinkStatus;
import com.gxkj.taobaoservice.enums.UserLinkTypes;
import com.gxkj.taobaoservice.enums.YANS;
import com.gxkj.taobaoservice.services.BusinessExceptionService;
import com.gxkj.taobaoservice.services.UserBaseService;
import com.gxkj.taobaoservice.util.mail.MailSender;
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
	 * 前台用户注册接口
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public EntityReturnData addRegUser(RegObjDTO regObjDTO) throws SQLException, JsonGenerationException, JsonMappingException, IOException, AddressException, MessagingException  {
		
		EntityReturnData ret = new EntityReturnData();
		 ObjectMapper mapper = new ObjectMapper();  
		 
		boolean checkResult =  checkRegCondition(regObjDTO,ret);
		log.info(String.format("checkRegCondition checkResult=%s", checkResult));
		/**
		 * 验证不通过把验证结果返回
		 */
		if(!checkResult) return ret;
		Date now = new Date();
		/**
		 * 验证通过，数据入库
		 */
		UserBase userBase = new UserBase();
		userBase.setPassword(PWDGenter.generateKen(regObjDTO.getPassword()) );
		userBase.setUserName(regObjDTO.getUserName());
		userBase.setRegTime(now);
		userBase.setStatus(UserBaseStatus.WAIT_FOR_ACTIVE);
		userBaseDao.insert(userBase);
		log.info(String.format("inser into userBase =%s", mapper.writeValueAsString(userBase)));
		
		/**
		 * 联系方式
		 * 邮箱：默认待激活
		 */
		UserLink emailLink = new UserLink();
//		emailLink.setCreateTime(now);
		emailLink.setStatus(UserLinkStatus.WAIT_ACTIVE);;
		emailLink.setLinkType(UserLinkTypes.EMAIL);
		emailLink.setLinkValue(regObjDTO.getEmail());
		emailLink.setUserId(userBase.getId());
		userLinkDao.insert(emailLink);
		log.info(String.format("inser into emailLink =%s", mapper.writeValueAsString(emailLink)));
		userBase.getUserLinks().add(emailLink);
		
		 
		
		/**
		 * 保存用户账户信息
		 */
		UserAccount userAccount = new UserAccount();
		userAccount.setUserId(userBase.getId());
		userAccountDao.insert(userAccount);
		log.info(String.format("inser into userAccount =%s", mapper.writeValueAsString(userAccount)));
		userBase.setUerAccount(userAccount);
		
		/**
		 * 向邮箱发信,激活邮箱
		 */
		MailSender mailSender = new MailSender();
		Date days3Later = DateUtils.addDays(now, 3);
		int emailLinkId = emailLink.getId();
		String endtime = DateFormatUtils.format(days3Later, "yyyy-MM-dd HH:mm:ss");
		String content = String.format("endtime=%s&id=%d&email=%s", endtime,emailLinkId,regObjDTO.getEmail());
		mailSender.regSendEmail(regObjDTO.getEmail(), "信用网 邮箱验证 ", now, content);
		log.info(String.format("mailSender sender content =%s", content));
		/**
		 * 保存修改邮箱的记录
		 */
		OperateLog operateLog = new OperateLog();
		operateLog.setIp(regObjDTO.getIp());
		operateLog.setOperateTime(now);
		operateLog.setOperateType(OperateTypes.REG_EMAIL);
		operateLog.setAfterValue(regObjDTO.getEmail());
		operateLog.setUser_id(userBase.getId());
		operateLogDao.insert(operateLog);
		log.info(String.format("inser into operateLog =%s", mapper.writeValueAsString(operateLog)));
		/**
		 * 保存修改密码的操作
		 */
		OperateLog operateLog2 = new OperateLog();
		operateLog2.setIp(regObjDTO.getIp());
		operateLog2.setOperateTime(now);
		operateLog2.setOperateType(OperateTypes.REG_PASSWORD);
		operateLog2.setAfterValue(PWDGenter.generateKen(regObjDTO.getPassword()) );
		operateLog2.setUser_id(userBase.getId());
		operateLogDao.insert(operateLog2);
		log.info(String.format("inser into operateLog2 =%s", mapper.writeValueAsString(operateLog2)));
		
		ret.setResult(true);
		ret.setEntity(userBase);
		ret.setMsg("SUCCESS");
		return ret;
	}
	
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
			throw new BusinessException(BusinessExceptionInfos.YAN_ZHENG_MA_ERROR,"yanzhengma");
		}else if(!yanzhengMaInSession.equalsIgnoreCase(yanzhengma)){
			throw new BusinessException(BusinessExceptionInfos.YAN_ZHENG_MA_ERROR,"yanzhengma");
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
		
		/**
		 * 查看用户联系方式 
		 */
		List<UserLink> userLinks = userLinkDao.getUsersByUserId(userBase.getId());
		userBase.setUserLinks(userLinks);
		
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
				List<UserLink> userLinks = userLinkDao.getUsersByUserId(userBase.getId());
				userBase.setUserLinks(userLinks);
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
	  * 兑换平台币
	 * @throws BusinessException 
	  */
	public EntityReturnData exchangePublishMoney(UserBase userBase,
			BigDecimal amount) throws SQLException, BusinessException {
		EntityReturnData ret = new EntityReturnData();
		UserAccount userAccount = userAccountDao.getUserAccountByUserId(userBase.getId()); 
		/**
		 * 当前余额
		 */
		BigDecimal currentBalance = userAccount.getCurrentBalance();
		if(currentBalance.compareTo(amount )<0){
			throw  new BusinessException(BusinessExceptionInfos.ACCOUNT_MONEY_NO_ENOUGH);
		}
		BigDecimal currentRestPoints  = userAccount.getCurrentRestPoints();
		//修改帐户余额及广告币的数量 1:1兑换
		userAccount.setCurrentBalance(currentBalance.subtract(amount));
		userAccount.setCurrentRestPoints(currentRestPoints.add(amount));
		this.userAccountDao.update(userAccount);
		//记录日志
		UserAccountLog userAccountLog = new UserAccountLog();
		userAccountLog.setAfterLockedAmount(userAccount.getLockedBalance());
		userAccountLog.setAfterLockedPoints(userAccount.getLockedPoints());
		userAccountLog.setAfterRestAmount( userAccount.getCurrentBalance());
		userAccountLog.setAfterRestPoints(userAccount.getCurrentRestPoints());
		userAccountLog.setPoints(amount);
		userAccountLog.setBeforeLockedAmount(userAccount.getLockedBalance());
		userAccountLog.setBeforeLockedPoints(userAccount.getLockedPoints());
		userAccountLog.setBeforeRestAmount(currentBalance);
		userAccountLog.setBeforeRestPoints(currentRestPoints);
		userAccountLog.setCreateTime(new Date());
		userAccountLog.setUserId(userBase.getId());
		userAccountLog.setType(UserAccountTypes.BUYPOINTS);
		this.userAccountLogDao.insert(userAccountLog);
		//记录公司帐户信息
		
		
		CompanyAccount companyAccount = (CompanyAccount) companyAccountDao.selectById(1, CompanyAccount.class);
		if(companyAccount == null){
			throw  new BusinessException(BusinessExceptionInfos.NO_SET_COMPANY_ACCOUNT);
		}
		companyAccount.setGetPoints(companyAccount.getSellPoint().add(amount));
		this.companyAccountDao.update(companyAccount);
	 
		ret.setResult(true);
		ret.setEntity(userAccountLog);
		return ret;
	}

	/**
	 * 设置公司对某个用户赞助点数
	 */
	public void doSupplypoint(AdminUser adminUser, Integer userId,
			BigDecimal supplyPoint) throws SQLException, BusinessException {
		
		
		if(BigDecimal.ZERO.compareTo(supplyPoint)>=0){
			throw  new BusinessException(BusinessExceptionInfos.SET_SUPPLY_POINT_CANNOT_BE_NEGATIVE);
		}else if (new BigDecimal("50").compareTo(supplyPoint)<0){
			//上限50
			throw  new BusinessException(BusinessExceptionInfos.SET_SUPPLY_POINT_OUT_THE_LARGE_RANGE);
		}
		 
		UserAccount userAccount = userAccountDao.getUserAccountByUserId(userId); 
		if(userAccount == null){
			throw  new BusinessException(BusinessExceptionInfos.NO_USER_ACCOUNT_BY_USERID);
		}
		//设置修改后的点数
		BigDecimal beforePoint = userAccount.getCurrentRestPoints();
		userAccount.setCurrentRestPoints(beforePoint.add(supplyPoint));
		this.userAccountDao.update(userAccount);
		
		//记录客户帐户日志
		UserAccountLog userAccountLog = new UserAccountLog();
		userAccountLog.setAfterLockedAmount(userAccount.getLockedBalance());
		userAccountLog.setAfterLockedPoints(userAccount.getLockedPoints());
		userAccountLog.setAfterRestAmount( userAccount.getCurrentBalance());
		userAccountLog.setAfterRestPoints(userAccount.getCurrentRestPoints());
		userAccountLog.setPoints(supplyPoint);
		userAccountLog.setBeforeLockedAmount(userAccount.getLockedBalance());
		userAccountLog.setBeforeLockedPoints(userAccount.getLockedPoints());
		userAccountLog.setBeforeRestAmount(userAccount.getCurrentBalance());
		userAccountLog.setBeforeRestPoints(beforePoint);
		userAccountLog.setCreateTime(new Date());
		userAccountLog.setUserId(userId);
		userAccountLog.setAdminUserId(adminUser.getId());
		userAccountLog.setType(UserAccountTypes.COMPANY_SUPPLY);
		this.userAccountLogDao.insert(userAccountLog);
		
		//记录公司帐户变化
		CompanyAccount companyAccount = (CompanyAccount) companyAccountDao.selectById(1, CompanyAccount.class);
		if(companyAccount == null){
			throw  new BusinessException(BusinessExceptionInfos.NO_SET_COMPANY_ACCOUNT);
		}
		//公司支出增加
		companyAccount.setSupplyPoints(companyAccount.getSupplyPoints().add(supplyPoint));;
		this.companyAccountDao.update(companyAccount);
		 
	}

}
