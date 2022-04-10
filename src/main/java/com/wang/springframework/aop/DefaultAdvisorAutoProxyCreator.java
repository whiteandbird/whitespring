package com.wang.springframework.aop;

import com.wang.springframework.BeanFactory;
import com.wang.springframework.beans.factory.support.DefaultListableBeanfactory;
import com.wang.springframework.beans.factory.support.InstantiationAwareBeanPostProcessor;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  17:09
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor {

    private DefaultListableBeanfactory beanFactory;

    @Override
    public Object postProcessorBeforeInstantiation(Class<?> beanClass, String beanName) {
        if(isInfrastructureClass(beanClass))return null;
        List<AspectJExpressPointCutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressPointCutAdvisor.class);
        for(AspectJExpressPointCutAdvisor advisor : advisors){
            ClassFilter classFilter = advisor.getPointCut().getClassFilter();
            if(!classFilter.matches(beanClass)){
                continue;
            }
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = null;
            try{
                 targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            }catch (Exception e){
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodMatcher(advisor.getPointCut().getMethodMatcher());
            advisedSupport.setInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setProxyTargetClass(false);
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanfactory) beanFactory;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass)
                || PointCut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }
}
