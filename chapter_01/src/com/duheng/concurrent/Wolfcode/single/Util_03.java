package com.duheng.concurrent.Wolfcode.single;

/*************************
 Author: 杜衡
 Date: 2020/2/13
 Describe:
    饿汉式单例模式
 简单粗暴还安全
 *************************/
public class Util_03 {

    private Util_03(){}
    private static final Util_03 instance = new Util_03();

    private static Util_03 getInstance(){
        return instance;
    }
}
