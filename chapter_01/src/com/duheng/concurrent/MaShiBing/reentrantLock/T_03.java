package com.duheng.concurrent.MaShiBing.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/*************************
 Author: 杜衡
 Date: 2020/2/11
 Describe:
    试了试lockInterruptibly
 当调用t.interrupt();出现的
 java.lang.InterruptedException文章好大哦
 *************************/
public class T_03 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        /**
         * Thread1把锁抓的死死的
         */
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "run");
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"Thread1").start();

        //睡一会保证线程1先运行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t = new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+":等待获取锁");
                lock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName()+":已获取锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName()+":已放弃获取锁");
            }
        },"Thread2");

        t.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
    }
}
