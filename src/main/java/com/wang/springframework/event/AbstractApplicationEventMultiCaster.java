package com.wang.springframework.event;

import cn.hutool.core.util.ClassUtil;
import com.wang.springframework.BeanFactory;
import com.wang.springframework.beans.factory.aware.BeanFactoryAware;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  13:28
 */
public abstract class AbstractApplicationEventMultiCaster implements ApplicationEventMultiCaster, BeanFactoryAware {
    Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void addApplicationEventListener(ApplicationListener<?> applicationListener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>)applicationListener);
    }

    @Override
    public void removeApplicationEventListener(ApplicationListener<?> applicationListener) {
        applicationListeners.remove((ApplicationListener<ApplicationEvent>)applicationListener);
    }

    public Collection<ApplicationListener> getListenerList(ApplicationEvent event){
        LinkedList<ApplicationListener> res = new LinkedList<>();
        for(ApplicationListener applicationListener:applicationListeners){
            if(supportEvent(applicationListener, event)){
                res.add(applicationListener);
            }
        }
        return res;
    }

    protected boolean supportEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent applicationEvent){
        Class<? extends ApplicationListener> listenerClass = listener.getClass();
        Class<?> targetClass = isCglib(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type[] genericInterfaces = targetClass.getGenericInterfaces();
        System.out.println("泛型信息"+genericInterfaces);
        Type genericInterface = genericInterfaces[0];
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String typeName = actualTypeArgument.getTypeName();
        Class<?> typeClass = null;
        try{
            typeClass = Class.forName(typeName);
        }catch (Exception e){

        }
        return typeClass.isAssignableFrom(applicationEvent.getClass());


    }

    private boolean isCglib(Class<?> type){
        return type.getName().contains("$") && type.getName().contains("CGLIB");
    }
}
