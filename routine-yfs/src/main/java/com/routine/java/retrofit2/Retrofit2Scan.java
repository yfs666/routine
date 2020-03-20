package com.routine.java.retrofit2;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Retrofit2的扫描注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(Retrofit2ScannerRegistrar.class)
public @interface Retrofit2Scan {
    /**
     * 扫描的包路径
     * @return 包路径
     */
    String basePackage();
}
