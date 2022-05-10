package com.wang.springframework.aop;

import com.wang.springframework.BeanFactory;
import com.wang.springframework.beans.factory.support.DefaultListableBeanfactory;
import com.wang.springframework.beans.factory.support.InstantiationAwareBeanPostProcessor;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:05:10  22:39
 */
public class MyPointCutAdvisorProxyCreator implements InstantiationAwareBeanPostProcessor {

    private DefaultListableBeanfactory beanFactory;

    @Override
    public Object postProcessorBeforeInstantiation(Class<?> beanClass, String beanName){
        if(isInstructClass(beanClass)){
            return null;
        }
        for(AspectJExpressPointCutAdvisor advisor : beanFactory.getBeansOfType(AspectJExpressPointCutAdvisor.class)) {
            ClassFilter classFilter = advisor.getPointCut().getClassFilter();
            boolean matches = classFilter.matches(beanClass);
            if(!matches){
                continue;
            }
            AdvisedSupport support = new AdvisedSupport();
            try{
                System.out.println(beanClass.getSimpleName());
                support.setTargetSource(new TargetSource(beanClass.getDeclaredConstructor().newInstance()));
                support.setInterceptor((MethodInterceptor) advisor.getAdvice());
                support.setProxyTargetClass(false);
                support.setMethodMatcher(advisor.getPointCut().getMethodMatcher());
            }catch (Exception e){
                e.printStackTrace();
            }
            return new ProxyFactory(support).getProxy();
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

    private boolean isInstructClass(Class<?> clazz){
        if(PointCut.class.isAssignableFrom(clazz)
                || Advice.class.isAssignableFrom(clazz)
          || Advisor.class.isAssignableFrom(clazz)){
            return true;
        }
        return false;
    }
}
