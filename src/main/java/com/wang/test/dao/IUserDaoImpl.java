package com.wang.test.dao;

import java.util.HashMap;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  12:09
 */
public class IUserDaoImpl implements IUserDao {
    static HashMap<String,String> maps = new HashMap();
    static {
        maps.put("1","wang");
    }

    @Override
    public String getQuery(String id) {
        return maps.get(id);
    }
}
