package com.wang.test;

/**
 * @author wangdy
 * @date 2022/3/30 17:08
 */
public class TimeTest {
    public static void main(String[] args) {
        err();
        noerr();
    }

    public static void err(){
        long i= System.currentTimeMillis();
        for(int x=0;x<10000000;x++){
            try {
                throw new RuntimeException();
            }catch (Exception e){

            }
        }
        System.out.println(System.currentTimeMillis()-i);
    }

    public static void noerr(){
        long i= System.currentTimeMillis();
        for(int x=0;x<10000000;x++){
            try {

            }catch (Exception e){

            }
        }
        System.out.println("========");
        System.out.println(System.currentTimeMillis()-i);
    }


}
