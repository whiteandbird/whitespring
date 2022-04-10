package com.wang.springframework.aop;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  15:39
 */
@AllArgsConstructor
@NoArgsConstructor
public class TargetSource {
    private Object target;

    public  Class<?>[] getTargetClass(){
        return target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return target;
    }
}
