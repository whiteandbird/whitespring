package com.wang.springframework.context.support;

import com.wang.springframework.beans.config.BeanFactoryPostProcessor;
import com.wang.springframework.beans.config.BeanPostProcessor;
import com.wang.springframework.beans.config.ConfigurableListableBeanFactory;
import com.wang.springframework.context.ConfigurableApplicationContext;
import com.wang.springframework.event.*;
import com.wang.springframework.io.DefaultResourceLoader;
import com.wang.springframework.io.Resource;

import java.util.List;

/**
 * @author wangdy 继承defaultResourceLoader只是为了加载spring.xml
 * @date 2022/3/31 20:01
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    private ApplicationEventMultiCaster multiCaster;

    @Override
    public void refresh() {
        // 创建并获取bean工厂
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 放入一个获取context上下文的postProcessor
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        // 更新beanDefinition信息
        invokeBeanFactoryPostProcessor(beanFactory);
        // 注册beanPostProcessor
        registerBeanPostProcessors(beanFactory);
        // 初始化caster
        initEventCaster();
        //注册listener
        registerListeners();
        // 实例化单例
        beanFactory.preInstantiateSingletons();

    }

    private void registerListeners() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        List<ApplicationListener> beansOfType = beanFactory.getBeansOfType(ApplicationListener.class);
        for(ApplicationListener applicationListener : beansOfType){
            multiCaster.addApplicationEventListener(applicationListener);
        }
    }

    private void initEventCaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        multiCaster = new SimpleMutiCaster();
        beanFactory.addSingleton("MULTI_CASTER", multiCaster);
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        multiCaster.multiCast(event);
    }

    @Override
    public void registerShutHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        publishEvent(new ApplicationContextClosedEvent(this));
        getBeanFactory().destroySingletons();
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected abstract void refreshBeanFactory();

    private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory){
        List<BeanFactoryPostProcessor> processors = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor postProcessor : processors){
            postProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        List<BeanPostProcessor> postProcessors = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for(BeanPostProcessor postProcessor:postProcessors){
            beanFactory.addBeanPostProcessor(postProcessor);
        }
    }

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return (T) getBeanFactory().getBean(requiredType);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> type) {
        return getBeanFactory().getBean(beanName, type);
    }

    @Override
    public <T> List<T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public String[] getBeansDefinitionNames() {
        return getBeanFactory().getBeansDefinitionNames();
    }
}
