package com.wang.springframework.context.support;

import com.wang.springframework.beans.config.BeanPostProcessor;
import com.wang.springframework.beans.config.ConfigurableListableBeanFactory;

/**
 * @author wangdy
 * @date 2022/3/31 20:02
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    private String[] locations;
    @Override
    protected String[] getConfigLocations() {
        return locations;
    }

    public ClassPathXmlApplicationContext(String location){
        this(new String[]{location});
    }

    public ClassPathXmlApplicationContext(String[] locations){
        this.locations = locations;
        refresh();
    }
}
