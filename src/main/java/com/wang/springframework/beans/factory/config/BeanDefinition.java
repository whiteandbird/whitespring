package com.wang.springframework.beans.factory.config;


/**
 * @author wangdy
 * @date 2021/10/14 16:34
 */
public class BeanDefinition {

   private Class beanClass;


   public BeanDefinition(Class beanClass){
       this.beanClass = beanClass;
   }

   public BeanDefinition(){

   }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

}
