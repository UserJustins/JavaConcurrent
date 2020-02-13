package com.duheng.concurrent.MaShiBing.synchronizedKeyword;

/*************************
 Author: 杜衡
 Date: 2020/2/10
 Describe:
    锁对象改变属性不会使线程释放锁，但是对象的引用改变了就会释放
 锁的是堆内存，不会栈
 *************************/
public class T_09 {

    Object o = new Object();

    public void m(){
        synchronized(o){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true){
                System.out.println(Thread.currentThread().getName()+"running");
            }
        }

    }

    public static void main(String[] args) {
        T_09 t = new T_09();
        new Thread(()->t.m(),"t1").start();

        Thread t2 = new Thread(()->t.m(),"t2");
        //照上面的代码片段，永远只会有一个线程在运行
        t.o = new Object();//改变锁对象，线程就是释放锁那么其他线程也就有机会了
        t2.start();
    }
}
