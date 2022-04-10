package com.wang.springframework.beans;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  20:22
 */
@Data
public class PropertyValues {
    private List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();

    public void addPropertieValue(PropertyValue propertyValue){
        for(PropertyValue value : propertyValues){
            if(value.getName().equals(propertyValue.getName())){
                value.setValue(propertyValue.getValue());
                return;
            }
        }
        this.propertyValues.add(propertyValue);
    }

    public PropertyValue[] getPropertieVaules(){
        return propertyValues.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName){
        for(PropertyValue value : getPropertieVaules()){
            if(value.getName().equals(propertyName)){
                return value;
            }
        }
        return null;
    }
}
