/*
 * @Author: your name
 * @Date: 2020-10-11 15:13:27
 * @LastEditTime: 2021-01-01 15:33:25
 * @LastEditors: keiki
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/interpreter/AutoPropertyInterpreter.java
 */
package com.virtualspacex.annotation.interpreter;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;

import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;
import com.google.auto.service.AutoService;
import com.virtualspacex.annotation.AutoProperty;
import com.virtualspacex.util.property.PropertyManager;

// @InterpreterFor(AutoProperty.class)
@AutoService({AnnotationInterpreterInterface.class})
@SupportedAnnotationTypes({"com.virtualspacex.annotation.AutoProperty"})
public class AutoPropertyInterpreter implements AnnotationInterpreterInterface {

    @Override
    public AspectNode enhanceBehaviour(AspectNode existNode, Annotation annotation) throws InterpreAnnotationException {
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
		return false;
	}
}
