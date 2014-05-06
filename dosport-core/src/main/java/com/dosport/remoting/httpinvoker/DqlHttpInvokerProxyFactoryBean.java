package com.dosport.remoting.httpinvoker;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerClientInterceptor;

/**
 * HTTPInvoker远程代理对象.
 * 
 * @author pwl
 * 
 */
public class DqlHttpInvokerProxyFactoryBean extends HttpInvokerClientInterceptor implements FactoryBean<Object> {

	private Object serviceProxy;

	public DqlHttpInvokerProxyFactoryBean() {
		super();
	}

	public void afterPropertiesSet() {

		DqlHttpInvokerRequestExecutor executor = new DqlHttpInvokerRequestExecutor();
		executor.setBeanClassLoader(getBeanClassLoader());
		this.setHttpInvokerRequestExecutor(executor);

		super.afterPropertiesSet();
		if (getServiceInterface() == null) {
			throw new IllegalArgumentException("Property 'serviceInterface' is required");
		}
		this.serviceProxy = new ProxyFactory(getServiceInterface(), this).getProxy(getBeanClassLoader());
	}

	@Override
	public Object getObject() throws Exception {

		return this.serviceProxy;
	}

	@Override
	public Class<?> getObjectType() {

		return getServiceInterface();
	}

	@Override
	public boolean isSingleton() {

		return true;
	}

}
