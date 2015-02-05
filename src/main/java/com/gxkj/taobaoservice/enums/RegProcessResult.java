package com.gxkj.taobaoservice.enums;
/**
 * 注册过程中产生的 结果 ，约定 所有的失败 都是以_FAILURE结尾 
 *
 */
public enum RegProcessResult {

	USER_NAME_BLANK_FAILURE("用户名为空 "),
	EMAIL_BLANK_FAILURE("用户名为空 "),
	EMAIL_ERROR_FAILURE("邮箱格式不正确 "),
	PASSWORD_BLANK_FAILURE("密码为空 "),
	REPASSWORD_BLANK_FAILURE("确认密码为空 "),
	PASSWORD_NOT_EQUAL_REPASSWORD_FAILURE("确认密码与密码不一致 "),
	EMAIL_IS_USED_FAILURE("邮箱已经被使用了"),
	USER_NAME_IS_USED_FAILURE("用户名已经 被使用了"),
	YAN_ZHENG_MA_IS_ERROR_FAILURE("验证码输入错误"),
	YAN_ZHENG_MA_IS_BLANK_FAILURE("验证码为空");
	
	private String name; 
	
	private RegProcessResult(String name ) {  
		       this.name = name; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static void main(String[] args) {
		System.out.println(RegProcessResult.USER_NAME_BLANK_FAILURE.toString());
		for (RegProcessResult s : RegProcessResult.values())  
			       System.out.println(s + ", name " + s.getName());  

	}
}
