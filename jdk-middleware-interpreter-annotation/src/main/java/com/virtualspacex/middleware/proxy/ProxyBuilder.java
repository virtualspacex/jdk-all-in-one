/*
 * @Author: your name
 * @Date: 2020-09-29 18:10:30
 * @LastEditTime: 2020-10-11 22:49:43
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/proxy/factory/ProxyBuilder.java
 */
package com.virtualspacex.middleware.proxy;

import com.virtualspacex.middleware.exception.InterpreAnnotationException;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

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
public class ProxyBuilder {

    private ProxyBuilder() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * プロキシオブジェクトを取得する
     *
     * @param clazz プロキシオブジェクトのタイプ
     * @param cb    プロキシクラス
     * @return プロキシオブジェクト
     * @throws InterpreAnnotationException 
     * @since 0.0.1
     */
    public static Object build(Class<?> clazz, Callback cb) throws InterpreAnnotationException {
        Object o = null;
        Enhancer enhancer = new Enhancer();
        try {
            enhancer.setSuperclass(clazz);
            enhancer.setCallback(cb);
            o = enhancer.create();
        } catch (Exception e) {
            throw new InterpreAnnotationException(e);
        }
        return o;
    }
}