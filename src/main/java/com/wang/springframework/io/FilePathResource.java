package com.wang.springframework.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  22:12
 */
public class FilePathResource implements Resource {

    private String filePath;


    public FilePathResource(String filePath){
        this.filePath = filePath;
    }

    @Override
    public InputStream getInputStream() throws IOException{
        InputStream is = null;
        is = Files.newInputStream(new File(filePath).toPath());
        return is;
    }
}
