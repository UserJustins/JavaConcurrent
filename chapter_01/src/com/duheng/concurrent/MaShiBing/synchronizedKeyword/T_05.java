package com.duheng.concurrent.MaShiBing.synchronizedKeyword;

/*************************
 Author: 杜衡
 Date: 2020/2/10
 Describe:
    同步方法和非同步的方法是否可以同时调用
        当然可以了
 *************************/
public class T_05 {

    public synchronized void m1()  {//m1方法运行需要this这把锁
        System.out.println(Thread.currentThread().getName()+"method M1 start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();//执行m2()时是不需要锁的
        System.out.println(Thread.currentThread().getName()+"method M2 end");

    }

    public void m2()  {
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
        new Thread(()->t.m1(),"Thread2").start();
    }

}
