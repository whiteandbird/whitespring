package com.wang.springframework.beans;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  15:27
 */
public class BeansException extends RuntimeException {
    private String msg;

    public BeansException(String msg){
        super(msg);
        this.msg = msg;
    }
}
