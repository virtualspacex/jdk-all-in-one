/*
 * @Author: your name
 * @Date: 2020-10-10 16:16:28
 * @LastEditTime: 2020-12-29 10:04:05
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/interpreter/AutoInterpreter.java
 */
package com.virtualspacex.annotation.interpreter;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;

import com.google.auto.service.AutoService;
import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;

/**
 * 注釈分析
 *
 * @author yao-chaochao
 * @version 0.0.1
 * @date 2020/08/26
 * @since JDK8
 */
@AutoService({AnnotationInterpreterInterface.class})
@SupportedAnnotationTypes({"com.virtualspacex.annotation.ConfigBatch", 
							"com.virtualspacex.annotation.Step"})
public class BatchInterpreter implements AnnotationInterpreterInterface {

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
		return true;
	}

}