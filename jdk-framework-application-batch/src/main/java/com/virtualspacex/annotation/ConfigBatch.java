package com.virtualspacex.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.virtualspacex.batch.configuration.BatchConfigurationInterface;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConfigBatch {
	Class<? extends BatchConfigurationInterface> value();
}
