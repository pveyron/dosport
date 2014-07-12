package com.dosport.springframework.remoting.httpinvoker;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.stereotype.Component;
/**
 * 远程调用注解.
 * 
 * @author pwl
 * 
 */

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Remote {

	Class<?> serviceExporter() default HttpInvokerServiceExporter.class;
}
