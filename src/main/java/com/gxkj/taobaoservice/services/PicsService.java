package com.gxkj.taobaoservice.services;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.multipart.MultipartFile;

import com.gxkj.common.exceptions.BusinessException;
import com.gxkj.common.util.ListPager;
import com.gxkj.taobaoservice.entitys.AdminUser;
import com.gxkj.taobaoservice.entitys.Pics;
import com.gxkj.taobaoservice.enums.PicStatus;

public interface PicsService {

	/**
	 * 分页查看
	 * @param pageno
	 * @param pagesize
	 * @param name
	 * @param status
	 * @return
	 * @throws SQLException
	 */
	public ListPager doPage(int pageno, int pagesize, String name,PicStatus status) throws SQLException;
	
	/**
	 * 增加图片
	 * @param pic
	 * @param picPath
	 * @param picName
	 * @param picDesc
	 * @param adminUser
	 * @throws SQLException,BusinessException
	 */
	public Pics addPics(MultipartFile pic,String picName,String picDesc,AdminUser adminUser,String saveDir)throws SQLException, BusinessException, IOException;
	
	/**
	 * 删除图片
	 * @param picId
	 * @param adminUser
	 * @throws Exception
	 */
	public void doDelPics(int picId,AdminUser adminUser )throws Exception;
	
	 
}
