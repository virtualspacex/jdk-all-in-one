/*
 * @Author: your name
 * @Date: 2020-10-10 16:41:08
 * @LastEditTime: 2020-12-29 09:45:21
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/service/AnnotationInterpreService.java
 */
package com.virtualspacex.middleware.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;
import com.virtualspacex.middleware.interpreter.AnnotationInterpreterInterface;
import com.virtualspacex.middleware.loader.GlobalAspectNodeRegister;
import com.virtualspacex.middleware.proxy.AdvancedMethodInterceptor;
import com.virtualspacex.middleware.proxy.ProxyBuilder;
import com.virtualspacex.middleware.proxy.MethodInterceptorDecorator;
import com.virtualspacex.middleware.proxy.MethodInterceptorFactory;

import net.sf.cglib.proxy.Callback;

/**
 * 注釈分析
 *
 * @author yao-chaochao
 * @version 0.0.1
 * @date 2020/08/26
 * @since JDK8
 */
public class AnnotationInterpreService {

	private static HashMap<Class<? extends Annotation>, AnnotationInterpreterInterface> InterpreterMap = new HashMap<>();

	private AnnotationInterpreService() {
		throw new IllegalStateException("Utility class");
	}
	  
	public static Object interpretFromField(Field field) throws InterpreAnnotationException {
		Object ret = null;

		Class<?> fieldClazz = field.getType();
		Annotation[] fieldAnnotations = field.getAnnotations();
		Annotation[] classAnnotations = fieldClazz.getAnnotations();

		Annotation[] annotations = combine(fieldAnnotations, classAnnotations);
		ret = interpretAndstrengthen(annotations, fieldClazz);

		return ret;
	}

	/**
	 * @description: 
	* @param {type} 
	* @return {type} 
	*/ 
	public static Object interpretFromClass(Class<?> clazz) throws InterpreAnnotationException {
		Object ret = null;

		Annotation[] annotations = clazz.getAnnotations();
		ret = interpretAndstrengthen(annotations, clazz);

		return ret;
	}

	public static AspectNode interpretFromMethod(Method method, AspectNode handler) throws InterpreAnnotationException {

		AspectNode tempHandlerChain = handler;
		tempHandlerChain = getMethodHandlerChain(method, tempHandlerChain);
		tempHandlerChain = getParameterHandlerChain(method, tempHandlerChain);

		return tempHandlerChain;
	}

	private static Object interpretAndstrengthen(Annotation[] annotations, Class<?> clazz) 
			throws InterpreAnnotationException {

		Object ret = null;
		boolean isNeedEnhance = false;
		
		AspectNode aspectode = GlobalAspectNodeRegister.getGlobalAspectNode();

		//行为增强（面向类和属性）
		for (Annotation anno : annotations) {
			AnnotationInterpreterInterface interpreter = InterpreterMap.get(anno.annotationType());
			if (null != interpreter) {
				if(interpreter.autoProxy(anno)) {
					isNeedEnhance = true;
				}
				aspectode = interpreter.enhanceBehaviour(aspectode, anno);
			}
		}

		//生成实例
		if(isNeedEnhance) {
			AdvancedMethodInterceptor methodInterceptor = MethodInterceptorFactory.getMethodInterceptor();
			MethodInterceptorDecorator.decorate(methodInterceptor, aspectode);
			ret = ProxyBuilder.build(clazz, (Callback) methodInterceptor);
		}

		//增强实例属性
		for (Annotation anno : annotations) {
			AnnotationInterpreterInterface interpreter = InterpreterMap.get(anno.annotationType());
			if (null != interpreter) {
				ret = interpreter.enhanceAttribute(clazz, ret, anno);
			}
		}

		return ret;
	}

	private static AspectNode getParameterHandlerChain(Method method, AspectNode handlerChina)
			throws InterpreAnnotationException {

		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		if (parameterAnnotations != null && parameterAnnotations.length > 0) {
			for (Annotation[] parameterAnnotation : parameterAnnotations) {
				for (Annotation parAnnotation : parameterAnnotation) {
					AnnotationInterpreterInterface interpreter = InterpreterMap.get(parAnnotation.annotationType());
					if (null != interpreter) {
						handlerChina = interpreter.enhanceBehaviour(handlerChina, parAnnotation);
					}
				}
			}
		}

		return handlerChina;
	}

	private static AspectNode getMethodHandlerChain(Method method, AspectNode existChain)
			throws InterpreAnnotationException {

		Annotation[] parameterAnnotations = method.getAnnotations();
		if (parameterAnnotations != null && parameterAnnotations.length > 0) {
			for (Annotation parAnnotation : parameterAnnotations) {
				AnnotationInterpreterInterface interpreter = InterpreterMap.get(parAnnotation.annotationType());
				if (null != interpreter) {
					existChain = interpreter.enhanceBehaviour(existChain, parAnnotation);
				}
			}
		}

		return existChain;
	}

	public static void register(Class<? extends Annotation> annotion, AnnotationInterpreterInterface handlerClazz) {
		InterpreterMap.put(annotion, handlerClazz);
	}

    public static Annotation[] combine(Annotation[] array1, Annotation[] array2){

        Annotation[] ret = null;

        if(null == array1 && null == array2){
            return ret;
        }

        if(null != array1 && null == array2){
            return array1;
        }

        if(null == array1){
            return array2;
        }

        ret = new Annotation[array1.length + array2.length];

        int i = 0;

        for(Annotation object:array1){
            ret[i] = object;
            i++;
        }

        for(Annotation object :array2){
            ret[i] = object;
            i++;
        }

        return ret;
    }
}