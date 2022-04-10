package com.wang.test.entity;

import com.wang.springframework.beans.config.BeanPostProcessor;
import com.wang.springframework.context.ApplicationContext;
import com.wang.springframework.context.aware.ApplicationContextAware;
import com.wang.springframework.context.support.DestroyableBean;
import com.wang.springframework.context.support.InitializingBean;
import lombok.Data;

import java.util.List;

/**
 * @author wangdy
 * @date 2022/3/30 19:38
 */
@Data
public class Person implements InitializingBean, DestroyableBean, ApplicationContextAware {
    private ApplicationContext context;

    private String personName;

    private Arm arm;

    @Override
    public void afterPropertiesSet() {
        System.out.println("===============init beaning running============");
        List<BeanPostProcessor> beansOfType = context.getBeansOfType(BeanPostProcessor.class);
        System.out.println(beansOfType.size());
        for (BeanPostProcessor postProcessor : beansOfType){
            System.out.println(postProcessor);
        }

    }

    public void init(){
        System.out.println("anooter iniit");
    }

    @Override
    public void destroy() {
        System.out.println("我自由了");
    }

    @Override
    public void setApplication(ApplicationContext context) {
        this.context =context;
    }
}
