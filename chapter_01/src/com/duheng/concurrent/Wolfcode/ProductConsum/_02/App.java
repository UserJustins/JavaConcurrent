package com.duheng.concurrent.Wolfcode.ProductConsum._02;

/*************************
 Author: 杜衡
 Date: 2020/2/12
 Describe:
    生产者生产一个消费者消费一个，消费者消费完再通知生产者生产

 不错代码实现了，但是学习了别人的代码，感觉好不优雅
 *************************/
public class App {
    public static void main(String[] args) {
        ShareResource source= new ShareResource();
        //消费者先启动，进入等待
        //消费者
        new Thread(new Consumer(source),"Thread_Consumer").start();
        //生产者
        new Thread(new Producter(source), "Thread_Product").start();


    }
}
