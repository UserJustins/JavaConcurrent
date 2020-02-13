package com.duheng.concurrent.KouDingLang.ProductConsum._03;

/*************************
 Author: 杜衡
 Date: 2020/2/12
 Describe:

 *************************/
public class App {
    public static void main(String[] args) {
        ShareResource source= new ShareResource();
        //生产者
        new Thread(new Producter(source), "Thread_Product").start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //消费者
        new Thread(new Consumer(source),"Thread_Consumer1").start();
        //new Thread(new Consumer(source),"Thread_Consumer2").start();
    }
}
