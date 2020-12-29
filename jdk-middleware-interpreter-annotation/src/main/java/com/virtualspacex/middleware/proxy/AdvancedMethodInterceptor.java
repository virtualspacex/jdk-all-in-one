/*
 * @Author: your name
 * @Date: 2020-10-10 16:15:18
 * @LastEditTime: 2020-12-29 09:37:45
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/proxy/factory/Proxy.java
 */
package com.virtualspacex.middleware.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.service.AnnotationInterpreService;

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
public class AdvancedMethodInterceptor implements MethodInterceptor {

    private AspectNode classHandlers;

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
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        Object result = null;
        AspectNode tempHandlerChain = AnnotationInterpreService.interpretFromMethod(method, classHandlers);

        if (null == tempHandlerChain) {
            result = methodProxy.invokeSuper(o, objects);
        } else {
            result = tempHandlerChain.invoke(o, method, objects, methodProxy);
        }

        return result;
    }

    public void push(AspectNode handler) {
        if (handler != null) {
            if (null != classHandlers) {
                handler.setNext(classHandlers);
            }
            classHandlers = handler;
        }
    }
}