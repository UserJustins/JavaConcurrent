package com.duheng.concurrent.MaShiBing.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*************************
 Author: 杜衡
 Date: 2020/2/11
 Describe: 多线程其实挺有意思的
    synchronized和ReentrantLock的区别 1:
    ReentrantLock可以尝试着去拿锁，拿不拿的到我都接着执行用不着死等
 =============================================================
    ReentrantLock还可以尝试着去拿锁。拿不拿的到，我线程接着往下执行，
 届时我在进行业务判断就行，如果拿到了怎么办，没有拿到怎么办。
    还可以指定tryLock的时间
 《===注意：为了能把下一个程序的lockInterruptibly()讲明白，在这和tryLock牵强的做个比较，有助于理解

 tryLock(),当前的线程尝试一次或指定时间段里多次获取锁，指定时间段有没有获取到，
 只要时间一到，程序接着往下执行。
 要说的就是这个指定的时间，lockInterruptibly()表示当前线程尝试获取锁，直到获取。这里的时间
 就和try的时间有了明显的区别。
 那要是一直获取不到，我又不想让他继续获取，我们就可以使用其他线程来打断这种状态====》
 ===============以上《==== ====》中的话，在深入了解发现lockInterruptibly比我想的要复杂的多，上面的话不可信
 *************************/
public class T_02 {

    final ReentrantLock lock = new ReentrantLock();

    public void m1(){
        lock.lock();

        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock.unlock();
    }

    /**
     * tryLock进行一次尝试
     */
    public void m2(){
        //尝试着去拿一次锁，拿不拿的到再议
        boolean bool = lock.tryLock();
        System.out.println(bool+"-->m2");
        if (bool) {//如果拿到了记得要释放锁
            //TODO
            lock.unlock();
        }else{
            //TODO
        }
    }

    /**
     * 指定时间段内一直尝试获取锁
     */
    public void m3(){
        Boolean bool = false;
        try {
            bool = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println(bool+"-->m3");
            //TODO
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            if(bool) lock.unlock();
        }
    }

    public static void main(String[] args) {
        T_02 t = new T_02();
        new Thread(()->t.m2()).start();
        new Thread(()->t.m1()).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->t.m3()).start();
    }
}
