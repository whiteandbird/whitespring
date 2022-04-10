package com.wang.springframework.beans.factory.aware;

import com.wang.springframework.BeanFactory;

public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory);
}
