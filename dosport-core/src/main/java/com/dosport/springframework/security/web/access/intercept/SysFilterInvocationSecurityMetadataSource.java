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

import com.dosport.springframework.remoting.httpinvoker.BaseRemotingServiceFactory;
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

	private UrlMatcher urlMatcher = new AntUrlPathMatcher();

	private BaseRemotingServiceFactory remotingServiceFactory;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

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

	public void loadResourceDefine() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		try {
			UserService userService = this.remotingServiceFactory.getMainSiteService(UserService.class);
			List<SysAuthority> allAuthority = userService.getAllAuthoritiy();
			if (CollectionUtils.isNotEmpty(allAuthority)) {
				for (SysAuthority authority : allAuthority) {
					ConfigAttribute ca = new SecurityConfig(authority.getName());
					List<String> urlList = userService.getResourceValueByAuthoritiyId(authority.getId());
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

	public BaseRemotingServiceFactory getRemotingServiceFactory() {
		return remotingServiceFactory;
	}

	public void setRemotingServiceFactory(BaseRemotingServiceFactory remotingServiceFactory) {
		this.remotingServiceFactory = remotingServiceFactory;
	}

}
