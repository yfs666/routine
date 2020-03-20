package com.routine.java.retrofit2;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * 自定义扫描器
 */
public class Retrofit2Scanner extends ClassPathBeanDefinitionScanner {

    private static Class<? extends Annotation> annotationClazz = null;


    public Retrofit2Scanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 获取扫描器
     * @author yangfengshuai
     * @date 2020/2/4
     */
    public static synchronized Retrofit2Scanner getScanner(BeanDefinitionRegistry registry, Class<? extends Annotation> clazz) {
        annotationClazz = clazz;
        Retrofit2Scanner scanner = new Retrofit2Scanner(registry);
        return scanner;
    }

    /**
     * 自己实现扫描方法
     */
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        if (CollectionUtils.isEmpty(beanDefinitionHolders)) {
            return  beanDefinitionHolders;
        }
        beanDefinitionHolders.forEach(beanDefinitionHolder -> {
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            beanDefinition.getPropertyValues().add("retrofit2Interface", beanDefinition.getBeanClassName());
            beanDefinition.setBeanClass(Retrofit2FactoryBean.class);
            beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        });
        return beanDefinitionHolders;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return (beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent());
    }

    @Override
    public void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(annotationClazz));
    }

}
