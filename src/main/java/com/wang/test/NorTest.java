package com.wang.test;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:09  10:00
 */
public class NorTest {
    public static void main(String[] args) {
        String path = NorTest.class.getResource("/a.txt").getPath();
        String path1 = NorTest.class.getClassLoader().getResource("a.txt").getPath();

        System.out.println(path);
    }
}
