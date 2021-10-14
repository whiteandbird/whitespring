package com.wang;

import com.wang.springframework.BeanDefinition;
import com.wang.springframework.BeanFactory;

/**
 * @author wangdy
 * @date 2021/10/14 16:47
 */
public class SpringframeworkTest {
    public static void main(String[] args) throws Exception{
        BeanFactory beanFactory = new BeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(User.class);
        beanFactory.registerBeanDefinition("user", beanDefinition);

        Class<User> userClass= (Class<User>) beanFactory.getBean("user");
        User user = userClass.newInstance();
        user.say();
    }
}
