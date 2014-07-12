package com.dosport.springframework.security.core.userdetails;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dosport.springframework.remoting.httpinvoker.BaseRemotingServiceFactory;
import com.dosport.springframework.security.domain.SysAuthority;
import com.dosport.springframework.security.domain.SysUser;
import com.dosport.springframework.security.service.UserService;

/**
 * 
 * @author pwl
 * 
 */
public class SysUserDetailsService implements UserDetailsService {

	@Resource(name = "remotingServiceFactory")
	private BaseRemotingServiceFactory remotingServiceFactory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		List<GrantedAuthority> authsList = new ArrayList<GrantedAuthority>();
		SysUser user = null;
		try {
			UserService userService = this.remotingServiceFactory.getMainSiteService(UserService.class);
			user = userService.getUserByLoginName(username);
			if (user == null) {
				throw new UsernameNotFoundException("用户不存在");
			}
			List<SysAuthority> authorityList = userService.getAuthorityList(user.getId());
			if (CollectionUtils.isNotEmpty(authorityList)) {
				for (SysAuthority authority : authorityList) {
					authsList.add(new GrantedAuthorityImpl(authority.getName()));
				}
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException("用户不存在");
		}

		User userdetail = new User(username, user.getPassword(), true, true, true, true, authsList);

		return userdetail;
	}
}
