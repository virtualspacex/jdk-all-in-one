/*
 * @Author: your name
 * @Date: 2020-10-11 23:07:25
 * @LastEditTime: 2021-01-01 15:42:59
 * @LastEditors: keiki
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/engine/loader/InterpreterLoader.java
 */
package com.virtualspacex.middleware.loader;

import java.lang.annotation.Annotation;
import java.util.ServiceLoader;
// import java.util.Set;

import javax.annotation.processing.SupportedAnnotationTypes;

// import com.virtualspacex.middleware.annotation.InterpreterFor;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;
import com.virtualspacex.middleware.service.AnnotationInterpreService;

// import org.reflections.Reflections;

class AnnotationInterpretersLoader {

    private AnnotationInterpretersLoader() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @description: 
     * @param {String} packageName
     * @return {*}
     */    
    // public static void load(String packageName) throws InterpreAnnotationException {
    //     Reflections reflections = new Reflections(packageName);
    //     // 获取带Handler注解的类
    //     Set<Class<?>> classList = reflections.getTypesAnnotatedWith(InterpreterFor.class);
    //     for (Class<?> clazz : classList) {
    //         InterpreterFor anno = clazz.getAnnotation(InterpreterFor.class);
    //         Class<? extends Annotation>[] targetAnnos = anno.value();
    //         if (null != targetAnnos && targetAnnos.length > 0) {
    //             for (Class<? extends Annotation> targetAnno : targetAnnos) {
    //                 try {
    //                     AnnotationInterpreService.register(targetAnno,
    //                             (AnnotationInterpreterInterface) clazz.newInstance());
    //                 } catch (InstantiationException | IllegalAccessException e) {
    //                     throw new InterpreAnnotationException(e);
    //                 }
    //             }
    //         }
    //     }
    // }

    /**
     * @description: 
     * @param {*}
     * @return {*}
     */    
    public static void load() throws InterpreAnnotationException {

        ServiceLoader<AnnotationInterpreterInterface> annotationInterpreters = ServiceLoader
                .load(AnnotationInterpreterInterface.class);

        for (AnnotationInterpreterInterface annotationInterpreter : annotationInterpreters) {
            SupportedAnnotationTypes supportedAnnotationTypes = annotationInterpreter.getClass()
                    .getAnnotation(SupportedAnnotationTypes.class);
            if (null == supportedAnnotationTypes)
                throw new InterpreAnnotationException(
                        "you should add SupportedAnnotationTypes by @SupportedAnnotationTypes on "
                                + annotationInterpreter.getClass());
            String[] classArray = supportedAnnotationTypes.value();
            for (String annotationClassName : classArray) {
                Class<?> annotationClass = null;
                try {
                    annotationClass = Class.forName(annotationClassName);
                    if(Annotation.class.isAssignableFrom(annotationClass)){
                        AnnotationInterpreService.register((Class<? extends Annotation>) annotationClass,
                                annotationInterpreter);
                    } else {
                        throw new InterpreAnnotationException("The values of @SupportedAnnotationTypes should be Annotation class name.");
                    }
                } catch (ClassNotFoundException e) {
                    throw new InterpreAnnotationException(e);
                }
            }
        }
    }
}
