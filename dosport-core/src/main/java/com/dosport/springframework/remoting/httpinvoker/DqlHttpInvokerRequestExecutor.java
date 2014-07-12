package com.dosport.springframework.remoting.httpinvoker;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.springframework.remoting.httpinvoker.SimpleHttpInvokerRequestExecutor;

import com.dosport.security.utils.SecurityUtils;

/**
 * 封装基于HTTP协议的远程调用过程.
 * 
 * @author pwl
 * 
 */
public class DqlHttpInvokerRequestExecutor extends SimpleHttpInvokerRequestExecutor {

	private final static int READ_TIME_OUT = 60000;

	/** 用户id. */
	protected static final String HTTP_HEADER_APP_IDENTITY_ID = "app_identity_id";

	/** 当前会话id. */
	protected static final String HTTP_HEADER_APP_IDENTITY_SESSION_ID = "app_identity_session_id";

	@Override
	protected void prepareConnection(HttpURLConnection con, int contentLength) throws IOException {

		con.setReadTimeout(READ_TIME_OUT);
		super.prepareConnection(con, contentLength);

		this.setMoreRequestProperty(con);
	}

	protected void setMoreRequestProperty(HttpURLConnection con) throws IOException {

		Long psnId = SecurityUtils.getCurrentPsnId();
		String sessionId = SecurityUtils.getSessionId();

		con.setRequestProperty(HTTP_HEADER_APP_IDENTITY_ID, String.valueOf(psnId));
		con.setRequestProperty(HTTP_HEADER_APP_IDENTITY_SESSION_ID, sessionId);
	}

}
