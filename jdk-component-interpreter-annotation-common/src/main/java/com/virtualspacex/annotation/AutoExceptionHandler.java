package com.virtualspacex.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * プロキシを使う<br/>
 * 統一された例外処理<br/>
 *
 * @version 0.0.1
 * @since JDK8
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoExceptionHandler {

}