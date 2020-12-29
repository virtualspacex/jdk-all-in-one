/*
 * @Author: your name
 * @Date: 2020-10-10 16:16:00
 * @LastEditTime: 2020-12-29 09:44:34
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/proxy/factory/ProxyFactory.java
 */
package com.virtualspacex.middleware.proxy;

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
public class MethodInterceptorFactory {

	private MethodInterceptorFactory() {
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
    public static AdvancedMethodInterceptor getMethodInterceptor() {
        return new AdvancedMethodInterceptor();
    }
}