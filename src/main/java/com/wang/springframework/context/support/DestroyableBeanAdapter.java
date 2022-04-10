package com.wang.springframework.context.support;

import cn.hutool.core.util.StrUtil;
import com.wang.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:09  17:17
 */
public class DestroyableBeanAdapter implements DestroyableBean {
    private final Object bean;

    private final String beanName;

    private String destroyMethodName;


    public DestroyableBeanAdapter(Object bean, String beanName, BeanDefinition definition){
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = definition.getDestroyMethodName();
    }

    @Override
    public void destroy() {
        if(bean instanceof DestroyableBean){
            ((DestroyableBean)bean).destroy();
        }
        if(StrUtil.isNotEmpty(destroyMethodName)){
            try{
                Method declaredMethod = bean.getClass().getDeclaredMethod(destroyMethodName);
                declaredMethod.invoke(bean);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
