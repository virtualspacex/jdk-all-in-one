/*
 * @Author: your name
 * @Date: 2020-10-11 15:13:27
 * @LastEditTime: 2020-10-11 23:38:02
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/interpreter/AutoPropertyInterpreter.java
 */
package com.virtualspacex.annotation.interpreter;

import java.lang.annotation.Annotation;

import com.virtualspacex.middleware.annotation.InterpreterFor;
import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;
import com.virtualspacex.annotation.AutoProperty;
import com.virtualspacex.util.property.PropertyManager;

@InterpreterFor(AutoProperty.class)
public class AutoPropertyInterpreter implements AnnotationInterpreterInterface {

    @Override
    public AspectNode enhanceBehaviour(AspectNode existNode, Annotation annotation) throws InterpreAnnotationException {
        // TODO Auto-generated method stub
        return existNode;
    }

    @Override
    public Object enhanceAttribute(Class<?> clazz, Object existInstance, Annotation annotation)
            throws InterpreAnnotationException {
        String key = ((AutoProperty) annotation).value();
        return PropertyManager.getProperty(key);
    }

	@Override
	public boolean autoProxy(Annotation annotation) {
		// TODO Auto-generated method stub
		return false;
	}
}
