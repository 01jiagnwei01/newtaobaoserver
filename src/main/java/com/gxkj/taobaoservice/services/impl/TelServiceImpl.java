package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.PWDGenter;
import com.gxkj.common.util.SystemGlobals;
import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.daos.YanZhengMaLogDao;
import com.gxkj.taobaoservice.dto.SmsResponse;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.entitys.YanzhengmaLog;
import com.gxkj.taobaoservice.enums.YanZhengMaLogTranTypes;
import com.gxkj.taobaoservice.enums.YanZhengMaTypes;
import com.gxkj.taobaoservice.services.TelService;
import com.gxkj.taobaoservice.sms.SmsService;
import com.gxkj.taobaoservice.util.RegexUtils;
@Service
public class TelServiceImpl implements TelService {
	private static final Log log =  LogFactory.getLog(TelServiceImpl.class);
	
	@Autowired
	private YanZhengMaLogDao yanZhengMaLogDao;
	
	@Autowired
	@Qualifier("F1SmsServiceImpl")
	private SmsService smsServiceImpl;
	
	@Autowired
	private UserBaseDao userBaseDao;
	 
	public void doSendTelCodeForBind(UserBase userBase, String telNo)
			throws BusinessException, SQLException {
		if(userBase == null){
			log.error(String.format("参数错误，userBase=null"));
			throw new BusinessException(BusinessExceptionInfos.PARAMETER_ERROR,"userBase");
		}
		if(StringUtils.isBlank(telNo)){
			throw new BusinessException(BusinessExceptionInfos.TEL_NO_IS_BLANK,"telNo");
		}
		String bindTel = userBase.getBindTelphone();
		if(StringUtils.isNotEmpty(bindTel) && telNo.equals(bindTel)){
			throw new BusinessException(BusinessExceptionInfos.NEW_TEL_IS_EQUAL_USER_TEL,"telNo");
		}
		if(!RegexUtils.isMobileNO(telNo)){
			throw new BusinessException(BusinessExceptionInfos.TEL_NO_ERROR,"telNo");
		}
		//判断手机是否已经被绑定了
		UserBase  dbUserBase = userBaseDao.getUsersByBindPhone(telNo);
		if(dbUserBase != null){
			throw new BusinessException(BusinessExceptionInfos.TEL_IS_Bind,"telNo");
		}
		
		yanZhengMaLogDao.updateEmaiToNoEnable(telNo,YanZhengMaTypes.phone);
		/**
		 * 保存修改的注册码
		 */
		String code = RegexUtils.getRandomNum(6)+"";
		Date now = new Date();
		YanzhengmaLog yanZhengMaBindCode = new YanzhengmaLog();
		yanZhengMaBindCode.setCode(code);
		yanZhengMaBindCode.setCreateDime(now);
		yanZhengMaBindCode.setType(YanZhengMaTypes.phone);
		yanZhengMaBindCode.setValue(telNo);
		yanZhengMaBindCode.setEnabled(true);
		yanZhengMaBindCode.setTranType(YanZhengMaLogTranTypes.Update_bind);
		int validTimeLeng = SystemGlobals.getIntPreference("reg.code.valid.time", 5);
		Date expTime = DateUtils.addMilliseconds(now, validTimeLeng);
		yanZhengMaBindCode.setExpTime(expTime);
		yanZhengMaBindCode.setUserId(userBase.getId());
		yanZhengMaLogDao.insert( yanZhengMaBindCode);
		
		/**
		 * 手机发送验证码
		 */
		SmsResponse repose = smsServiceImpl.sendSms(String.format(SystemGlobals.getPreference("taobao.sms.temp"), code), telNo, now);
		if(!repose.isOk()){
			log.error("手机发送验证码失败，错误原因是："+smsServiceImpl.getErrorMsg(repose.getCode()));
			throw new BusinessException(BusinessExceptionInfos.TEL_Code_Send_error,"telNo");
		}
	 

	}

	 
	public void doUpdateByPhone(UserBase userBase, String telNo,
			String caozuoma, String yanzhengma) throws BusinessException,
			SQLException {
		if(StringUtils.isBlank(telNo)){
			throw new BusinessException(BusinessExceptionInfos.TEL_NO_IS_BLANK,"telNo");
		}
		 if(StringUtils.isBlank(yanzhengma)) {
			 throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_IS_BLANK,"yanzhengma");
		 }
		 if(StringUtils.isBlank(caozuoma)) {
			 throw new BusinessException(BusinessExceptionInfos.CAO_ZUO_MA_IS_BLANK,"caozuoma");
		 }
		 String sessionCaozuoma = userBase.getCaoZuoMa();
		 if(StringUtils.isBlank(sessionCaozuoma)) {
			 sessionCaozuoma = "";
		 }
		 String md5Caozuoma = PWDGenter.generateKen(caozuoma);
		 if(!md5Caozuoma.equalsIgnoreCase(sessionCaozuoma)) {
			 throw new BusinessException(BusinessExceptionInfos.CAO_ZUO_MA_IS_ERROR,"caozuoma");
		 }
		 
		 YanzhengmaLog yanZhengMaBindCode = yanZhengMaLogDao.getRegLogByUserIdAndTransAndTypeAndValue(userBase.getId(),
				 YanZhengMaLogTranTypes.Update_bind,
				 YanZhengMaTypes.phone,telNo);
		 
		 if(yanZhengMaBindCode == null) {
			 throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_ERROR,"yanzhengma");
		 }
		 if(!yanzhengma.equals(yanZhengMaBindCode.getCode())){
			 throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_ERROR,"yanzhengma");
		 }
		 Date now = new Date();
		 yanZhengMaBindCode.setEnabled(false);
		 yanZhengMaBindCode.setActiveTime(now);
		 yanZhengMaLogDao.update(yanZhengMaBindCode);
		 
		 //重置绑定邮箱
		 userBase.setBindTelphone(telNo);
		 userBaseDao.update(userBase);
		
	}

}
