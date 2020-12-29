/*
 * @Author: your name
 * @Date: 2020-12-16 19:15:07
 * @LastEditTime: 2020-12-29 10:03:42
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \jdk-all-in-one\jdk-framework-application-batch\src\main\java\com\virtualspacex\annotation\interpreter\BatchApplicationInterpreter.java
 */
package com.virtualspacex.annotation.interpreter;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;

import com.google.auto.service.AutoService;
import com.virtualspacex.annotation.BatchExecutor;
import com.virtualspacex.batch.BatchContainer;
import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;

@AutoService({AnnotationInterpreterInterface.class})
@SupportedAnnotationTypes({"com.virtualspacex.annotation.BatchExecutor"})
public class BatchApplicationInterpreter implements AnnotationInterpreterInterface {

	@Override
	public AspectNode enhanceBehaviour(AspectNode existNode, Annotation annotation) throws InterpreAnnotationException {
		new BatchContainer().run(((BatchExecutor)annotation).value());
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
