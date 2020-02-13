package com.duheng.concurrent.MaShiBing.synchronizedKeyword;

/*************************
 Author: 杜衡
 Date: 2020/2/10
 Describe:
    同一线程，同一把锁，那么同步方法中也是可以调用同步方法的
 结论：synchronized获得的锁是可重入的。
        可重入就是m1获得锁对象，m1中调用m2方法，m2是可以再次申请到这把锁
 *************************/
public class T_06 {
    public synchronized void m1()  {
        System.out.println(Thread.currentThread().getName()+"method M1 start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println(Thread.currentThread().getName()+"method M2 end");

    }

    public synchronized  void m2()  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"method M2 run");
    }

    public static void main(String[] args) {
        T_05 t = new T_05();
        //创建两个线程
        new Thread(()->t.m1(),"Thread1").start();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                t.m1();
            }
        },"Thread3").start();*/
        new Thread(()->t.m1(),"Thread2").start();
    }
}
