package com.dosport.springframework.remoting.httpinvoker;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationUtils;

public class RemoteAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements
		PriorityOrdered {

	private static ApplicationContext applicationContext = null;

	private static final String REMOTION_BEAN_POSTFIX = "Remoting";

	private int order = Ordered.LOWEST_PRECEDENCE - 1;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		Remote remote = AnnotationUtils.findAnnotation(bean.getClass(), Remote.class);

		Object resultBean = null;
		if (null != remote) {
			DqlHttpInvokerServiceExporter httpInvokerServiceExporter = new DqlHttpInvokerServiceExporter();
			// httpInvokerServiceExporter.setServiceInterface(remote.remoteInterface());
			httpInvokerServiceExporter.setService(bean);
			httpInvokerServiceExporter.afterPropertiesSet();
			resultBean = httpInvokerServiceExporter;
			// this.registerRemotingBeanDefinition(httpInvokerServiceExporter,
			// beanName);
		}

		return resultBean;
	}

	private void registerRemotingBeanDefinition(Object bean, String beanName) {

		String remotingBeanName = beanName + REMOTION_BEAN_POSTFIX;
		ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) RemoteAnnotationBeanPostProcessor.applicationContext;
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
		if (!beanFactory.containsBean(beanName)) {
			BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(bean.getClass());
			bdb.setScope("prototype");
			beanFactory.registerBeanDefinition(remotingBeanName, bdb.getBeanDefinition());
		}
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int getOrder() {
		return order;
	}

}
