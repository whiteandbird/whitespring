package com.wang.springframework.context.support;

import cn.hutool.core.util.StrUtil;
import com.wang.springframework.annotation.Component;
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
                String scope = resolveBeanScope(beanDefinition);
                if(StrUtil.isNotEmpty(scope)) {
                    beanDefinition.setScope(scope);
                }
                registry.registryBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
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

    private String determineBeanName(BeanDefinition beanDefinition){
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if(StrUtil.isEmpty(value)){
            return StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }
}
