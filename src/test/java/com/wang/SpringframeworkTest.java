package com.wang;

import com.wang.springframework.beans.factory.config.BeanDefinition;
import com.wang.springframework.BeanFactory;

/**
 * @author wangdy
 * @date 2021/10/14 16:47
 */
public class SpringframeworkTest {
    public static void main(String[] args) {
        String path = SpringframeworkTest.class.getResource("/").getPath();
        System.out.println(path);
    }
}
