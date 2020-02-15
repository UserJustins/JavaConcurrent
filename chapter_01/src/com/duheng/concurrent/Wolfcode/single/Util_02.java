package com.duheng.concurrent.Wolfcode.single;

/*************************
 Author: 杜衡
 Date: 2020/2/13
 Describe:
    懒加载 ：存在线程不安全，减小synchronized的粒度
    加volatile的具体原因不明，就当是规定吧
推荐使用饿汉式，简单、安全、粗暴
 *************************/
public class Util_02 {

    private Util_02(){}
    //volatile：线程不会把该变量放到线程缓存中，就在共享的内存中
    volatile private static Util_02 instance = null;

    public static Util_02 getInstance(){

        if (instance != null) {
            //在执行同步代码块之前，完全有可能进来好几个线程
            synchronized (Util_02.class){
                //那就再检查一遍
                if (instance != null) {

                    instance = new Util_02();
                }
            }
        }
        return instance;
    }
}
