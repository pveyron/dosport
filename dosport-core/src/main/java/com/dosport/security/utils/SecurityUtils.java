package com.dosport.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全工具类.
 * 
 * @author pwl
 * 
 */
public class SecurityUtils {

	/**
	 * 获取当前登录用户的id.
	 * 
	 * @return
	 */
	public static Long getCurrentPsnId() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long userId;
		if (auth == null || auth.getName().equalsIgnoreCase("anonymous")) {
			userId = 0L;
		} else {
			userId = ThreadLocalPsnId.getPsnId();
		}
		return userId;
	}

	/**
	 * 获取当前会话id.
	 * 
	 * @return
	 */
	public static String getSessionId() {

		return ThreadLocalSessionId.getSessionId();
	}
}
