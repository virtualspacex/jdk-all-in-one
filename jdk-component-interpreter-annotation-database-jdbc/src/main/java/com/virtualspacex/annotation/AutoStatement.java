
/*
 * @Author: your name
 * @Date: 2020-12-20 18:00:03
 * @LastEditTime: 2020-12-20 18:10:24
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /jdk-all-in-one/jdbc-database-annotation-interpreter/src/main/java/com/virtualspacex/annotation/AutoStatement.java
 */

package com.virtualspacex.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * プロキシを使う
 *
 * @version 0.0.1
 * @since JDK8
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoStatement {

}
