package com.wang.springframework.io;

import cn.hutool.core.lang.Assert;
import com.wang.springframework.beans.BeansException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  22:16
 */
public class UrlPathResource implements Resource {

    private URL url;

    public UrlPathResource(URL url){
        this.url = url;
    }

    @Override
    public InputStream getInputStream() {
        Assert.notNull(url, "url is null");
        URLConnection urlConnection = null;
        try{
            urlConnection = url.openConnection();
            return urlConnection.getInputStream();
        }catch (IOException e){
            if(urlConnection instanceof HttpURLConnection){
                ((HttpURLConnection)urlConnection).disconnect();
            }
            throw new BeansException(e.getMessage());
        }
    }
}
