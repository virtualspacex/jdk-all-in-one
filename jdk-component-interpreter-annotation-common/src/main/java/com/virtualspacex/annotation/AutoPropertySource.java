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
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoPropertySource {
	SourceType type();
	String filepath() default "";
	Class<?> reader();
	String table() default "";
	String keyField() default "";
	String valueField() default "";
}