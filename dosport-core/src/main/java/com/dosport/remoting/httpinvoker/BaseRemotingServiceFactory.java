package com.dosport.remoting.httpinvoker;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.dosport.beans.factory.config.ExtendedPropertyPlaceholderConfigurer;

/**
 * 远程调用服务工厂.
 * 
 * @author pwl
 * 
 */
public class BaseRemotingServiceFactory implements Serializable, InitializingBean, BeanFactoryAware {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 */
	private static final long serialVersionUID = 134376048451419740L;

	// 当前节点URL
	private String currentServiceUrl = null;
	// bean工厂
	private BeanFactory beanFactory;

	@Autowired
	private ExtendedPropertyPlaceholderConfigurer propertyConfigurer;

	public <T> T getMainSiteService(Class<T> clazz) {
		try {
			String serviceUrl = propertyConfigurer.mergeProperties().getProperty("remote.url");
			return this.getServiceByUrl(serviceUrl, null, clazz);
		} catch (Exception e) {
			logger.error("远程调用错误", e);
		}
		return null;
	}

	/**
	 * 传入节点ID调用指定节点service服务.
	 * 
	 * @param <T>
	 * @param nodeId
	 * @param clazz
	 * @return
	 */
	public <T> T getServiceByNodeId(Integer nodeId, Class<T> clazz) {

		Assert.notNull(nodeId);
		try {
			return this.getServiceByNodeId(nodeId, null, clazz);
		} catch (Exception e) {
			logger.error("远程调用错误", e);
		}
		return null;
	}

	/**
	 * 传入节点ID调用指定节点service服务，可指定beanId.
	 * 
	 * @param nodeId
	 * @param beanId
	 * @param clazz
	 * @return
	 */
	public <T> T getServiceByNodeId(Integer nodeId, String beanId, Class<T> clazz) {

		Assert.notNull(nodeId);
		try {
			return this.getServiceByUrl(null, beanId, clazz);
		} catch (Exception e) {
			logger.error("远程调用错误", e);
		}
		return null;
	}

	/**
	 * 通过url获取远程service，可以指定具体beanId.
	 * 
	 * @param serviceUrl
	 * @param beanId
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getServiceByUrl(String serviceUrl, String beanId, Class<T> clazz) {

		Assert.notNull(serviceUrl);
		try {
			if (serviceUrl.equals(this.currentServiceUrl)) {
				// 直接从本地bean工厂获取
				String beanName = getLocaleServiceName(clazz, beanId);
				logger.debug("请勿本地调用本地节点url:" + serviceUrl + ",beanName:" + beanName);
				return (T) this.beanFactory.getBean(beanName);
			}

			T obj = getRemoteObject(clazz, serviceUrl, beanId);
			return obj;
		} catch (Exception e) {
			logger.error("远程调用错误", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T getRemoteObject(final Class<T> clazz, final String baseURL, final String serviceName) {

		T service = null;
		try {
			String beanName = getServiceName(clazz, serviceName);
			final String url = baseURL + beanName;
			// 创建一个代理对象.
			DqlHttpInvokerProxyFactoryBean bean = new DqlHttpInvokerProxyFactoryBean() {

				@Override
				@SuppressWarnings("rawtypes")
				public Class getServiceInterface() {
					try {
						return Class.forName(clazz.getName());
					} catch (ClassNotFoundException e) {
						logger.error("远程调用错误ClassNotFoundException", e);
					}
					return super.getServiceInterface();
				}

				@Override
				public String getServiceUrl() {
					return url;
				}
			};

			bean.afterPropertiesSet();
			service = (T) bean.getObject();
		} catch (Exception e) {
			logger.error("远程调用错误", e);
		}

		return service;
	}

	private <T> String getServiceName(final Class<T> clazz, final String serviceName) {
		String beanName = serviceName;
		if (beanName == null) {
			// 得到HelloService
			beanName = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
			// 首字母小写,得到helloService
			beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
		}
		return beanName;
	}

	private <T> String getLocaleServiceName(final Class<T> clazz, final String serviceName) {
		String beanName = serviceName;
		if (beanName == null) {
			// 得到HelloService
			beanName = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
			// 首字母小写,得到helloService
			beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
		}
		return beanName;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

		this.beanFactory = beanFactory;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

}
