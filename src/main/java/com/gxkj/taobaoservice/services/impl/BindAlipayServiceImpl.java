package com.gxkj.taobaoservice.services.impl;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.PWDGenter;
import com.gxkj.taobaoservice.daos.UserBaseDao;
import com.gxkj.taobaoservice.entitys.UserBase;
import com.gxkj.taobaoservice.services.BindAlipayService;
@Service
public class BindAlipayServiceImpl implements BindAlipayService {

	@Autowired
	private UserBaseDao userBaseDao;
	
	public UserBase doBindAlipay(UserBase userBase, String alipay,
			String caozuoma, String yanzhengma, String yanzhengMaInSession)
			throws BusinessException, SQLException {
	 
		if(StringUtils.isBlank(alipay)){
			throw new BusinessException(BusinessExceptionInfos.ALIPAY_IS_BLANK,"alipay");
		}
		if(alipay.length() >=30){
			throw new BusinessException(BusinessExceptionInfos.TAO_BAO_XIAO_HAO_Length_MORE_THAN,"alipay");
		}
		if(StringUtils.isBlank(caozuoma)){
			throw new BusinessException(BusinessExceptionInfos.CAO_ZUO_MA_IS_BLANK,"caozuoma");
		}
		if(StringUtils.isBlank(yanzhengma)){
			throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_IS_BLANK,"yanzhengma");
		}
		if(!yanzhengma.equalsIgnoreCase(yanzhengMaInSession)){
			throw new BusinessException(BusinessExceptionInfos.Yan_Zheng_MA_ERROR,"yanzhengma");
		}
		String  sessionCaozuoMa = userBase.getCaoZuoMa();
		
		if(StringUtils.isBlank(sessionCaozuoMa)){
			throw new BusinessException(BusinessExceptionInfos.NO_SET_YAN_ZHENG_MA,"caozuoma");
		}
		String  md5Caozuoma = PWDGenter.generateKen(caozuoma);
		if ( !sessionCaozuoMa.equals(md5Caozuoma)){
			throw new BusinessException(BusinessExceptionInfos.CAO_ZUO_MA_IS_ERROR,"caozuoma");
		}
		UserBase dbUserBase = (UserBase) userBaseDao.selectById(userBase.getId(), UserBase.class);
		dbUserBase.setBindAlipay(alipay);
		userBaseDao.update(dbUserBase);
		
		return dbUserBase;
	}

}
