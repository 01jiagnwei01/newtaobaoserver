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
import com.gxkj.taobaoservice.services.BindQQService;
@Service
public class BindQQServiceImpl implements BindQQService {

	@Autowired
	private UserBaseDao userBaseDao;
 
	/**
	 * 修改用户的QQ
	 */
	public UserBase doBindQQ(UserBase userBase, String newQQ, String caozuoma,
			String yanzhengma, String yanzhengMaInSession)
			throws BusinessException, SQLException {
 
		if(StringUtils.isBlank(newQQ)){
			throw new BusinessException(BusinessExceptionInfos.QQ_IS_BLANK,"qq");
		}
		if(newQQ.length() >=15){
			throw new BusinessException(BusinessExceptionInfos.QQ_Length_MORE_THAN,"qq");
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
		dbUserBase.setBindQq(newQQ);
		userBaseDao.update(dbUserBase);
		
		return dbUserBase;

	}

}
