package com.gxkj.common.cache.aop.advice;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;

import com.gxkj.common.cache.aop.annotation.MethodCache;
import com.gxkj.common.cache.aop.interceptor.AnnotationMethodCacheInterceptor;

 

public class AnnotationMethodCacheAdvice implements org.springframework.aop.PointcutAdvisor,InitializingBean,BeanFactoryAware {
	private AnnotationMethodCacheInterceptor annotationMethodCacheInterceptor;
	private String annotationMethodCacheInterceptorBeanName;
	private BeanFactory beanFactory;
	
	public Advice getAdvice() {
		return annotationMethodCacheInterceptor;
	}
	
	public void setAnnotationMethodCacheInterceptor(
			AnnotationMethodCacheInterceptor annotationMethodCacheInterceptor) {
		this.annotationMethodCacheInterceptor = annotationMethodCacheInterceptor;
	}

	public boolean isPerInstance() {
		return true;
	}

	public void setAnnotationMethodCacheInterceptorBeanName(
			String annotationMethodCacheInterceptorBeanName) {
		this.annotationMethodCacheInterceptorBeanName = annotationMethodCacheInterceptorBeanName;
	}

	public void afterPropertiesSet() throws Exception {
		if(annotationMethodCacheInterceptor == null) {
			annotationMethodCacheInterceptor = (AnnotationMethodCacheInterceptor)beanFactory.getBean(annotationMethodCacheInterceptorBeanName);
		}
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	public Pointcut getPointcut() {
		return pointcut;
	}
	
	Pointcut pointcut = new StaticMethodMatcherPointcut() {
		public boolean matches(Method method, Class<?> targetClass) {
			if(method.isAnnotationPresent(MethodCache.class)) {
				return true;
			}
			return false;
		}
	};
	
	
}
