package com.wang;

import com.wang.springframework.BeanDefinition;
import com.wang.springframework.BeanFactory;

/**
 * @author wangdy
 * @date 2021/10/14 16:47
 */
public class SpringframeworkTest {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(new User());
        beanFactory.registerBeanDefinition("user", beanDefinition);

        User user = (User)beanFactory.getBean("user");
        user.say();

    }
}
