package com.wang.springframework;


/**
 * @author wangdy
 * @date 2021/10/14 16:34
 */
public class BeanDefinition {

    private Class bean;

    public BeanDefinition(){}

    public BeanDefinition(Class object){
        this.bean = object;
    }

    public Object getBean(){
        return bean;
    }

    public void setBean(Class bean){
        this.bean = bean;
    }
}
