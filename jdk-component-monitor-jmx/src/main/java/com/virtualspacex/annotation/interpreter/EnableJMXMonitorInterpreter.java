package com.virtualspacex.annotation.interpreter;

import java.lang.annotation.Annotation;

import com.virtualspacex.annotation.EnableJMXMonitor;
import com.virtualspacex.middleware.annotation.InterpreterFor;
import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;
import com.virtualspacex.monitor.JmxMonitor;

@InterpreterFor(EnableJMXMonitor.class)
public class EnableJMXMonitorInterpreter implements AnnotationInterpreterInterface{

	public AspectNode enhanceBehaviour(AspectNode existNode, Annotation annotation) throws InterpreAnnotationException {
		
		try {
			JmxMonitor.startJmxMonitor(((EnableJMXMonitor)annotation).port());
		} catch (Exception e) {
			throw new InterpreAnnotationException(e);
		}
		
		return null;
	}

	public Object enhanceAttribute(Class<?> clazz, Object existInstance, Annotation annotation)
			throws InterpreAnnotationException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean autoProxy(Annotation annotation) {
		// TODO Auto-generated method stub
		return false;
	}

}
