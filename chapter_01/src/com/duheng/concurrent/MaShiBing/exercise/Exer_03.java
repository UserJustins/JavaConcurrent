package com.duheng.concurrent.MaShiBing.exercise;

import java.util.ArrayList;
import java.util.List;

/*************************
 Author: 杜衡
 Date: 2020/2/11
 Describe:
    两个问题：1、原子性 2、不使用while一直监测
 思路：
 1、保证原子性，就是同步，我们必然要进行加锁
 2、当线程1加到5的时候，让它去通知线程2
 实现：
    加锁 wait和notify方法，详情请阅读代码和注释



 如果想要得到期望的结果，线程2要先运行。
 太理想主义了，不太现实，join方法可以保证线程2先运行，可在这根本不行
 *************************/
public class Exer_03 {
    /**
     * 个人认为这段代码没有必要使用volatile
     * synchronized既保证可见性也保证原子性
     */
    /*volatile*/ List<Integer> list = new ArrayList<>();


    public void add(Integer i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }


    public static void main(String[] args) {
        Exer_03 e = new Exer_03();
        //锁
        final Object lock = new Object();

        /**
         * 1、先创建线程2，让它拥有过这个锁
         * 2、使用wait让它释放锁，并等待线程1唤醒它
         */
        new Thread(()->{
        /*    try {
                *//**
                 * 睡一会，让线程1先运行
                 * 这种情况下就出大事了，线程1和线程2都去等待池了，程序阻塞了，怎么办？
                 *//*
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }*/
            synchronized (lock){
                System.out.println(Thread.currentThread().getName() + "开始");
                /**
                 * 老师在wait之前还进行判断
                 * if(e.size()!=5){
                 *      lock.wait();
                 * }
                 * 我觉得完全没有必要。
                 * 线程2是最先启动，线程1后启动，但是指不定谁先运行呢
                 * 如果线程1先运行了，肯定是size()=5的时候过来的（实际上线程1不能先运行）
                 * 如果线程2先运行你这有何意义呢？
                 */
                try {//释放锁，线程1才能执行
                    lock.wait();//在lock对象的等待池中等待线程1唤醒它，（接着去线程1看）
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                //被唤醒接着运行
                System.out.println(Thread.currentThread().getName() + "结束");
                //线程2运行完之前，一定要把线程1从阻塞状态弄成就绪状态
                //不要想着run方法执行完，线程2就释放锁对象了，可是线程1不是就绪状态呀，根本就碰不上锁
                lock.notify();
            }
        },"Thread2").start();

        //join()方法是能保证Thread2先执行，但Thread2和main线程会一起阻塞

        /**
         * 创建线程1
         */
        new Thread(()->{
            synchronized (lock){
                System.out.println(Thread.currentThread().getName() + "开始");
                for (int i = 0; i < 10; i++) {
                    e.add(i);
                    System.out.println("add：" + i);
                    if (e.size()==5) {
                        //线程1去唤醒线程2进入锁池中，此时线程2就是就绪状态
                        lock.notify();
                        //但是notify并不会释放锁，因此线程2不可能拥有CPU的资源运行起来
                        try {
                            System.out.println("Thread1-wait");
                            lock.wait();//程序又回到了线程2
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        },"Thread1").start();
    }
}
