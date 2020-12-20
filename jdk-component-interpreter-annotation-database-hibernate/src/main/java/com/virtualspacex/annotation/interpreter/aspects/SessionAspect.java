/*
 * @Author: your name
 * @Date: 2020-12-16 23:28:54
 * @LastEditTime: 2020-12-20 18:11:51
 * @LastEditors: your name
 * @Description: In User Settings Edit
 * @FilePath: /jdk-all-in-one/jdk-component-interpreter-annotation-database/src/main/java/com/virtualspacex/annotation/interpreter/aspects/SessionAspect.java
 */
package com.virtualspacex.annotation.interpreter.aspects;

import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.AspectNodeInvokeThrowable;

import org.hibernate.Session;

import com.virtualspacex.annotation.AutoSession;
import com.virtualspacex.component.hibernate.HibernateUtils;

import net.sf.cglib.proxy.MethodProxy;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 自動印刷ログプロキシクラス
 *
 * @author yao-chaochao
 * @version 0.0.1
 * @className
 * @description
 * @date 2020/08/26
 * @since JDK8
 */
public class SessionAspect extends AspectNode{

    /**
     * プロキシ監視
     *
     * @param o           プロキシオブジェクト
     * @param method      方法
     * @param objects     パラメータ
     * @param methodProxy プロキシ方法
     * @return メソッドの戻り結果
     * @throws Throwable 例外を投げる
     * @since 0.0.1
     */
    public Object invoke(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws AspectNodeInvokeThrowable {

    	Object result = null;
    	Session session = null;
    	
    	int parameterIndex = getAutoSessionIndex(method);
		if(-1 != parameterIndex){
			if(objects[parameterIndex] == null) {
				try {
					session = HibernateUtils.openSession();
				} catch (Exception e) {
					throw new AspectNodeInvokeThrowable("Can not open Session by @AutoSession.");
				}
				objects[parameterIndex] = session;
			}
		} 

		try {
			result = this.next(o, method, objects, methodProxy);
		} finally {
			if(null != session){
				try {
					HibernateUtils.closeSession(session);
				} catch (Exception e) {
					// do nothing
				}
			}
		}
		
        return result;
	}
	
	private int getAutoSessionIndex(Method method){
		int autoSessionIndex = -1;

		Annotation[][] parameterAnnotations = method.getParameterAnnotations();

		if (parameterAnnotations != null && parameterAnnotations.length > 0) {
			for(int i = 0; i < parameterAnnotations.length; i++) {
	        	Annotation[] parameterAnnotation = parameterAnnotations[i];
	        	for(int j = 0; j < parameterAnnotation.length; j++) {
	        		Annotation parAnnotation = parameterAnnotation[j];
	        		if(parAnnotation instanceof AutoSession) {
						autoSessionIndex = i;
						break;
	        		}
				}
				
				if(-1 != autoSessionIndex){
					break;
				}
	        }
		} 
		
		return autoSessionIndex;
	}
}