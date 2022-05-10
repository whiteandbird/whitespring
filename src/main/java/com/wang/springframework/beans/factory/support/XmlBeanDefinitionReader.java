package com.wang.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.wang.springframework.beans.config.BeanReference;
import com.wang.springframework.beans.PropertyValue;
import com.wang.springframework.beans.PropertyValues;
import com.wang.springframework.beans.factory.config.BeanDefinition;
import com.wang.springframework.context.support.ClassPathBeanDefintionScanner;
import com.wang.springframework.io.Resource;
import com.wang.springframework.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wangdy
 * @date 2022/3/30 18:52
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{


    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader){
        super(registry, resourceLoader);

    }

    @Override
    public void loadBeanDefinitions(String locations) {
        Resource resource = getResourceLoader().getResource(locations);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeanException {
        try{
            InputStream inputStream = resource.getInputStream();
            doLoadBeanDefinition(inputStream);
        }catch (IOException e){
            e.printStackTrace();
            throw new BeanException("获取资源失败");
        }catch (ClassNotFoundException e){
            throw new BeanException("对应class不存在");
        }

    }

    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for(Resource resource : resources){
            loadBeanDefinitions(resource);
        }
    }

    private void doLoadBeanDefinition(InputStream inputStream) throws ClassNotFoundException{
        BeanDefinitionRegistry beanDefinitionRegistry = getBeanDefinitionRegistry();
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();

        NodeList beanList = root.getElementsByTagName("bean");
        NodeList scans = root.getElementsByTagName("component-scan");
        if(scans.getLength()>0){
            Element item = (Element)scans.item(0);
            String scanPath = item.getAttribute("base-package");
            if(StrUtil.isEmpty(scanPath))
                throw new BeanException("scan path is nll");
            scanPackage(scanPath);
        }


        for(int x=0;x<beanList.getLength();x++){
            Element bean = (Element)beanList.item(x);
            String beanName = bean.getAttribute("id");
            String classPath = bean.getAttribute("class");
            String scope = bean.getAttribute("scope");
            // 属性
            PropertyValues propertyValue = new PropertyValues();
            // 获取属性
            NodeList properties = bean.getElementsByTagName("property");
            for(int y=0;y<properties.getLength();y++){
                Object value;
                Element property = (Element) properties.item(y);
                String propertyName = property.getAttribute("name");
                value = property.getAttribute("value");
                if(StrUtil.isEmpty(property.getAttribute("value"))){
                    value = new BeanReference(property.getAttribute("ref"));
                }
                propertyValue.addPropertieValue(new PropertyValue(propertyName, value));
            }
            if(beanDefinitionRegistry.containsBeanDefinition(beanName)){
                throw new BeanException(beanName+"该bean已经注册");
            }
            // 反射出class
            String initMethodName = bean.getAttribute("init-method");
            String destroyMethodName = bean.getAttribute("destroy-method");
            Class<?> aClass = Class.forName(classPath);
            BeanDefinition definition = new BeanDefinition(aClass, propertyValue);
            definition.setInitMethodName(StrUtil.isEmpty(initMethodName)?null : initMethodName);
            definition.setDestroyMethodName(StrUtil.isEmpty(destroyMethodName)?null : destroyMethodName);
            if(StrUtil.isNotEmpty(scope)){
                definition.setScope(scope);
            }
            beanDefinitionRegistry.registryBeanDefinition(beanName, definition);

        }
    }

    private void scanPackage(String scanPath){
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefintionScanner classPathBeanDefintionScanner = new ClassPathBeanDefintionScanner(getBeanDefinitionRegistry());
        classPathBeanDefintionScanner.doScan(basePackages);
    }
}
