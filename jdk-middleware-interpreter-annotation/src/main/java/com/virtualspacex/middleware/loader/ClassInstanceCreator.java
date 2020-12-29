/*
 * @Author: your name
 * @Date: 2020-10-10 14:04:02
 * @LastEditTime: 2020-12-28 19:39:48
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/engine/loader/ClassLoader.java
 */
package com.virtualspacex.middleware.loader;

import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.service.AnnotationInterpreService;

/**
 * 注釈分析
 *
 * @author yao-chaochao
 * @version 0.0.1
 * @date 2020/08/26
 * @since JDK8
 */
class ClassInstanceCreator {

	private ClassInstanceCreator() {
		throw new IllegalStateException("Utility class");
	}

    public static Object load(Class<?> clazz) throws InterpreAnnotationException{
    	Object instance = AnnotationInterpreService.interpretFromClass(clazz);
    	FieldInstanceCreator.newInstanceForField(clazz, instance);
	    return instance;
	}
}