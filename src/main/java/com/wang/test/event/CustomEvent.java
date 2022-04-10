package com.wang.test.event;

import com.wang.springframework.context.ApplicationContext;
import com.wang.springframework.event.ApplicationContextEvent;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  14:50
 */
@ToString
public class CustomEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    private Integer id;
    public CustomEvent(Object source,Integer id) {
        super(source);
        this.id = id;
    }
}
