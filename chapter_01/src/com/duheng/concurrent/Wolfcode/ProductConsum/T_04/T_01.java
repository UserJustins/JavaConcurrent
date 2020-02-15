package com.duheng.concurrent.Wolfcode.ProductConsum.T_04;

/*************************
 Author: 杜衡
 Date: 2020/2/12
 Describe:
    一个生产者和一个消费者，已编写完成
 一个生产者，多个消费者怎么实现？已编写完成
    为什么消费线程会如此的和谐？从来就没有一个线程重复消费两次
 *************************/
public class T_01 {
    String name;
    String gender;
    boolean isEmpty = true;//当成盘子吧，表示盘子里有没有东西
    /**
     * 生产者进行生产
     * @param name
     * @param gender
     */
    public synchronized void push(String name,String gender){
        while (!isEmpty) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name ;
        this.gender = gender;
        this.isEmpty = false;
        this.notifyAll();
    }

    /**
     * 消费者进行消费
     */
    public synchronized void pop(){
        while (isEmpty){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+this.name+"-"+this.gender);
        this.isEmpty = true;
        this.notifyAll();
    }

    public static void main(String[] args) {
        T_01 t = new T_01();



        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                if (i % 2 == 0) {
                    t.push("杜衡","男");
                }else{
                    t.push("姜莹","女");
                }
            }
        },"product").start();

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                t.pop();

            },"consumer"+i).start();
        }
    }
}
