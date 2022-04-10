package com.wang.springframework.beans.factory.aware;

import com.wang.springframework.beans.factory.aware.Aware;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  8:57
 */
public interface ClassLoaderAware extends Aware {
    void setClassLoader(ClassLoader classLoader);
}
