package com.gxkj.common.security.securityvo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.gxkj.common.security.entitys.AdminMenu;


public class UserInfo extends BaseUserDetails {
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 * @param menus  授权用户可以访问的菜单列表
	 */
	public UserInfo(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,List<AdminMenu> menus) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.menus = menus;
		 
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1647847972103185229L;
	
	private List<AdminMenu> menus ;

	public List<AdminMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<AdminMenu> menus) {
		this.menus = menus;
	}
	
	

}
