package com.dosport.security.utils;

/**
 * 用户id本地线程.
 * 
 * @author pwl
 * 
 */
public class ThreadLocalPsnId {

	private static ThreadLocal<Long> psnIdThread = new ThreadLocal<Long>();

	public static long getPsnId() {
		try {
			return psnIdThread.get();
		} catch (Exception e) {
			return 0L;
		}
	}

	public static void setPsnId(long userId) {
		psnIdThread.set(userId);
	}
}
