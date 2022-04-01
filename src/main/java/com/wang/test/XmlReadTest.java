package com.wang.test;

import cn.hutool.core.util.XmlUtil;
import com.wang.springframework.beans.config.BeanReference;
import com.wang.springframework.beans.PropertyValue;
import com.wang.springframework.beans.PropertyValues;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author wangdy
 * @date 2022/3/30 19:08
 */
public class XmlReadTest {
    public static void main(String[] args) {
        Document document = XmlUtil.readXML(XmlReadTest.class.getResourceAsStream("/beans.xml"));
        Element root = document.getDocumentElement();

        NodeList beanList = root.getElementsByTagName("bean");
        for(int x=0;x<beanList.getLength();x++){
            Element bean = (Element)beanList.item(x);
            String beanName = bean.getAttribute("id");
            String classPath = bean.getAttribute("class");

            // 属性
            PropertyValues propertyValue = new PropertyValues();
            // 获取属性
            NodeList properties = bean.getElementsByTagName("property");
            for(int y=0;y<properties.getLength();y++){
                Object value;
                Element property = (Element) properties.item(y);
                String propertyName = property.getAttribute("name");
                value = property.getAttribute("value");
                if(null == value){
                    value = new BeanReference(property.getAttribute("ref"));
                }
                propertyValue.addPropertieValue(new PropertyValue(propertyName, value));
            }

        }


    }
}
