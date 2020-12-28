package com.virtualspacex.annotation.interpreter;

import java.lang.annotation.Annotation;

import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;

import com.google.auto.service.AutoService;
import com.virtualspacex.annotation.BatchExecutor;
import com.virtualspacex.batch.BatchContainer;
import com.virtualspacex.middleware.annotation.InterpreterFor;
import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;

@AutoService({AnnotationInterpreterInterface.class, Processor.class})
@InterpreterFor({BatchExecutor.class})
@SupportedAnnotationTypes({"com.virtualspacex.annotation.BatchExecutor"})
public class BatchApplicationInterpreter implements AnnotationInterpreterInterface {

	@Override
	public AspectNode enhanceBehaviour(AspectNode existNode, Annotation annotation) throws InterpreAnnotationException {

		new BatchContainer().run(((BatchExecutor)annotation).value());
		
		return null;
	}

	@Override
	public Object enhanceAttribute(Class<?> clazz, Object existInstance, Annotation annotation)
			throws InterpreAnnotationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean autoProxy(Annotation annotation) {
		// TODO Auto-generated method stub
		return false;
	}

}
