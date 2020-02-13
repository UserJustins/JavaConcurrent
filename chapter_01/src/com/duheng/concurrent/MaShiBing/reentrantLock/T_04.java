package com.duheng.concurrent.MaShiBing.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/*************************
 Author: 杜衡
 Date: 2020/2/12
 Describe:
 synchronized和ReentrantLock的区别 2:
 公平锁：谁在锁池中等待的时间长，我就让谁先执行
 *************************/
public class T_04 implements Runnable{
    //其实这也没公平多少嘛
    ReentrantLock lock = new ReentrantLock(true);//公平锁
    @Override
    public void run() {

        /**
         * 每个线程循环一次就结束
         */
        for (int i = 0; i <10; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName());
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T_04 t = new T_04();
        new Thread(t, "Thread1").start();
        new Thread(t, "Thread2").start();

    }
}
