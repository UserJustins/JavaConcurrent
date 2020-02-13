package com.duheng.concurrent.KouDingLang.ProductConsum.T_04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*************************
 Author: 杜衡
 Date: 2020/2/12
 Describe:
    消费者和生产者案例使用Lock锁的机制来完成.线程间的通信使用Condition
 *************************/
public class T_02 {

    private String name;
    private String gender;
    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();
    boolean isEmpty = true;


    public void push(String name,String gender){
        lock.lock();//获取锁
        try {
            while (!isEmpty){//不用生产就等着
                condition.await();
            }
            this.name = name;
            this.gender = gender;
            //生产完成
            condition.signalAll();
            this.isEmpty = false;
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }

    public void get(){

        lock.lock();
        try {
            while (isEmpty){//true的话就等着
                condition.await();
            }
            System.out.println(this.name + "--" + this.gender);
            //消费完通知生产者
            condition.signalAll();
            this.isEmpty = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T_02 t = new T_02();
        //创建消费者
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0) {
                    t.push("杜衡"+i,"男");
                }else{
                    t.push("姜莹"+i,"女");
                }
            }
        },"product").start();
        //创建消费者线程
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                t.get();
            }
        },"consumer").start();
    }
}
