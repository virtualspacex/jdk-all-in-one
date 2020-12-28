/*
 * @Author: your name
 * @Date: 2020-12-28 13:15:44
 * @LastEditTime: 2020-12-28 17:42:40
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \jdk-all-in-one\jdk-middleware-interpreter-annotation\src\main\java\com\virtualspacex\middleware\loader\Enhancer.java
 */
package com.virtualspacex.middleware.loader;

import com.virtualspacex.middleware.exception.InterpreAnnotationException;

public class Enhancer {
  
    /**
     * @description: 
     * @param {*}
     * @return {*}
     */    
    public static void loadInterpreters() throws InterpreAnnotationException {
		  AnnotationInterpretersLoader.load();
    }
    
    /**
     * @description: 
     * @param {Class<?>} clazz
     * @return {*}
     */
    public static Object from(Class<?> clazz) throws InterpreAnnotationException {
      return ClassInstanceCreator.load(clazz);
    }

    /**
     * @description: 
     * @param {Object} obj
     * @return {*}
     */    
    public static Object from(Object obj) throws InterpreAnnotationException {
      return obj;
    }
}