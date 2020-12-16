/*
 * @Author: your name
 * @Date: 2020-10-10 16:14:56
 * @LastEditTime: 2020-12-14 22:58:28
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/proxy/handlers/AspectHandlerNode.java
 */
package com.virtualspacex.middleware.aspect;

import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

import com.virtualspacex.middleware.exception.AspectNodeInvokeThrowable;

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
public abstract class AspectNode {

    private AspectNode nextHandler;

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
    public Object next(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws AspectNodeInvokeThrowable {
        Object retObj;

        try {
            if (null != nextHandler) {
                retObj = nextHandler.invoke(o, method, objects, methodProxy);
            } else {
                retObj = methodProxy.invokeSuper(o, objects);
            }
        } catch (Throwable e) {
            throw new AspectNodeInvokeThrowable(e);
        }

        return retObj;
    }

    public void setNext(AspectNode next) {
        nextHandler = next;
    }

    public AspectNode getNext() {
        return nextHandler;
    }

    public abstract Object invoke(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws AspectNodeInvokeThrowable;

}