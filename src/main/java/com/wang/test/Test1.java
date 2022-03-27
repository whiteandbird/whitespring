package com.wang.test;

import com.wang.springframework.beans.factory.config.BeanDefinition;
import com.wang.springframework.beans.factory.support.DefaultListableBeanfactory;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  15:44
 */
public class Test1 {
    public static void main(String[] args) {
        error();
        noerr();
    }
    public static void noerr(){
        DefaultListableBeanfactory beanfactory = new DefaultListableBeanfactory();
        beanfactory.registryBeanDefinition("person", new BeanDefinition(Person.class));
        Person person = (Person) beanfactory.getBean("person");
        Person person1 = (Person) beanfactory.getBean("person");
        person.sayHello();
        System.out.println(person);
        System.out.println(person1);
    }

    public static void error(){
        DefaultListableBeanfactory beanfactory = new DefaultListableBeanfactory();
        beanfactory.registryBeanDefinition("person", new BeanDefinition(ConstructClass.class));
        ConstructClass person = (ConstructClass) beanfactory.getBean("person","wang");
        ConstructClass person1 = (ConstructClass) beanfactory.getBean("person", "wang");
        person.sayHello();
        System.out.println(person);
        System.out.println(person1);
    }

    public static class Person{
        public void sayHello(){
            System.out.println("hello world");
        }
    }

    public static class ConstructClass{
        private String name;
        public ConstructClass(String name){
            this.name = name;
        }

        public void sayHello(){
            System.out.println("hello world");
        }
    }
}
