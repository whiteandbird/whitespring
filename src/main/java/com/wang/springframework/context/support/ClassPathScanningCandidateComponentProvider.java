package com.wang.springframework.context.support;

import cn.hutool.core.util.ClassUtil;
import com.wang.springframework.annotation.Component;
import com.wang.springframework.beans.factory.config.BeanDefinition;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:05:08  21:58
 */
public class ClassPathScanningCandidateComponentProvider {
    public Set<BeanDefinition> findCandidateComponents(String basePackage){
        Set<BeanDefinition> candidates =new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for(Class<?> clzz : classes){
            candidates.add(new BeanDefinition(clzz));
        }
        return candidates;
    }
}
