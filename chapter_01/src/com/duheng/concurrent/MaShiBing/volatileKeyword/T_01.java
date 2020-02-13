package com.duheng.concurrent.MaShiBing.volatileKeyword;

/*************************
 Author: 杜衡
 Date: 2020/2/10
 Describe:
    1.volatile关键字，使一个变量在多线程间可见
    2.A、B两个线程都用一个变量，Java默认是A线程中保留一份Copy，这样如果
 B线程修改了变量，则A线程未必知道这个改变
    3.使用volatile关键字，会让所有线程都会读到变量的修改值

 1、下面的代码中，running是存在于堆内存的t对象中
 2、当线程t1开始运行的时候，会把running中从内存中读到t1线程的工作区，在运行的
 过程中直接使用这个Copy，并不会每次都去读取堆内存，这样，当主线程修改running
 的值以后，t1线程感知不到，所以也就不会停止循环
 3、使用volatile，将会强制所有的线程都去堆内存中读取running的值
 4、相关的文章介绍
    http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html

 volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说
 volatile不能代替synchronized

 *************************/
public class T_01 {
    //对比一下有无volatile关键字的区别
    /*volatile*/ boolean running = true;
    public void m (){
        System.out.println("m.start");
        while (running){

        }
        System.out.println("m.end");
    }

    public static void main(String[] args) {
        T_01 t = new T_01();
        new Thread(()->t.m()).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running=false;

    }
}
