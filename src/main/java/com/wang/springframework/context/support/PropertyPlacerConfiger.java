package com.wang.springframework.context.support;

import com.wang.springframework.beans.BeansException;
import com.wang.springframework.beans.PropertyValue;
import com.wang.springframework.beans.PropertyValues;
import com.wang.springframework.beans.config.BeanFactoryPostProcessor;
import com.wang.springframework.beans.config.ConfigurableListableBeanFactory;
import com.wang.springframework.beans.factory.config.BeanDefinition;
import com.wang.springframework.io.DefaultResourceLoader;
import com.wang.springframework.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:05:08  21:15
 */
public class PropertyPlacerConfiger implements BeanFactoryPostProcessor {
    
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
    
    public static final String DEFAULR_PLACEHOLDER_SUFFIX = "}";
    
    private String location;
    
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        try{
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);

            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            String[] beansDefinitionNames = beanFactory.getBeansDefinitionNames();
            for(String beanName : beansDefinitionNames){
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                
                for(PropertyValue propertyValue : propertyValues.getPropertyValues()){
                    Object value = propertyValue.getValue();
                    if(!(value instanceof String))continue;
                    String strVal = (String) value;
                    StringBuilder buffer = new StringBuilder(strVal);
                    int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int endIdx = strVal.indexOf(DEFAULR_PLACEHOLDER_SUFFIX);
                    if(startIdx != -1 && endIdx != -1 && startIdx < endIdx){
                        String propKey = strVal.substring(startIdx+2,endIdx);
                        String propVal = properties.getProperty(propKey);
                        buffer.replace(startIdx, endIdx, propVal);
                        propertyValues.addPropertieValue(new PropertyValue(propertyValue.getName(), buffer.toString()));

                    }
                }
            }
        }catch (IOException e){
            throw new BeansException("Could not load properties");
        }
    }

    public void setLocation(String location){
        this.location = location;
    }
}
