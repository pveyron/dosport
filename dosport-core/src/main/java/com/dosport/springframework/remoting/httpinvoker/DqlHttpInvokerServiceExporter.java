package com.dosport.springframework.remoting.httpinvoker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.support.RemoteInvocation;
import org.springframework.remoting.support.RemoteInvocationResult;
import org.springframework.web.util.NestedServletException;

import com.dosport.security.utils.ThreadLocalPsnId;
import com.dosport.security.utils.ThreadLocalSessionId;

/**
 * 响应客户端发送的远程调用HTTP请求.
 * 
 * @author pwl
 * 
 */
public class DqlHttpInvokerServiceExporter extends HttpInvokerServiceExporter {

	/** 用户id. */
	protected static final String HTTP_HEADER_APP_IDENTITY_ID = "app_identity_id";

	/** 当前会话id. */
	protected static final String HTTP_HEADER_APP_IDENTITY_SESSION_ID = "app_identity_session_id";

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		try {
			this.handleRequestHeader(request, response);

			RemoteInvocation invocation = readRemoteInvocation(request);
			RemoteInvocationResult result = invokeAndCreateResult(invocation, getProxy());
			writeRemoteInvocationResult(request, response, result);
		} catch (ClassNotFoundException ex) {
			throw new NestedServletException("Class not found during deserialization", ex);
		}
	}

	protected void handleRequestHeader(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String psnId = request.getHeader(HTTP_HEADER_APP_IDENTITY_ID);
		String sessionId = request.getHeader(HTTP_HEADER_APP_IDENTITY_SESSION_ID);

		// 用户ID
		ThreadLocalPsnId.setPsnId(NumberUtils.toLong(psnId, 0L));

		// sessionId
		ThreadLocalSessionId.setSessionId(StringUtils.isNotBlank(sessionId) ? sessionId : null);
	}

}
