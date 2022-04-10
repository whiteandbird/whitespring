package com.wang.springframework.context;

import com.wang.springframework.beans.config.HierachicalBeanFactory;
import com.wang.springframework.beans.config.ListableBeanFactory;
import com.wang.springframework.event.ApplicationEventPublisher;

/**
 * @author wangdy
 * @date 2022/3/31 20:00
 */
public interface ApplicationContext extends ListableBeanFactory, HierachicalBeanFactory, ApplicationEventPublisher {
}
