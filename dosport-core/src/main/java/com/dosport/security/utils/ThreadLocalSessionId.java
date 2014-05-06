package com.dosport.security.utils;

/**
 * 当前会话id.
 * 
 * @author pwl
 * 
 */
public class ThreadLocalSessionId {

	private static ThreadLocal<String> sessionIdThread = new ThreadLocal<String>();

	public static String getSessionId() {
		return sessionIdThread.get();
	}

	public static void setSessionId(String sessionId) {
		sessionIdThread.set(sessionId);
	}
}
