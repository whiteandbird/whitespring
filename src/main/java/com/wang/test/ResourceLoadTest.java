package com.wang.test;

import com.wang.springframework.io.DefaultResourceLoader;
import com.wang.springframework.io.Resource;
import com.wang.springframework.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wangdy
 * @date 2022/3/29 9:36
 */
public class ResourceLoadTest {
    public static void main(String[] args) throws IOException {
        fileRead();
        httpRead();
        classPathRead();
    }

    public static void fileRead() throws IOException{
        ResourceLoader resource = new DefaultResourceLoader();
        Resource resource1 = resource.getResource("pom.xml");
        InputStream inputStream = resource1.getInputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = (inputStream.read(buffer)))!=-1){
            System.out.println(new String(buffer, 0,len));
        }
    }

    public static void classPathRead() throws IOException{
        ResourceLoader resource = new DefaultResourceLoader();
        Resource resource1 = resource.getResource("classpath:helloworld.txt");
        InputStream inputStream = resource1.getInputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = (inputStream.read(buffer)))!=-1){
            System.out.println(new String(buffer, 0,len));
        }
    }
    public static void httpRead() throws IOException{
        ResourceLoader resource = new DefaultResourceLoader();
        Resource resource1 = resource.getResource("http://www.baidu.com");
        InputStream inputStream = resource1.getInputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = (inputStream.read(buffer)))!=-1){
            System.out.println(new String(buffer, 0,len));
        }
    }
}
