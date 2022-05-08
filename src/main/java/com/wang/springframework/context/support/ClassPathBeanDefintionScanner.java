package com.wang.springframework.context.support;

import com.wang.springframework.annotation.Scope;
import com.wang.springframework.beans.factory.config.BeanDefinition;
import com.wang.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:05:08  22:17
 */
public class ClassPathBeanDefintionScanner extends ClassPathScanningCandidateComponentProvider {
    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefintionScanner(BeanDefinitionRegistry registry){
        this.registry = registry;
    }

    public void doScan(String ...basePackages){
        for(String basePackage : basePackages){
            Set<BeanDefinition> candidateComponents = findCandidateComponents(basePackage);
            for(BeanDefinition beanDefinition : candidateComponents){
                resolveBeanScope(beanDefinition);
            }
        }
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope annotation = beanClass.getAnnotation(Scope.class);
        if(null != annotation){
            return annotation.value();
        }
        return "singleton";

    }
}
