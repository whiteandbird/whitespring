package com.wang.springframework.context.aware;

import com.wang.springframework.beans.factory.aware.Aware;
import com.wang.springframework.context.ApplicationContext;

public interface ApplicationContextAware extends Aware {
    void setApplication(ApplicationContext context);
}
