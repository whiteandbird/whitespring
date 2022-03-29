package com.wang.springframework.io;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  22:31
 */
public class DefaultResourceLoader implements ResourceLoader{

    public static String PREFIX_PATH = "classpath:";

    @Override
    public Resource getResource(String location) {
        if(location.startsWith(PREFIX_PATH)){
            return new ClassPathResource(location.substring(PREFIX_PATH.length()));
        }
        try{
            return new UrlPathResource(new URL(location));
        }catch (MalformedURLException e){
            return new FilePathResource(location);
        }


    }
}
