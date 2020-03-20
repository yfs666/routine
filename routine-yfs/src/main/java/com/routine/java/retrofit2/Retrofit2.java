package com.routine.java.retrofit2;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * retrofit2的注释，
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Retrofit2 {
    /**
     * 域名，必须http或者https开头
     * @return 域名
     */
    String domainUrl();
}
