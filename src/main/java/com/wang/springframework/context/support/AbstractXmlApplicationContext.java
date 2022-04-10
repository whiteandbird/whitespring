package com.wang.springframework.context.support;

import com.wang.springframework.beans.factory.support.DefaultListableBeanfactory;
import com.wang.springframework.beans.factory.support.XmlBeanDefinitionReader;

/**
 * @author wangdy
 * @date 2022/3/31 20:02
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinition(DefaultListableBeanfactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        if(getConfigLocations() != null){
            xmlBeanDefinitionReader.loadBeanDefinitions(getConfigLocations());
        }

    }

    protected abstract String[] getConfigLocations();
}
