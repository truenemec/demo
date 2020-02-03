package com.demo.productservice.common.annotation;

import com.demo.productservice.common.configuration.HeartbeatConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(HeartbeatConfiguration.class)
public @interface HeartbeatEnable {
}
