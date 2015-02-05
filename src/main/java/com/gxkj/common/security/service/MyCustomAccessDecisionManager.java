package com.gxkj.common.security.service;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 自定义访问决策器，决定某个用户具有的角色，是否有足够的权限去访问某个资源 
 *
 */
public class MyCustomAccessDecisionManager implements AccessDecisionManager {

	/**
	 * 裁定当前用户对应权限authentication是否包含所请求资源所拥有的权限 
	 * 如果成立 则通过裁定 
	 * 否则发生AccessDeniedException异常
	 * @param authentication the caller invoking the method (not null)
     * @param object the secured object being called
     * @param configAttributes the configuration attributes associated with the secured object being invoked
	 * 
	 */
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		
		if (configAttributes == null) {
			return;
		}
		//用户拥有的权限
		Collection<? extends GrantedAuthority>  userOwnAuths = authentication.getAuthorities();
		StringBuffer userOwnAuths_bf = new StringBuffer();
		//用户拥有的权限字符串，用逗号<,>分割
		String userOwnAuths_String = "";
		if(userOwnAuths!=null){
			for(GrantedAuthority entity : userOwnAuths){
				userOwnAuths_bf.append(entity.getAuthority()).append(",");
			}
			userOwnAuths_String = userOwnAuths_bf.toString();
			if(userOwnAuths_String.length()>0){
				//去除结尾的逗号
				userOwnAuths_String = userOwnAuths_String.substring(0, userOwnAuths_String.length()-1);
			}
		}
		//configAttributes： 所请求的资源需要拥有的权限(一个资源对多个权限)
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while (iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();
			// 访问所请求资源所需要的权限
			String needPermission = configAttribute.getAttribute();
			//System.out.println("needPermission is " + needPermission);
			// 用户所拥有的权限authentication
			if(userOwnAuths_String.indexOf(needPermission)>=0){
				return;
			}
		}

		// 没有权限
		throw new AccessDeniedException(" No Access Dendied ");

	}
 
	public boolean supports(ConfigAttribute configAttribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}
