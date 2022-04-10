package com.wang.springframework.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  16:08
 */
public class CglibAopProxy implements AopProxy {

    private AdvisedSupport support;

    public CglibAopProxy(AdvisedSupport advisedSupport){
        this.support = advisedSupport;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(support.getTargetSource().getClass());
        enhancer.setInterfaces(support.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvisedInterceptor(support));
        return enhancer.create();
    }

    public static class DynamicAdvisedInterceptor implements MethodInterceptor {

        private AdvisedSupport support;

        public DynamicAdvisedInterceptor(AdvisedSupport support){
            this.support = support;
        }


        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            CglibMethodInvocation cglibMethodInvocation = new CglibMethodInvocation(support.getTargetSource().getTarget(), method, objects, methodProxy);
            if(support.getMethodMatcher().mathes(method, support.getTargetSource().getClass())){
                return support.getInterceptor().invoke(cglibMethodInvocation);
            }
            return cglibMethodInvocation.proceed();
        }


    }
    private static class CglibMethodInvocation extends ReflectiveMethodInvocation{

        private  MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }
        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(getThis(), getArguments());
        }
    }
}
