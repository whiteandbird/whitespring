package com.wang.springframework.event;

import java.util.Collection;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  14:21
 */
public class SimpleMutiCaster extends AbstractApplicationEventMultiCaster {
    @Override
    public void multiCast(ApplicationEvent applicationEvent) {
        Collection<ApplicationListener> listenerList = getListenerList(applicationEvent);
        for(ApplicationListener applicationListener : listenerList){
            applicationListener.onApplicationEvent(applicationEvent);
        }
    }
}
