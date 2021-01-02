/*
 * @Author: your name
 * @Date: 2020-12-16 19:15:03
 * @LastEditTime: 2021-01-01 15:34:00
 * @LastEditors: keiki
 * @Description: In User Settings Edit
 * @FilePath: \jdk-all-in-one\jdk-component-interpreter-annotation-common\src\main\java\com\virtualspacex\annotation\interpreter\EnablePerformanceMonitorInterpreter.java
 */
package com.virtualspacex.annotation.interpreter;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;

import com.google.auto.service.AutoService;
import com.virtualspacex.annotation.interpreter.aspects.PerformanceMonitorAspect;
import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;
import com.virtualspacex.middleware.loader.GlobalAspectNodeRegister;

// @InterpreterFor(EnablePerformanceMonitor.class)
@AutoService({AnnotationInterpreterInterface.class})
@SupportedAnnotationTypes({"com.virtualspacex.annotation.EnablePerformanceMonitor"})
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