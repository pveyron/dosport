package com.dosport.springframework.security.web.access.intercept;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;

import com.dosport.remoting.httpinvoker.BaseRemotingServiceFactory;
import com.dosport.springframework.security.domain.SysAuthority;
import com.dosport.springframework.security.service.UserService;

/**
 * 
 * 
 * @author pwl
 * 
 */
public class SysFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private Logger logger = LoggerFactory.getLogger(getClass());

	// 这里不能使用注解注入的方式，只能通过set方法和构造方法的方式注入，因为在构造方法中不能使用注入的数据
	private UserService userService;

	private UrlMatcher urlMatcher = new AntUrlPathMatcher();

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public SysFilterInvocationSecurityMetadataSource() {

	}

	public SysFilterInvocationSecurityMetadataSource(BaseRemotingServiceFactory remotingServiceFactory) {
		this.userService = remotingServiceFactory.getMainSiteService(UserService.class);
		loadResourceDefine();
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object obj) throws IllegalArgumentException {
		String url = ((FilterInvocation) obj).getRequestUrl();

		int firstQuestionMarkIndex = url.indexOf("?");

		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}

		Iterator<String> ite = resourceMap.keySet().iterator();

		while (ite.hasNext()) {
			String resURL = ite.next();

			if (this.urlMatcher.pathMatchesUrl(url, resURL)) {
				return resourceMap.get(resURL);
			}
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

	protected void loadResourceDefine() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		try {
			List<SysAuthority> allAuthority = this.userService.getAllAuthoritiy();
			if (CollectionUtils.isNotEmpty(allAuthority)) {
				for (SysAuthority authority : allAuthority) {
					ConfigAttribute ca = new SecurityConfig(authority.getName());
					List<String> urlList = this.userService.getResourceValueByAuthoritiyId(authority.getId());
					if (CollectionUtils.isNotEmpty(urlList)) {
						for (String url : urlList) {
							if (resourceMap.containsKey(url)) {
								Collection<ConfigAttribute> value = resourceMap.get(url);
								value.add(ca);
								resourceMap.put(url, value);
							} else {
								atts.add(ca);
								resourceMap.put(url, atts);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("初始化资源所需权限出现异常：", e);
		}
	}

}
