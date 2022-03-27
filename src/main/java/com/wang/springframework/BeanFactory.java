package com.wang.springframework;

import com.wang.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;


/**
 * @author wangdy
 * @date 2021/10/14 16:34
 */
public interface BeanFactory {

   Object getBean(String beanName);

   Object getBean(String beanName, Object ...args);

}
