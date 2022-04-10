package com.wang.test.dao;

import lombok.Data;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  12:19
 */
@Data
public class UserService implements IUserService{
    private IUserDao iUserDao;

    public String query(String id){
        System.out.println("service execute query");
        if(iUserDao == null){
            return "wag";
        }
        return iUserDao.getQuery(id);
    }
}
