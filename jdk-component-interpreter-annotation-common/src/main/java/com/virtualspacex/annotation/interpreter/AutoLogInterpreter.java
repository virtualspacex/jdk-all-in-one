/*
 * @Author: your name
 * @Date: 2020-10-10 16:16:38
 * @LastEditTime: 2021-01-01 15:33:10
 * @LastEditors: keiki
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/interpreter/AutoLogInterpreter.java
 */
package com.virtualspacex.annotation.interpreter;

import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;
import com.google.auto.service.AutoService;
import com.virtualspacex.annotation.interpreter.aspects.LogAspect;

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
// @InterpreterFor(AutoLog.class)
@AutoService({AnnotationInterpreterInterface.class})
@SupportedAnnotationTypes({"com.virtualspacex.annotation.AutoLog"})
public class AutoLogInterpreter implements AnnotationInterpreterInterface {

	@Override
	public AspectNode enhanceBehaviour(AspectNode handler, Annotation annotation) throws InterpreAnnotationException {
		AspectNode topHandler = new LogAspect();
		if (null != handler) {
			topHandler.setNext(handler);
		}
		return topHandler;
	}

	@Override
	public Object enhanceAttribute(Class<?> clazz, Object existInstance, Annotation annotation)
			throws InterpreAnnotationException {
		return existInstance;
	}

	@Override
	public boolean autoProxy(Annotation annotation) {
		return true;
	}

}