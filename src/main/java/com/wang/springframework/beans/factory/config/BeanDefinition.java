package com.wang.springframework.beans.factory.config;


import com.wang.springframework.beans.PropertyValues;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangdy
 * @date 2021/10/14 16:34
 */
@Data
@NoArgsConstructor
public class BeanDefinition {


    public static String SCOPE_SINGLETON = "singleton";

    public static String SCOPE_PROTOTYPE = "prototype";


    private Class beanClass;

    private PropertyValues propertyValues;

    private boolean singleton = true;

    private boolean prototype = false;

    private String scope;

    private String initMethodName;

    private String destroyMethodName;

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }


    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues == null ? new PropertyValues() : propertyValues;
    }

    public BeanDefinition(Class beanClass) {
        this(beanClass, null);
    }

}
