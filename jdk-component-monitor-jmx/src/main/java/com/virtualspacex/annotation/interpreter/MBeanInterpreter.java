package com.virtualspacex.annotation.interpreter;

import java.lang.annotation.Annotation;

import com.virtualspacex.annotation.MBean;
import com.virtualspacex.middleware.annotation.InterpreterFor;
import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;
import com.virtualspacex.monitor.JmxMonitor;

@InterpreterFor(MBean.class)
public class MBeanInterpreter implements AnnotationInterpreterInterface{

	public AspectNode enhanceBehaviour(AspectNode existNode, Annotation annotation) throws InterpreAnnotationException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object enhanceAttribute(Class<?> clazz, Object existInstance, Annotation annotation)
			throws InterpreAnnotationException {
		Object bean = existInstance;
		
		try {
			if(null == bean) {
				bean = clazz.newInstance();
			}
			
			JmxMonitor.registMBean(bean);
			
		} catch(Exception e) {
			throw new InterpreAnnotationException(e);
		}
		return bean;
	}

	public boolean autoProxy(Annotation annotation) {
		// TODO Auto-generated method stub
		return false;
	}

}
