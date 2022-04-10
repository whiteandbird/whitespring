package com.wang.springframework.event;

import java.util.EventObject;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  13:04
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
