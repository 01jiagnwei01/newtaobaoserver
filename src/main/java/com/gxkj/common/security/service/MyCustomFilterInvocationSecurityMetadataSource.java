package com.gxkj.common.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import com.gxkj.common.security.dao.AdminMenuDaoImpl;
import com.gxkj.common.security.entitys.AdminMenu;


/**
 * 资源源数据定义，即定义某一资源可以被哪些角色访问
 *
 */
public class MyCustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource  {

	private static final Logger logger = Logger
			.getLogger(MyCustomFilterInvocationSecurityMetadataSource .class);

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	private PathMatcher pathMatcher = new AntPathMatcher();
	
	private AdminMenuDaoImpl adminMenuDaoImpl =  new AdminMenuDaoImpl();	

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		System.out.println("执行方法 getAllConfigAttributes");
		
		return null;
	}
	
	public MyCustomFilterInvocationSecurityMetadataSource  () {
		super();
		//resourceMap = loadResourceMatchAuthority(); 
	}


	/**
	 * 加载资源与权限的映射关系
	 * @return
	 */
	private Map<String, Collection<ConfigAttribute>> loadResourceMatchAuthority() {

		Map<String, Collection<ConfigAttribute>> map = new HashMap<String, Collection<ConfigAttribute>>();

		List<AdminMenu> menus = adminMenuDaoImpl.getAllAdminMenu();
		for(AdminMenu item:menus){
			if(StringUtils.isEmpty(item.getPath())){
				continue;
			}
			Collection<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
			ConfigAttribute config = new SecurityConfig(item.getSpringSecurityRole());
			list.add(config);
			map.put(item.getPath(), list);
		}
		 

		return map;

	}
	/**
	 * 返回null:表示资源不受权限约束，所有人都可以访问
	 * 正常返回[ROLE_ADMIN,ROLE_USER]
	 */
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();

//		System.out.println("requestUrl is " + url);
		logger.info("requestUrl is " + url);
		
		if (resourceMap == null) {
			resourceMap =loadResourceMatchAuthority();
		}
		Collection<ConfigAttribute>  retData = null;
		//比较url是否存在
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (pathMatcher.match(resURL,url)) {
				retData = resourceMap.get(resURL);
				break;
			}
		}
		if(retData == null){
			 retData = resourceMap.get(url);
		}
		if(retData == null){
			 System.out.println(url+"不是受保护资源 ");
		}	 
		return retData;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
}
