package com.gxkj.taobaoservice.services.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;

import com.gxkj.common.enums.BusinessExceptionInfos;
import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.common.util.SpringValidatorHolder;
import com.gxkj.taobaoservice.daos.MailAddressListDao;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.MailAddressList;
import com.gxkj.taobaoservice.enums.MailAddressComeFrom;
import com.gxkj.taobaoservice.enums.MailAddressListStatus;
import com.gxkj.taobaoservice.enums.UserGender;
import com.gxkj.taobaoservice.services.MailAddressListService;
import com.gxkj.taobaoservice.util.RegexUtils;
@Service
public class MailAddressListServiceImpl implements MailAddressListService {

	@Autowired
	private MailAddressListDao mailAddressListDao;

	/**
	 * 分类查询
	 */
	public ListPager doPage(int pageno, int pagesize, String name,
			String email, MailAddressListStatus status) throws SQLException {
	 
		ListPager pager = mailAddressListDao.doPage(  pageno,   pagesize,   name,
				  email,   status);
		return pager;
	}


	/**
	 * 增加
	 * @throws BindException 
	 */
	public MailAddressList doAddMailAddressList(
			MailAddressList mailAddressList, AdminUser adminUser) throws SQLException, BindException {
		
		mailAddressList.setCreateUserId(adminUser.getId());
		mailAddressList.setCreteTime(new Date());
		mailAddressList.setStatus(MailAddressListStatus.NORMAL);
		SpringValidatorHolder.validate(mailAddressList); 
		mailAddressListDao.insert(mailAddressList);
		return mailAddressList;
	}


	/**
	 * 修改
	 * @throws BindException 
	 */
	public MailAddressList doUpdateMailAddressList(
			MailAddressList mailAddressList, AdminUser adminUser) throws SQLException, BindException {
		mailAddressList.setCreateUserId(adminUser.getId());
		mailAddressList.setCreteTime(new Date());
		mailAddressList.setStatus(MailAddressListStatus.NORMAL);
		SpringValidatorHolder.validate(mailAddressList); 
		mailAddressListDao.update(mailAddressList);
		return mailAddressList;
	}


	/**
	 * 删除 
	 */
	public void doDelMailAddressList(Integer id,
			AdminUser adminUser) throws SQLException {
		MailAddressList mailAddressList = (MailAddressList) mailAddressListDao.selectById(id, MailAddressList.class);
		mailAddressList.setCreateUserId(adminUser.getId());
		mailAddressList.setCreteTime(new Date());
		mailAddressList.setStatus(MailAddressListStatus.DEL);
		mailAddressListDao.update(mailAddressList);
		
	}


	/**
	 * 批量导入通讯录
	 * @throws SQLException,BusinessException, IOException  
	 */
 
	@SuppressWarnings("resource")
	public void doImport(MultipartFile excel, AdminUser adminUser)
			throws SQLException,BusinessException, IOException {
		
		HSSFWorkbook wb = new HSSFWorkbook( excel.getInputStream());  //处理输入流
		HSSFSheet sheet =  wb.getSheetAt(0);
//		int firstRowNum = sheet.getFirstRowNum();
//		int lastRowNum = sheet.getLastRowNum();
		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
		HSSFRow firstRow =  sheet.getRow(0);
		if(firstRow == null){
			throw new BusinessException(BusinessExceptionInfos.NO_USE_EXCEL_TEMPLATE);
		}
		HSSFCell nameCell = firstRow.getCell(0);
		HSSFCell emailCell = firstRow.getCell(1);
		HSSFCell genderCell = firstRow.getCell(2);
		if(nameCell == null || !"姓名".equals( nameCell.getStringCellValue()) 
				|| emailCell == null || !"邮箱".equals( emailCell.getStringCellValue()) 
				|| genderCell == null || !"性别".equals( genderCell.getStringCellValue()) ){
			throw new BusinessException(BusinessExceptionInfos.NO_USE_EXCEL_TEMPLATE);
		}
		
		
		HSSFRow readRow = null;
		
		List<MailAddressList> entitys = new ArrayList<MailAddressList>();
		Date now = new Date();
		StringBuffer errorMsg = new StringBuffer();
		for(int i=1;i<physicalNumberOfRows;i++){
			readRow = sheet.getRow(i);
			if(readRow == null) continue;
			
			MailAddressList address = new MailAddressList();
			
			String name = readRow.getCell(0).getStringCellValue();
			String email = readRow.getCell(1).getStringCellValue();
			String gender = readRow.getCell(2).getStringCellValue();
			
			address.setComefrom(MailAddressComeFrom.OTHER);
			address.setCreateUserId(adminUser.getId());
			address.setCreteTime(now);
			address.setEmail(email);
			if("男".equals(gender)){
				address.setGender(UserGender.male);
			}else if("女".equals(gender)){
				address.setGender(UserGender.female);
			}else if("未知".equals(gender)){
				address.setGender(UserGender.unknow);
			}
			address.setName(name);
			address.setStatus(MailAddressListStatus.NORMAL);
			
			if(  StringUtils.isEmpty(address.getEmail())  ){
				if(StringUtils.isNotEmpty(errorMsg)){
					errorMsg.append(";");
				}
				errorMsg.append("第"+(i+1)+"行邮箱为空");
				continue;
			} else{
				boolean isEmail = RegexUtils.isEmail(email);
				if(!isEmail){
					if(StringUtils.isNotEmpty(errorMsg)){
						errorMsg.append(";");
					}
					errorMsg.append("第"+(i+1)+"行邮箱格式错误");
					continue;
				}
				boolean isExist =	mailAddressListDao.emailIsExist(email);
				if(isExist){
					if(StringUtils.isNotEmpty(errorMsg)){
						errorMsg.append(";");
					}
					errorMsg.append("第"+(i+1)+"行邮箱已经在数据库中存在，不重复录入");
					continue;
				}
			}
			entitys.add(address);
		}
		wb.close();
		if(StringUtils.isNotEmpty(errorMsg)){
			throw new BusinessException(errorMsg.toString());
		}
		for(int i=0,j=entitys.size();i<j;i++){
			mailAddressListDao.insert(entitys.get(i));
		}
		
		
	}


	 
	 
}
