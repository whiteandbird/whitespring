package com.wang.springframework.context.support;

import com.wang.springframework.beans.config.ConfigurableListableBeanFactory;
import com.wang.springframework.beans.factory.support.DefaultListableBeanfactory;

/**
 * @author wangdy
 * @date 2022/3/31 20:01
 */

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanfactory beanfactory;

    @Override
    protected void refreshBeanFactory() {
        DefaultListableBeanfactory beanFactory = createBeanFactory();
        loadBeanDefinition(beanFactory);
        this.beanfactory = beanFactory;
    }

    protected abstract void loadBeanDefinition(DefaultListableBeanfactory beanFactory);


    public DefaultListableBeanfactory createBeanFactory(){
        return new DefaultListableBeanfactory();
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanfactory;
    }
}
