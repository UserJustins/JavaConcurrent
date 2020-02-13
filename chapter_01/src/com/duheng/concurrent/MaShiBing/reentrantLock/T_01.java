package com.duheng.concurrent.MaShiBing.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*************************
 Author: 杜衡
 Date: 2020/2/11
 Describe:
    使用ReentrantLock可以代替synchronized

    lock.lock();
    try{

    }catch(Exception e){

    }finally{
        lock.unlock();
    }


 需要注意的是：ReentrantLock必须要手动的释放锁
 使用synchronized遇到异常的话，jvm会自动的释放锁，但是lock必须手动释放锁,
 通常我们要在finally中手动的释放锁
 *************************/
public class T_01 {

    Lock lock = new ReentrantLock();

    public void m1(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        try {

            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }finally{
            lock.unlock();
        }
    }

    public void m2(){
        lock.lock();
        System.out.println("m2 run...");
        //lock.unlock();//如果不释放
        System.out.println("lock没有释放");
    }

    public static void main(String[] args) {
        T_01 t = new T_01();
        new Thread(()->t.m1()).start();
        new Thread(()->t.m2()).start();
    }
}
