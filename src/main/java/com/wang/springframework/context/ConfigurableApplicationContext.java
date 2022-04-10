package com.wang.springframework.context;

/**
 * @author wangdy
 * @date 2022/3/31 20:00
 */
public interface ConfigurableApplicationContext extends ApplicationContext{
    void refresh();

    void registerShutHook();

    void close();
}
