package com.wang.springframework.event;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  14:11
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
