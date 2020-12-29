/*
 * @Author: your name
 * @Date: 2020-12-16 19:15:07
 * @LastEditTime: 2020-12-29 10:03:53
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \jdk-all-in-one\jdk-framework-application-batch\src\main\java\com\virtualspacex\annotation\interpreter\BatchConfigurationInterpreter.java
 */
package com.virtualspacex.annotation.interpreter;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;

import com.google.auto.service.AutoService;
import com.virtualspacex.annotation.ConfigBatch;
import com.virtualspacex.batch.BatchContainer;
import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;

@AutoService({AnnotationInterpreterInterface.class})
@SupportedAnnotationTypes({"com.virtualspacex.annotation.ConfigBatch"})
public class BatchConfigurationInterpreter implements AnnotationInterpreterInterface {

	@Override
	public AspectNode enhanceBehaviour(AspectNode existNode, Annotation annotation) throws InterpreAnnotationException {
		BatchContainer.config(((ConfigBatch)annotation).value());
		return null;
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
