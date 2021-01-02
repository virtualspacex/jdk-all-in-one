/*
 * @Author: keiki
 * @Date: 2020-12-16 23:28:54
 * @LastEditTime: 2021-01-01 15:49:30
 * @LastEditors: keiki
 * @Description: 
 */
package com.virtualspacex.exception.handler;

import static com.virtualspacex.util.logger.LoggerFactory.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * グローバル例外処理基本クラス
 *
 * @author yao-chaochao
 * @version 0.0.1
 * @className
 * @description
 * @date 2020/08/26
 * @since JDK8
 */
public class DefaultExceptionHandler {

    private HashMap<Class<?>, Method> invokeTable;

    /**
     * 扱う
     *
     * @param e 異常な
     * @since 0.0.1
     */
    public final void handle(Throwable e) {
        System.out.print(e.getClass().getName());
        Method method = invokeTable.get(e.getClass());
        try {
            if (method != null) {
                method.invoke(this, e);
            } else {
                throwable(e);
            }
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (IllegalArgumentException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 異常な
     *
     * @param e 異常な
     * @since 0.0.1
     */
    protected void throwable(Throwable e) {

        String stackinfo = "";
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try {
            e.printStackTrace(pw);
            stackinfo = sw.toString();
        } finally {
            pw.close();
        }

        getLogger().error(stackinfo);
    }

}
