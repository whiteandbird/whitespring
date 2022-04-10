package com.wang.springframework.beans.config;

import com.wang.springframework.BeanFactory;

import java.util.List;
import java.util.Map;

/**
 * 返回一类数据的接口
 * @author wangdy
 * @date 2022/3/31 20:31
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     *
     * @param type
     * @param <T>
     * @return
     */
    <T> List<T> getBeansOfType(Class<T> type);

    String[] getBeansDefinitionNames();
}
