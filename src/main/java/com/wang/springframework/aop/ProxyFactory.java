package com.wang.springframework.aop;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  17:22
 */
public class ProxyFactory {
    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport){
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy(){
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if(advisedSupport.isProxyTargetClass()){
            return new CglibAopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
