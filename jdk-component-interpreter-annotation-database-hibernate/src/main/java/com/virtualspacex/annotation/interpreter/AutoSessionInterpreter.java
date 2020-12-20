/*
 * @Author: your name
 * @Date: 2020-10-10 16:42:52
 * @LastEditTime: 2020-10-11 23:38:57
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/interpreter/AutoSessionInterpreter.java
 */
package com.virtualspacex.annotation.interpreter;

import com.virtualspacex.middleware.annotation.InterpreterFor;
import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;
import com.virtualspacex.annotation.AutoSession;
import com.virtualspacex.annotation.interpreter.aspects.SessionAspect;

import java.lang.annotation.Annotation;

/**
 * 注釈分析
 *
 * @author yao-chaochao
 * @version 0.0.1
 * @date 2020/08/26
 * @since JDK8
 */
@InterpreterFor(AutoSession.class)
public class AutoSessionInterpreter implements AnnotationInterpreterInterface {

	@Override
	public AspectNode enhanceBehaviour(AspectNode handler, Annotation annotation) throws InterpreAnnotationException {
		// TODO Auto-generated method stub
		AspectNode topHandler = new SessionAspect();
		if (null != handler) {
			topHandler.setNext(handler);
		}
		return topHandler;
	}

	@Override
	public Object enhanceAttribute(Class<?> clazz, Object existInstance, Annotation annotation)
			throws InterpreAnnotationException {
		// TODO Auto-generated method stub
		return existInstance;
	}

	@Override
	public boolean autoProxy(Annotation annotation) {
		// TODO Auto-generated method stub
		return false;
	}
}