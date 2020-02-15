package com.duheng.concurrent.Wolfcode.ProductConsum;

import java.util.concurrent.TimeUnit;

/*************************
 Author: 杜衡
 Date: 2020/2/15
 Describe:
    生产者和消费者
 *************************/
public class Exercise_02 {

    private String name;
    private String gender;
    private boolean isEmpty = true;

    /**
     * 生产
     * @param name
     * @param gender
     */
    public synchronized void push(String name,String gender){
        while (!isEmpty){//不空就歇着
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //空了就生产
        this.name = name;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.gender = gender;
        this.isEmpty = false;
        this.notifyAll();//唤醒消费者

    }

    /**
     * 消费
     */
    public synchronized void get(){
        while (isEmpty){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.name + "---" + this.gender);
        this.isEmpty = true;
        this.notifyAll();
    }


    public static void main(String[] args) {
        //共享的资源对象
        Exercise_02 share = new Exercise_02();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                if (i % 2 ==0) {
                    share.push("杜衡","男");
                }else{
                    share.push("姜莹","女");
                }
            }

        },"product").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                share.get();
            }

        },"consumer").start();
    }
}
