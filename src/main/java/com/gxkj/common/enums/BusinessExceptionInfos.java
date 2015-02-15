package com.gxkj.common.enums;

public enum BusinessExceptionInfos {

	NO_USER_FOUND_BY_ID("1","根据ID没有找到用户 "),
	NO_USER_ACCOUNT_BY_USERID("2","根据USERID没有找到用户账号信息 "),
	EMAIL_DUPLICATE_EXIST("3","邮箱重复存在 "),
	RECHARDAPPLY_STATUS_NOT_WAIT_FOR("4","申请充值的记录的状态不是待审核状态 "),
	THIRDORDERNO_IS_USED("5","该流水号已经被使用过且充值成功"),
	DRAWPAPPLY_STATUS_NOT_WAIT_FOR("6","取款申请的记录的状态不是待审核状态 "),
	DRAWPAPPLY_THIRDORDERNO_IS_USED("7","该流水号已经被使用过且付款成功"),
	THIRD_ORDER_NO_IS_NULL("9","流水号不能为空"),
	ACCOUNT_MONEY_NO_ENOUGH("10","账户余额不足"),
	EMAIL_LINNK_CANNOT_CHANGE("11","不支持邮箱更改"),
	ACCOUNT_CAN_NOT_BE_NEGATIVE("12","金额不能是负数"),
	OUT_THE_LARGE_RANGE("13","超出上限了"),
	NO_SET_COMPANY_ACCOUNT("14","没有设置公司帐号"),
	SET_SUPPLY_POINT_CANNOT_BE_NEGATIVE("15","补助用户帐户点数不能为负数"),
	SET_SUPPLY_POINT_OUT_THE_LARGE_RANGE("16","补助用户帐户点数超限制了"),
	Yan_Zheng_MA_ERROR("17","验证码输入错误"),
	ADMIN_IS_MAINTING("18","管理员正在维护中 ，请联系客服"),
	EMAIL_ADDRESS_IS_ERROR("19","邮箱地址无效"),
	UP_LOAD_PIC_CANNOT_BE_NULL_ERROR("20","上传图片不能为空"),
	MAIL_CONTENT_CANNOT_FOUND("21","邮件内容无法找到"),
	MAIL_RECEIVER_CANNOT_BE_NULL("22","收件人不能为空"), 
	ONLY_NOSEND_MAIL_CAN_EXECUTE("23","只有未发送的邮件才允许执行"), 
	SEND_TIME_SHOULD_AFTER_SYSTEM_TIME("24","发送时间应该晚于系统当前时间"), 
	NO_USE_EXCEL_TEMPLATE("25","请使用模板进行导入"),
	EMAIL_IS_REGED("26","邮箱已经被注册了"),
	EMAIL_NOT_SEND_CODE("27","邮箱没有获得注册码"),
	PASSWORD_IS_BLANK("28","密码为空"),
	REPASSWORD_IS_BLANK("29","确认密码为空"),
	USER_NAME_IS_BLANK("30","用户名为空"),
	USER_NAME_IS_REGED("31","用户名已经被注册过"),
	USER_NAME_IS_OUT_MAX("32","用户名长度超过了50"),
	USER_NAME_IS_less_than("33","用户名长度应该大于5"),
	PASSWORD_IS_MORE_than_20("34","密码长度不能大于20"),
	EMAIL_IS_MORE_than_50("35","邮箱长度不能超过50"),
	USER_NAME_OR_PASSWORD_ERROR("36","用户名获密码错误"),
	PASSWORD_IS_ERROE("37","密码错误"),
	NO_EMAIL_IS_BIND("38","没有绑定邮箱"),
	CAO_ZUO_MA_IS_BLANK("39","操作码为空"),
	RECAO_ZUO_MA_IS_BLANK("40","确认操作码为空"),
	RECAO_ZUO_MA_NOT_EQUAL("41","确认操作码与操作码不一致"),
	Yan_Zheng_MA_IS_BLANK("42","验证码为空"),
	OLD_CAO_ZUO_MA_IS_BLANK("43","旧操作码为空"),
	OLD_CAO_ZUO_MA_IS_ERROR("44","旧操作码输入错误"),
	NEW_EMAIL_IS_EQUAL_USER_EMAIL("45","新邮箱与已绑定邮箱一致，请重置新邮箱"),
	NEW_EMAIL_IS_BLANK("46","新邮箱为空"),
	CAO_ZUO_MA_IS_ERROR("47","操作码输入错误"),
	REPASSWORD_NOT_EQUAL_PASSOWRD("48","确认密码与新密码不一致") ;
	private String errorCode; 
	
	private String errorMsg;
	private BusinessExceptionInfos(String errorCode, String errorMsg) {  
		       this.errorCode = errorCode; 
		       this.errorMsg = errorMsg;
	}
	private BusinessExceptionInfos( String errorMsg) {  
	       this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	 
}
