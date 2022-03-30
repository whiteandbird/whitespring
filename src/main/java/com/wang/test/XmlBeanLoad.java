package com.wang.test;

import com.wang.springframework.beans.factory.support.DefaultListableBeanfactory;
import com.wang.springframework.beans.factory.support.XmlBeanDefinitionReader;
import com.wang.test.entity.Person;

/**
 * @author wangdy
 * @date 2022/3/30 19:36
 */
public class XmlBeanLoad {
    public static void main(String[] args) {
        DefaultListableBeanfactory defaultListableBeanfactory = new DefaultListableBeanfactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanfactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:beans.xml");
        Person person = (Person) defaultListableBeanfactory.getBean("person");
        Person person2 = (Person) defaultListableBeanfactory.getBean("person");
        System.out.println(person.hashCode());
        System.out.println(person2.hashCode());
        System.out.println(person);
    }
}
