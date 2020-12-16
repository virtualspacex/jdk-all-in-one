/*
 * @Author: your name
 * @Date: 2020-10-10 14:04:30
 * @LastEditTime: 2020-10-11 23:54:48
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/engine/loader/FieldLoader.java
 */
package com.virtualspacex.middleware.loader;

import java.lang.reflect.Field;

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
class FieldLoader {

	private FieldLoader() {
		throw new IllegalStateException("Utility class");
	}
	
    public static void scanField(Class<?> clazz, Object instance) throws InterpreAnnotationException {
	    Field[] fields = clazz.getDeclaredFields();
	    for (Field field : fields) {
	    	FieldLoader.load(field, instance);
	    }
    }

    private static void load(Field field, Object obj) throws InterpreAnnotationException{
		Class<?> fieldClazz = field.getType();
		
        Object fieldInstence = AnnotationInterpreService.interpretFromField(field);

        if(null != fieldInstence ) {
            field.setAccessible(true);
            try {
				field.set(obj, fieldInstence);
			} catch (Exception e) {
				throw new InterpreAnnotationException(e);
			}
            
            scanField(fieldClazz, fieldInstence);
        }
    }
}