/*
 * @Author: your name
 * @Date: 2020-12-28 13:15:44
 * @LastEditTime: 2020-12-29 09:30:40
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \jdk-all-in-one\jdk-middleware-interpreter-annotation\src\main\java\com\virtualspacex\middleware\loader\Enhancer.java
 */
package com.virtualspacex.middleware.loader;

import com.virtualspacex.middleware.exception.InterpreAnnotationException;

public class AdvancedInstanceCreator {
  
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
}