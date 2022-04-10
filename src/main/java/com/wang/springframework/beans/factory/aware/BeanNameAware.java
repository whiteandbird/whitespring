package com.wang.springframework.beans.factory.aware;

import com.wang.springframework.beans.factory.aware.Aware;

public interface BeanNameAware extends Aware {
    void setBeanName(String beanName);
}
