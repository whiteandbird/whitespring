package com.wang.springframework.event;

import java.util.EventListener;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  13:21
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    void onApplicationEvent(E event);
}
