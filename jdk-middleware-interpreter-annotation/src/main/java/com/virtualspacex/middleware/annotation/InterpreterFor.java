/*
 * @Author: your name
 * @Date: 2020-10-11 19:27:19
 * @LastEditTime: 2020-12-29 09:56:55
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/annotation/AnnotationInterpreter.java
 */
package com.virtualspacex.middleware.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Deprecated
public @interface InterpreterFor {
    Class<? extends Annotation>[] value();
}
