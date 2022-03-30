package com.wang.springframework.beans.factory.support;

import com.wang.springframework.io.Resource;
import com.wang.springframework.io.ResourceLoader;

/**
 * @author wangdy
 * @date 2022/3/30 13:58
 */
public interface BeanDefinitionReader {
    /**
     * 获取注册机
     * @return
     */
    BeanDefinitionRegistry getBeanDefinitionRegistry();

    /**
     * 获取资源加载器
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     * 加载beanDefiniton
     * @param locations
     */
    void loadBeanDefinitions(String locations);

    /**
     * 加载beanDefinition
     * @param resource
     */
    void loadBeanDefinitions(Resource resource);

    /**
     * 加载beanDefinition
     * @param resources
     */
    void loadBeanDefinitions(Resource ...resources);

}
