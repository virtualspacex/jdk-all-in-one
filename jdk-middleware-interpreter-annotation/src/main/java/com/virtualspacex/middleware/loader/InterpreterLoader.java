/*
 * @Author: your name
 * @Date: 2020-10-11 23:07:25
 * @LastEditTime: 2020-12-14 22:58:02
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/engine/loader/InterpreterLoader.java
 */
package com.virtualspacex.middleware.loader;

import java.lang.annotation.Annotation;
import java.util.Set;

import com.virtualspacex.middleware.annotation.InterpreterFor;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;
import com.virtualspacex.middleware.service.AnnotationInterpreService;

import org.reflections.Reflections;

class InterpreterLoader {

    private InterpreterLoader() {
        throw new IllegalStateException("Utility class");
    }

    public static void load(String packageName) throws InterpreAnnotationException {
        Reflections reflections = new Reflections(packageName);
        // 获取带Handler注解的类
        Set<Class<?>> classList = reflections.getTypesAnnotatedWith(InterpreterFor.class);
        for (Class<?> clazz : classList) {
            InterpreterFor anno = clazz.getAnnotation(InterpreterFor.class);
            Class<? extends Annotation>[] targetAnnos = anno.value();
            if (null != targetAnnos && targetAnnos.length > 0) {
            	for(Class<? extends Annotation> targetAnno : targetAnnos) {
            		try {
                        AnnotationInterpreService.register(targetAnno,
                                (AnnotationInterpreterInterface) clazz.newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new InterpreAnnotationException(e);
                    }
            	}
            }
        }
    }
}
