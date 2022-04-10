package com.wang.test.event;

import com.wang.springframework.event.ApplicationContextClosedEvent;
import com.wang.springframework.event.ApplicationListener;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  14:40
 */
public class ContextClosedListener implements ApplicationListener<ApplicationContextClosedEvent> {
    @Override
    public void onApplicationEvent(ApplicationContextClosedEvent event) {
        System.out.println("接受到context关闭事件");
    }
}
