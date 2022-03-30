package com.wang.springframework.io;

import cn.hutool.core.util.ClassLoaderUtil;

import java.io.InputStream;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  21:57
 */
public class ClassPathResource implements Resource {

    private ClassLoader classLoader = ClassLoaderUtil.getClassLoader();

    private String classPath;

    public ClassPathResource(String path){
        classPath = path;
    }

    public ClassPathResource(String path, ClassLoader classLoader){
        this.classPath = path;
        this.classLoader = classLoader == null? ClassLoaderUtil.getClassLoader() : classLoader;
    }

    @Override
    public InputStream getInputStream() {
        return classLoader.getResourceAsStream(classPath);
    }
}
