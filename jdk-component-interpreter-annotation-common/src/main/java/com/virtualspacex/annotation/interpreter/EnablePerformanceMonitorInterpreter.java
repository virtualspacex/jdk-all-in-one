package com.virtualspacex.annotation.interpreter;

import java.lang.annotation.Annotation;

import com.virtualspacex.annotation.EnablePerformanceMonitor;
import com.virtualspacex.annotation.interpreter.aspects.PerformanceMonitorAspect;
import com.virtualspacex.middleware.annotation.InterpreterFor;
import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;
import com.virtualspacex.middleware.loader.GlobalAspectNodeRegister;

@InterpreterFor(EnablePerformanceMonitor.class)
public class EnablePerformanceMonitorInterpreter implements AnnotationInterpreterInterface {

    @Override
    public AspectNode enhanceBehaviour(AspectNode existNode, Annotation annotation) throws InterpreAnnotationException {
        GlobalAspectNodeRegister.register(PerformanceMonitorAspect.class);
		return existNode;
    }

    @Override
    public Object enhanceAttribute(Class<?> clazz, Object existInstance, Annotation annotation)
            throws InterpreAnnotationException {
        return existInstance;
    }

    @Override
    public boolean autoProxy(Annotation annotation) {
        return false;
    }
    
}