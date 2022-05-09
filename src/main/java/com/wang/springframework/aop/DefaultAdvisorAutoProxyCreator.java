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
        // 拿到配置的拦截器 以及对应的方法配置
        // 从配置里面拿  这个是用户进行配置的
        List<AspectJExpressPointCutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressPointCutAdvisor.class);
        for(AspectJExpressPointCutAdvisor advisor : advisors){
            // 每个advisor 有个pointCut 来进行进一步判断 是否应该进行切
            ClassFilter classFilter = advisor.getPointCut().getClassFilter();
            if(!classFilter.matches(beanClass)){
                continue;
            }
            // 进行构造 使用参数最后去生成代理对象
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = null;
            try{
                // 包装目标对象
                 targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            }catch (Exception e){
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            // 包装方法匹配 上面的已经通过了类匹配  所以只要方法匹配就能执行了
            advisedSupport.setMethodMatcher(advisor.getPointCut().getMethodMatcher());
            // 设置执行的方法
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
