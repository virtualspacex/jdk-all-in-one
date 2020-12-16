package com.virtualspacex.exception.handler;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * キャッチされない例外処理
 *
 * @author yao-chaochao
 * @version 0.0.1
 * @date 2020/08/26
 * @since JDK8
 */
public class DefaultUncaughtExceptionHandler implements UncaughtExceptionHandler {

    /**
     * キャッチされない例外処理
     *
     * @param t 糸
     * @param e 異常な
     * @since 0.0.1
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Exception caught: " + e.getMessage());
    }

}

