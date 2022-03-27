package com.wang.springframework.beans.factory.config;


import com.wang.springframework.beans.PropertyValues;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangdy
 * @date 2021/10/14 16:34
 */
@Data
@NoArgsConstructor
public class BeanDefinition {

   private Class beanClass;

   private PropertyValues propertyValues;

   public BeanDefinition(Class beanClass, PropertyValues propertyValues){
       this.beanClass = beanClass;
       this.propertyValues = propertyValues == null ? new PropertyValues() : propertyValues;
   }

   public BeanDefinition(Class beanClass){
       this(beanClass, null);
   }

}
