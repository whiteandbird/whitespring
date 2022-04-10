package com.wang.springframework.beans.factory.support;

public interface FactoryBean<T> {
    T getObject();

    Class<?> getObjectType();

    boolean singleton();
}
