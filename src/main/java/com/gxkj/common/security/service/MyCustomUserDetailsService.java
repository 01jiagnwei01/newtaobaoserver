package com.gxkj.common.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gxkj.common.security.dao.RelUserRoleDaoImpl;
import com.gxkj.common.security.dao.UserDaoImpl;
import com.gxkj.common.security.entitys.AdminMenu;
import com.gxkj.common.security.entitys.AdminUserBean;
import com.gxkj.common.security.securityvo.UserInfo;

 

/**
 * 自定义用户与权限的关系
 *
 */
public class MyCustomUserDetailsService implements UserDetailsService {
	protected static Logger logger = Logger.getLogger("service");
	
	private UserDaoImpl userDAO = new UserDaoImpl();
	
	private RelUserRoleDaoImpl relUserRole = new RelUserRoleDaoImpl();
	
	/**
	 * 根据用户名获取用户-权限等用户信息
	 * 约定1：保证系统用户登陆唯一
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		UserDetails user = null;
		try {
		 
			AdminUserBean dbUser = userDAO.getUser(username);
			List<AdminMenu> menus = relUserRole.getAdminMenuByUserId(dbUser.getId());
	        
			/**
			 * org.springframework.security.core.userdetails.User构造方法里参数的意义
			 * arg0:username
			 * arg1:password
			 * arg2:enabled	default:true
			 * arg3:accountNonExpired default:true
			 * arg4:credentialsNonExpired default:true
			 * arg5:accountNonLocked default:true
			 * arg6:the authorities that should be granted to the caller
			 */
			user = new UserInfo(dbUser.getUserName(), dbUser.getPassword().toLowerCase(), true, true, true, true,getAuthorities(dbUser),menus);
		} catch (Exception e) {
			logger.error("Error in retrieving user");  
            throw new UsernameNotFoundException("Error in retrieving user"); 
		}
		return user;
	}
	
	 /** 
     * 获得访问权限资源 
     *  
     * @param access 
     * @return 
     */  
	private Collection<GrantedAuthority> getAuthorities(AdminUserBean dbUser) {  
  
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);  
  
        // 所有的用户默认拥有ROLE_USER权限  
//        logger.debug("Grant ROLE_USER to this user");  
//        authList.add(new  SimpleGrantedAuthority(RoleConstants.ROLE_USER));  
  
        // 如果参数access为1.则拥有ROLE_ADMIN权限  
//        if (dbUser.getRole().getRoleName().equals(RoleConstants.ROLE_ADMIN)) {  
//            logger.debug("Grant ROLE_ADMIN to this user");  
//            authList.add(new  SimpleGrantedAuthority(RoleConstants.ROLE_ADMIN));  
//        }  
        List<AdminMenu> menus = relUserRole.getAdminMenuByUserId(dbUser.getId());
        
        for(AdminMenu menu :menus){
        	 authList.add(new  SimpleGrantedAuthority(menu.getSpringSecurityRole()));  
        }
        return authList;  
    }  
	
	

}
