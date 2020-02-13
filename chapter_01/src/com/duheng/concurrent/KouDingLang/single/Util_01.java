package com.duheng.concurrent.KouDingLang.single;

/*************************
 Author: 杜衡
 Date: 2020/2/13
 Describe:
    懒加载 ：存在线程不安全，最直接的暴力的方式就是同步方法
 可是synchronized的粒度太大了
 *************************/
public class Util_01 {

    private Util_01(){}
    private static Util_01 instance = null;

    synchronized public static Util_01 getInstance(){

        if (instance != null) {
            instance = new Util_01();
        }
        return instance;
    }
}
