/*
 * @Author: your name
 * @Date: 2020-10-11 19:42:56
 * @LastEditTime: 2020-10-11 22:29:39
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/interpreter/AnnotationInterpreterIntrpreter.java
 */
package com.virtualspacex.middleware.interpreter;

import java.lang.annotation.Annotation;

import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;


public class AbstractAnnotationInterpreter implements AnnotationInterpreterInterface {

    @Override
    public AspectNode enhanceBehaviour(AspectNode existNode, Annotation annotation) throws InterpreAnnotationException {
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
