/*
 * @Author: keiki
 * @Date: 2020-12-16 23:28:54
 * @LastEditTime: 2021-01-01 15:38:48
 * @LastEditors: keiki
 * @Description: 
 */
package com.virtualspacex.annotation.interpreter;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;

import com.google.auto.service.AutoService;
import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;
import com.virtualspacex.monitor.JmxMonitor;

// @InterpreterFor(MBean.class)
@AutoService({AnnotationInterpreterInterface.class})
@SupportedAnnotationTypes(value = "com.virtualspacex.annotation.MBean")
public class MBeanInterpreter implements AnnotationInterpreterInterface{

	public AspectNode enhanceBehaviour(AspectNode existNode, Annotation annotation) throws InterpreAnnotationException {
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
		return false;
	}

}
