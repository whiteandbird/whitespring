package com.wang.test.proxy;

import com.wang.springframework.aop.AspectJExpressPointCut;
import com.wang.springframework.aop.MethodMatcher;
import com.wang.test.dao.IUserService;
import com.wang.test.dao.UserService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  15:22
 */
public class MethodProxyTest {
    public static void main(String[] args) {
        Object targetObj = new UserService();
        IUserService userService =(IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            MethodMatcher methodMatcher = new AspectJExpressPointCut("execution(* com.wang.test.dao.IUserService.*(..))");
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(methodMatcher.mathes(method, targetObj.getClass())){
                    MethodInterceptor interceptor  = invotio->{
                        System.out.println("开始拦截");
                        System.out.println("拦截方法"+method.getName());
                        return "wa";
                    };
                    return interceptor.invoke(new MethodInvocation() {
                        @Override
                        public Method getMethod() {
                            return method;
                        }

                        @Override
                        public Object[] getArguments() {
                            return args;
                        }

                        @Override
                        public Object proceed() throws Throwable {
                            return method.invoke(targetObj,args);
                        }

                        @Override
                        public Object getThis() {
                            return targetObj;
                        }

                        @Override
                        public AccessibleObject getStaticPart() {
                            return method;
                        }
                    });
                }
                return method.invoke(targetObj, args);
            }
        });
        userService.query("1");
    }
}
