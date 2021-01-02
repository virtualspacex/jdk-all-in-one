/*
 * @Author: your name
 * @Date: 2020-10-10 16:16:48
 * @LastEditTime: 2021-01-01 15:34:09
 * @LastEditors: keiki
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/virtualspacex/batch/interpreter/ExceptionHandlerInterpreter.java
 */
package com.virtualspacex.annotation.interpreter;

import com.google.auto.service.AutoService;
import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;

/**
 * 注釈分析
 *
 * @author yao-chaochao
 * @version 0.0.1
 * @date 2020/08/26
 * @since JDK8
 */
// @InterpreterFor(ExceptionHandler.class)
@AutoService({AnnotationInterpreterInterface.class})
@SupportedAnnotationTypes({"com.virtualspacex.annotation.ExceptionHandler"})
public class ExceptionHandlerInterpreter implements AnnotationInterpreterInterface {

	@Override
	public AspectNode enhanceBehaviour(AspectNode handler, Annotation annotation) throws InterpreAnnotationException {
		return handler;
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