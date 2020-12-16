/*
 * @Author: your name
 * @Date: 2020-10-10 16:16:12
 * @LastEditTime: 2020-12-14 22:58:10
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/interpreter/AbstractAnnotationInterpreter.java
 */
package com.virtualspacex.middleware.interpreter;

import java.lang.annotation.Annotation;

import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;

/**
 * 注釈分析
 *
 * @author yao-chaochao
 * @version 0.0.1
 * @date 2020/08/26
 * @since JDK8
 */
public interface AnnotationInterpreterInterface {

    /**
     * @description:
     * @param {type}
     * @return {type}
     */
    public abstract AspectNode enhanceBehaviour(AspectNode existNode, Annotation annotation)
            throws InterpreAnnotationException;

    /**
     * @description:
     * @param {type}
     * @return {type}
     */
    public abstract Object enhanceAttribute(Class<?> clazz, Object existInstance, Annotation annotation)
            throws InterpreAnnotationException;
    
    /**
     * @description:
     * @param {type}
     * @return {type}
     */
    public abstract boolean autoProxy(Annotation annotation);
}