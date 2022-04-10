package com.wang.test.event;

import com.wang.springframework.event.ApplicationListener;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  14:53
 */
public class CustomListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println(event);
    }
}
