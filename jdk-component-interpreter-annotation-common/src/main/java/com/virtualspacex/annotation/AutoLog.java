/*
 * @Author: your name
 * @Date: 2020-10-10 14:07:48
 * @LastEditTime: 2020-10-11 19:02:14
 * @LastEditors: your name
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/fujielectric/batch/annotation/AutoLog.java
 */
package com.virtualspacex.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * プロキシを使う<br/>
 * オブジェクトのメソッドのスタック情報を自動的に出力します<br/>
 *
 * @version 0.0.1
 * @since JDK8
 */
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoLog {

}