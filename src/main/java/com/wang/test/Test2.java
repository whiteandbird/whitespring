package com.wang.test;

import com.wang.springframework.beans.BeanReference;
import com.wang.springframework.beans.PropertyValue;
import com.wang.springframework.beans.PropertyValues;
import com.wang.springframework.beans.factory.config.BeanDefinition;
import com.wang.springframework.beans.factory.support.DefaultListableBeanfactory;
import lombok.Data;
import lombok.ToString;

import java.io.File;

/**
 * @Author: whiteandbird
 * @Descripter: 属性填充实验
 * @Date: 2022:03:27  20:50
 */
public class Test2 {
    public static void main(String[] args) {
        DefaultListableBeanfactory beanfactory = new DefaultListableBeanfactory();
        beanfactory.registryBeanDefinition("car", new BeanDefinition(Car.class));

        // 注册user
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertieValue(new PropertyValue("name","wangdana"));
        propertyValues.addPropertieValue(new PropertyValue("car", new BeanReference("car")));
        beanfactory.registryBeanDefinition("user", new BeanDefinition(User2.class, propertyValues));

        User2 user = (User2) beanfactory.getBean("user");
        Car car = (Car) beanfactory.getBean("car");
        System.out.println(user);
        System.out.println(car);
        File file = new File("");
        System.out.println(file.getAbsolutePath());
    }

    @Data
    public static class User2{
        private String name;

        private Car car;

        public String toString(){
            return "name:["+name+"]"+
                    "car["+car.hashCode()+"]";
        }
    }

    public static class Car{
        public String toString(){
            return String.valueOf(hashCode());
        }
    }
}
