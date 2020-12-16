/*
 * @Author: your name
 * @Date: 2020-10-10 16:41:08
 * @LastEditTime: 2020-12-14 22:57:52
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/service/AnnotationInterpreService.java
 */
package com.virtualspacex.middleware.loader;

import com.virtualspacex.middleware.exception.InterpreAnnotationException;

/**
 * 注釈分析
 *
 * @author yao-chaochao
 * @version 0.0.1
 * @date 2020/08/26
 * @since JDK8
 */
public class AnnotationEngine {

	private AnnotationEngine() {
		throw new IllegalStateException("Utility class");
	}
	  
	public static void loadInterpreters(String packageOfInterpreters) throws InterpreAnnotationException{
		InterpreterLoader.load(packageOfInterpreters);
	}
	
	public static Object loadClass(Class<?> clazz) throws InterpreAnnotationException{
		return ClassLoader.load(clazz);
	}
}