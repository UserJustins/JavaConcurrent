package com.duheng.concurrent.MaShiBing.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/*************************
 Author: 杜衡
 Date: 2020/2/11
 Describe:

 使用Latch（门闩）代替wait、notify来进行通信
 好处就是通信方式简单，同时也可以指定等待的时间
 使用await和countdown放代替wait和notify
 CountDownLatch不涉及锁定，当count的值为0的时候当前线程继续运行，await的线程也执行
 当不涉及同步，知识设计线程的通信时，用synchronized+wait和notify就显得太重了
 这时应该考虑CountDownLatch\CyclicBarrier\Semaphore




    难道是我钻牛角了？下面的代码片段想要得到预期的结果。
 1、线程2放开门闩之前，线程1必须已经执行latch.await();这尼玛在实际开发中太扯了吧

 看似实现了Exer_01中的问题，可这是啥吗，这不是扯淡吗
 *************************/
public class Exer_04 {

    List<Integer> list = new ArrayList<>();


    public void add(Integer i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        Exer_04 o = new Exer_04();
        //
        CountDownLatch latch = new CountDownLatch(1);
        //创建一个线程，监控线程2
        new Thread(()->{
            /*try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "结束");

        },"Thread1").start();



        //创建线程2
        new Thread(()->{

                for (int i = 0; i < 10; i++) {
                    o.add(i);
                    System.out.println("add"+i);
                    if (o.size() == 5) {

                       latch.countDown();
                    }
                    try {//慢一点，确保 latch.await()在latch.countDown()之前执行
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        },"Thread2").start();
    }
}
