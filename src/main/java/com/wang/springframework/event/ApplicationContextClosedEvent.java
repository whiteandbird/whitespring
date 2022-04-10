package com.wang.springframework.event;

import com.wang.springframework.context.ApplicationContext;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  13:08
 */
public class ApplicationContextClosedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
