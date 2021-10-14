package com.wang.springframework;


/**
 * @author wangdy
 * @date 2021/10/14 16:34
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(){}

    public BeanDefinition(Object object){
        this.bean = object;
    }

    public Object getBean(){
        return bean;
    }

    public void setBean(Object bean){
        this.bean = bean;
    }
}
