package com.wang.springframework;

import java.util.HashMap;
import java.util.Map;


/**
 * @author wangdy
 * @date 2021/10/14 16:34
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    public Object getBean(String name){
        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition){
        if(beanDefinitionMap.containsKey(name)){
            throw new RuntimeException("该bean已经被定义，重复bean Name"+name+"   对应class:"+beanDefinitionMap.get(name).getBean().getClass());
        }
        beanDefinitionMap.put(name, beanDefinition);
    }

}
