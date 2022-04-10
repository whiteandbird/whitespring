package com.wang.springframework.event;

import com.wang.springframework.context.ApplicationContext;
import com.wang.springframework.context.support.AbstractApplicationContext;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  13:06
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    protected ApplicationContextEvent getApplicationContextEvent(){
        return (ApplicationContextEvent) getSource();
    }
}
