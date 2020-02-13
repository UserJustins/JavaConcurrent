package com.duheng.concurrent.KouDingLang.ProductConsum._01;

/*************************
 Author: 杜衡
 Date: 2020/2/12
 Describe:
    重要的是要明白为什么是这样的运行结果？
 解决了生产和消费的同步问题
 *************************/
public class App {
    public static void main(String[] args) {
        ShareResource source= new ShareResource();
        //生产者
        new Thread(new Producter(source), "Thread_Product").start();

        //消费者
        new Thread(new Consumer(source),"Thread_Consumer").start();
    }
}
