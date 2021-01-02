/*
 * @Author: your name
 * @Date: 2020-10-10 16:16:44
 * @LastEditTime: 2021-01-01 15:34:42
 * @LastEditors: keiki
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/interpreter/AutoTransactionInterpreter.java
 */
package com.virtualspacex.annotation.interpreter;

import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;
import com.google.auto.service.AutoService;
import com.virtualspacex.annotation.interpreter.aspects.TransactionAspect;

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
// @InterpreterFor(AutoTransaction.class)
@AutoService({AnnotationInterpreterInterface.class})
@SupportedAnnotationTypes(value = "com.virtualspacex.annotation.AutoTransaction")
public class AutoTransactionInterpreter implements AnnotationInterpreterInterface {

	@Override
	public AspectNode enhanceBehaviour(AspectNode handler, Annotation annotation) throws InterpreAnnotationException {
		AspectNode topHandler = new TransactionAspect();
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
		return false;
	}
}