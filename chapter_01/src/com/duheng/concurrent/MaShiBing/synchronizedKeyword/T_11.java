package com.duheng.concurrent.MaShiBing.synchronizedKeyword;

/*************************
 Author: 杜衡
 Date: 2020/2/11
 Describe:
    启动顺序并不是线程的执行顺序
 *************************/
public class T_11 {
    public static void main(String[] args) {
        final Object lock = new Object();
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock){

                System.out.println(Thread.currentThread().getName() + "run");
            }
        },"Thread1").start();
        new Thread(()->{
            synchronized (lock){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "run");
            }
        },"Thread2").start();
        new Thread(()->{
            synchronized (lock){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "run");
            }
        },"Thread3").start();
        new Thread(()->{
            synchronized (lock){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "run");
            }
        },"Thread4").start();
    }
}
