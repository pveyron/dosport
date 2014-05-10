package com.dosport.remoting.httpinvoker;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationUtils;

public class RemoteAnnotationBeanPostProcessor extends
		InstantiationAwareBeanPostProcessorAdapter implements PriorityOrdered {

	private int order = Ordered.LOWEST_PRECEDENCE - 1;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {

		Remote remote = AnnotationUtils.findAnnotation(bean.getClass(),
				Remote.class);

		Object resultBean = bean;

		if (null != remote) {

			DqlHttpInvokerServiceExporter httpInvokerServiceExporter = new DqlHttpInvokerServiceExporter();
			httpInvokerServiceExporter.setServiceInterface(remote
					.remoteInterface());
			httpInvokerServiceExporter.setService(bean);
			httpInvokerServiceExporter.afterPropertiesSet();
			resultBean = httpInvokerServiceExporter;
		}

		return resultBean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
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
