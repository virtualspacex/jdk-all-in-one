/*
 * @Author: your name
 * @Date: 2020-10-10 16:16:20
 * @LastEditTime: 2020-10-11 23:36:46
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/interpreter/AutoExceptionHandlerInterpreter.java
 */
package com.virtualspacex.annotation.interpreter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.virtualspacex.middleware.annotation.InterpreterFor;
import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;
import com.virtualspacex.annotation.AutoExceptionHandler;
import com.virtualspacex.annotation.ExceptionHandler;
import com.virtualspacex.exception.handler.DefaultExceptionHandler;

import java.lang.annotation.Annotation;

/**
 * 注釈分析
 *
 * @author yao-chaochao
 * @version 0.0.1
 * @date 2020/08/26
 * @since JDK8
 */
@InterpreterFor(AutoExceptionHandler.class)
public class AutoExceptionHandlerInterpreter implements AnnotationInterpreterInterface {

    @Override
    public AspectNode enhanceBehaviour(AspectNode handler, Annotation annotation) throws InterpreAnnotationException {
        // TODO Auto-generated method stub
        return handler;
    }

    @Override
    public Object enhanceAttribute(Class<?> clazz, Object existInstance, Annotation annotation)
            throws InterpreAnnotationException {
        try {
            Field targetField = null;
            Class<?> targetClass = clazz;
            Method[] methods = clazz.getDeclaredMethods();
            HashMap<Class<?>, Method> invokeTable = new HashMap<>();

            while (!(DefaultExceptionHandler.class.getName().equals(targetClass.getName()))) {
                targetClass = targetClass.getSuperclass();
                if (Object.class.equals(targetClass.getName())) {
                    break;
                }
            }

            Field[] fields = targetClass.getDeclaredFields();

            for (Field field : fields) {
                if ("invokeTable".equals(field.getName())) {
                    targetField = field;
                    break;
                }
            }

            if (targetField != null) {

                for (int i = 0; i < methods.length; i++) {
                    ExceptionHandler anno = methods[i].getAnnotation(ExceptionHandler.class);
                    if (anno != null) {
                        invokeTable.put(anno.value(), methods[i]);
                    }
                }

                targetField.setAccessible(true);
                targetField.set(existInstance, invokeTable);
            }
            return existInstance;
        } catch (IllegalAccessException e) {
            throw new InterpreAnnotationException(e);
        }
    }

	@Override
	public boolean autoProxy(Annotation annotation) {
		return true;
	}
}