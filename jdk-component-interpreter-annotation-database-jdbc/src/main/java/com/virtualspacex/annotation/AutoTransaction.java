/*
 * @Author: your name
 * @Date: 2020-12-20 17:26:00
 * @LastEditTime: 2020-12-20 18:11:31
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Editpublic class AutoTransaction {
     
 }
 
 * @FilePath: /jdk-all-in-one/jdbc-database-annotation-interpreter/src/main/java/com/virtualspacex/annotation/AutoTransaction.java
 */

package com.virtualspacex.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * プロキシを使う
 *
 * @version 0.0.1
 * @since JDK8
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoTransaction {

}