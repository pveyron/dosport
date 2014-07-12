package com.dosport.springframework.remoting.httpinvoker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author liqinghua
 * 
 */
public class RemotingCallServlet extends HttpServlet {

	private static final long serialVersionUID = -5938339339896394812L;
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private Map<String, DqlHttpInvokerServiceExporter> httpInvokerServices = new HashMap<String, DqlHttpInvokerServiceExporter>();
	private Map<String, String> mapping = null;

	protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException,
			IOException {

		try {

			invokeService(httpResponse, httpRequest);

		} catch (Throwable e) {

			// 注意,bean运行过程中的异常并不是通过此处抛出的
			// 而是通过remoting机制传递到客户端再抛出的
			// 此处抛出的是非bean的异常
			// 由于这里的异常不会抛出到客户端，因此把异常打印出来，方便开发调试
			// 使用log4j把异常打印出来是一种好习惯！
			logger.error(e.getMessage() + " remote url=" + httpRequest.getRequestURL(), e);
			throw new ServletException(e);
		}
	}

	private void invokeService(HttpServletResponse response, HttpServletRequest request) throws ServletException {

		logger.debug("request remote url:{}", request.getRequestURL());

		String reqPath = request.getRequestURI();
		// 解析请求的beanId
		String[] serviceId = getServiceId(reqPath);
		// 处理请求
		invokeBean(request, response, serviceId);

	}

	@SuppressWarnings("unchecked")
	private void invokeBean(HttpServletRequest request, HttpServletResponse response, String[] serviceId)
			throws ServletException {

		DqlHttpInvokerServiceExporter exporter = httpInvokerServices.get(serviceId[0]);
		try {
			if (exporter != null) {
				exporter.handleRequest(request, response);
			} else {

				WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
				Object service = ctx.getBean(serviceId[1]);
				Class serviceInterface = this.findServiceInterface(service.getClass());
				// 未存在注解@remote的接口，则查找特殊配置
				if (serviceInterface == null) {
					setMapping();
					String className = mapping.get(serviceId[1]);
					if (className != null) {
						serviceInterface = Class.forName(className);
					} else {
						logger.error("找不到接口{}", serviceId);
						throw new ServletException(String.format("找不到接口%s,%s", serviceId[0], serviceId[1]));
					}
				}
				exporter = new DqlHttpInvokerServiceExporter();
				exporter.setService(service);
				exporter.setServiceInterface(serviceInterface);
				exporter.afterPropertiesSet();
				exporter.handleRequest(request, response);
				httpInvokerServices.put(serviceId[0], exporter);
			}
		} catch (BeansException e) {
			logger.error(e.getMessage(), e);
			throw new ServletException(e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new ServletException(e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			throw new ServletException(e);
		}

	}

	/**
	 * 找到标注了remote注解的接口.
	 * 
	 * @param beanClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Class findServiceInterface(Class beanClass) {
		Class serviceInterface = null;
		for (Class interfaceClass : beanClass.getInterfaces()) {
			if (AnnotationUtils.isAnnotationDeclaredLocally(Remote.class, interfaceClass)) {
				serviceInterface = interfaceClass;
			}
		}
		return serviceInterface;
	}

	/**
	 * 用正则表达式将Path中的服务id提取出来，比如“/remoting/com.cownew.demo.IService”
	 * 将“com.cownew.demo.IService”解析出来.
	 */
	private String[] getServiceId(String reqPath) throws ServletException {

		if (reqPath.matches("^(.*)/remoting/([\\w]+)Remoting$")) {
			int index = reqPath.lastIndexOf("/");
			// remoteBean = beanId + Remoting
			String remoteBean = reqPath.substring(index + 1);
			String beanId = remoteBean.substring(0, remoteBean.length() - 8);
			return new String[] { remoteBean, beanId };
		} else {
			throw new ServletException("remotting请求路径不正确{}");
		}
	}

	@SuppressWarnings("unchecked")
	public void setMapping() {
		if (mapping == null) {
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			this.mapping = (Map<String, String>) ctx.getBean("remoteInterfaceContainer");
		}
	}

}
