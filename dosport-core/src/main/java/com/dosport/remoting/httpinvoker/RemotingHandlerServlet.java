package com.dosport.remoting.httpinvoker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 远程调用服务器端拦截器.
 * 
 * @author pwl
 * 
 */
public class RemotingHandlerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1903001780446282476L;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			Object invokeService = ctx.getBean(this.getInvokeBean(req.getRequestURI()));
			if (invokeService != null && (invokeService instanceof DqlHttpInvokerServiceExporter)) {
				DqlHttpInvokerServiceExporter exporter = (DqlHttpInvokerServiceExporter) invokeService;
				exporter.handleRequest(req, resp);
			}
		} catch (Exception e) {
			logger.error("远程调用拦截器出现异常", e);
			throw new ServletException(e);
		}
	}

	/**
	 * 获取远程调用的beanName.
	 * 
	 * @param rqeuestUrl
	 * @return
	 * @throws ServletException
	 */
	private String getInvokeBean(String rqeuestUrl) throws ServletException {

		int index = rqeuestUrl.lastIndexOf("/");
		String invokeBean = rqeuestUrl.substring(index + 1);

		return invokeBean;
	}

}
