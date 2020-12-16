package com.virtualspacex.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 処理する例外のタイプ
 *
 * @version 0.0.1
 * @since JKD8
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptionHandler {

    /**
     * @return 処理する例外のタイプ
     * @since 0.0.1
     */
    Class<?> value() default Throwable.class;

}