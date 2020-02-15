package com.duheng.concurrent.Wolfcode.ProductConsum;

/*************************
 Author: 杜衡
 Date: 2020/2/15
 Describe: 编写程序，开启三个线程，分别为A、B、C，每个线程将自己的名字在
 屏幕上打印10次，要求输出的结果必须按顺序显示
    如：ABCABC....
 *************************/
public class Exercise_01 {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(()->{
                System.out.print(Thread.currentThread().getName());
            },"A");
            t1.start();
            t1.join();
            Thread t2 = new Thread(()->{
                System.out.print(Thread.currentThread().getName());
            },"B");
            t2.start();
            t2.join();
            Thread t3 = new Thread(()->{
                System.out.print(Thread.currentThread().getName()+",");
            },"C");
            t3.start();
            t3.join();
        }
    }
}
