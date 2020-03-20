package com.routine.java.retrofit2;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class Retrofit2ScannerRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(Retrofit2Scan.class.getName()));
        String basePackage = annotationAttributes.getString("basePackage");
        Retrofit2Scanner scanner = Retrofit2Scanner.getScanner(registry, Retrofit2.class);
        scanner.doScan(basePackage);
    }
}
