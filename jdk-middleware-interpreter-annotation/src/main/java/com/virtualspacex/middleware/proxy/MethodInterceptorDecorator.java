/*
 * @Author: your name
 * @Date: 2020-10-10 16:15:52
 * @LastEditTime: 2020-12-29 09:43:54
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/proxy/factory/ProxyDecorator.java
 */
package com.virtualspacex.middleware.proxy;

import com.virtualspacex.middleware.aspect.AspectNode;

/**
 * エージェントファクトリー
 *
 * @author yao-chaochao
 * @version 0.0.1
 * @className
 * @description
 * @date 2020/08/26
 * @since JDK8
 */
public class MethodInterceptorDecorator {

    private MethodInterceptorDecorator() {
        throw new IllegalStateException("Utility class");
    }
      
    /**
     * プロキシオブジェクトを取得する
     *
     * @param clazz プロキシオブジェクトのタイプ
     * @param cb    プロキシクラス
     * @return プロキシオブジェクト
     * @since 0.0.1
     */
    public static AdvancedMethodInterceptor decorate(AdvancedMethodInterceptor proxy, AspectNode handler) {
    	proxy.push(handler);
        return proxy;
    }
}