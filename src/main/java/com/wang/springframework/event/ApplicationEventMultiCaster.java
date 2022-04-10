package com.wang.springframework.event;

public interface ApplicationEventMultiCaster {
    void addApplicationEventListener(ApplicationListener<?> applicationListener);

    void removeApplicationEventListener(ApplicationListener<?> applicationListener);

    void multiCast(ApplicationEvent applicationEvent);
}
