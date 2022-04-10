package com.wang.springframework.context.support;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:09  16:48
 */
public interface ConfigurableApplicationContext {
    void close();

    void registerShutDownHook();
}
