package com.wang.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.wang.springframework.beans.config.AutowireCapableBeanFactory;
import com.wang.springframework.beans.config.BeanPostProcessor;
import com.wang.springframework.beans.config.BeanReference;
import com.wang.springframework.beans.BeansException;
import com.wang.springframework.beans.PropertyValue;
import com.wang.springframework.beans.PropertyValues;
import com.wang.springframework.beans.factory.aware.Aware;
import com.wang.springframework.beans.factory.aware.BeanFactoryAware;
import com.wang.springframework.beans.factory.aware.BeanNameAware;
import com.wang.springframework.beans.factory.aware.ClassLoaderAware;
import com.wang.springframework.beans.factory.config.BeanDefinition;
import com.wang.springframework.context.support.DestroyableBean;
import com.wang.springframework.context.support.DestroyableBeanAdapter;
import com.wang.springframework.context.support.InitializingBean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  14:55
 */
public abstract class AbstractAutowireCapableBeanfactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition definition, Object[] args) {

        Object bean = resovleBeforeInstantiation(beanName, definition);
        if(null != bean){
            return bean;
        }

        return doCreateBean(beanName, definition, args);
    }

    private Object resovleBeforeInstantiation(String beanName, BeanDefinition definition) {
        Object o = applyBeanPostProcessorBeforeInstantiation(beanName, definition);
        return o;
    }

    private Object applyBeanPostProcessorBeforeInstantiation(String beanName, BeanDefinition definition){
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for(BeanPostProcessor postProcessor : beanPostProcessors){
            if(postProcessor instanceof InstantiationAwareBeanPostProcessor){
                Object o = ((InstantiationAwareBeanPostProcessor) postProcessor).postProcessorBeforeInstantiation(definition.getBeanClass(), beanName);
                if(o != null){
                    return o;
                }
            }
        }
        return null;
    }

    protected Object doCreateBean(String beanName, BeanDefinition definition, Object[] args){
        Object res = null;
        try{
            res = createBeanInstance(beanName, definition,  args);

            // 填充属性
            applyPropertyValues(beanName, res, definition);

            res = initializeBean(beanName, res, definition);

        }catch (Exception e){
            e.printStackTrace();
            throw new BeansException(e.getMessage());
        }
        registerDisposableBeanIfNessary(beanName, res, definition);
        if(definition.isSingleton()){
            addSingleton(beanName, res);
        }
        return res;
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        if(bean instanceof Aware){
            if(bean instanceof BeanNameAware){
                ((BeanNameAware)bean).setBeanName(beanName);
            }
            if(bean instanceof BeanFactoryAware){
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if(bean instanceof ClassLoaderAware){
                ((ClassLoaderAware) bean).setClassLoader(ClassLoader.getSystemClassLoader());
            }
        }
        Object instance = applyBeanPostProcessorBeforeInitialize(beanName, bean);
        try {
            invokeInitMethod(beanName, instance, beanDefinition);
        }catch (Exception e){
            throw new BeansException("执行beanInitlizing失败");
        }
        instance = applyBeanPostProcessorAfterInitialize(beanName, bean);

        return instance;

    }

    @Override
    public Object applyBeanPostProcessorAfterInitialize(String beanName, Object res) {
        Object result = res;
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for(BeanPostProcessor postProcessor : beanPostProcessors){
            Object o = postProcessor.postProcessorAfterInitialization(res, beanName);
            if(o == null)return result;
            result = o;
        }
        return result;
    }

    private void invokeInitMethod(String beanName, Object res, BeanDefinition beanDefinition) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if(res instanceof InitializingBean){
            ((InitializingBean)res).afterPropertiesSet();
        }
        if(StrUtil.isNotEmpty(beanDefinition.getInitMethodName())){
            Method declaredMethod = beanDefinition.getBeanClass().getDeclaredMethod(beanDefinition.getInitMethodName());
            declaredMethod.invoke(res);
        }
    }

    protected  void registerDisposableBeanIfNessary(String beanName,Object bean, BeanDefinition definition){
        if(definition.isSingleton()){
            if(bean instanceof DestroyableBean || StrUtil.isNotEmpty(definition.getDestroyMethodName())){
                addDestroyBean(beanName, new DestroyableBeanAdapter(bean, beanName, definition));
            }
        }
    }

    @Override
    public Object applyBeanPostProcessorBeforeInitialize(String beanName, Object res) {
        Object result = res;
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for(BeanPostProcessor postProcessor : beanPostProcessors){
            Object o = postProcessor.postProcessorBeforeInitialization(res, beanName);
            if(o == null)return result;
            result = o;
        }
        return result;
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition definition) {
        PropertyValues propertyValues = definition.getPropertyValues();
        PropertyValue[] propertieVaules = propertyValues.getPropertieVaules();
        for(PropertyValue property : propertieVaules){
            String propertyName = property.getName();
            Object value = property.getValue();

            if(value instanceof BeanReference){
                // BeanReference类型去bean容器里面再拿·
                value = getBean(((BeanReference)value).getValue());
            }
            BeanUtil.setFieldValue(bean, propertyName, value);
        }
    }

    protected Object createBeanInstance(String beanName, BeanDefinition definition, Object[] args)throws  RuntimeException{
        Class beanClass = definition.getBeanClass();
        Constructor[] declaredConstructors = beanClass.getDeclaredConstructors();
        Constructor constructor = null;
        for(Constructor item : declaredConstructors){
            if(item.getParameterTypes().length == args.length){
                constructor = item;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanName, definition, constructor, args);
    };
}
